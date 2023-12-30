package entity_Impl;

import level_Interfaces.LevelNames;
import level_Interfaces.MovementServiceIfc;
import common.Animation;
import common.Coordinates;
import common.Direction;
import common.ServiceManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yanick
 */
public abstract class MovingEntityAbs extends EntityAbs {

    public MovingEntityAbs(Coordinates position, String skinPath) {
        super(position);
        m_idleAnimations = new HashMap<>();
        m_movementAnimations = new HashMap<>();
        m_isIdle = true;
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        
        setupAnimations(skinPath);
    }

    public final void Teleport(Coordinates newPosition) {
        m_position = newPosition;
    }

    /**
     * Stalls the Entity for any amount of time.
     *
     * @param stallDuration stall time in seconds.
     */
    public final void stall(int stallDuration) {
        m_isStalled = true;
        m_stallStartTime = System.nanoTime();
        m_stallDuration = stallDuration;
    }

    public final boolean isStalled() {
        return m_isStalled;
    }

    @Override
    protected final BufferedImage getSpriteToDraw() {
        if (m_isDieing) {
            return m_deathAnimation.getSpriteToDraw();
        }
        if (m_isIdle) {
            return m_lastUsedAnimationSprite;
        }
        Animation animationToUse = m_isIdle ? m_idleAnimations.get(m_direction) : m_movementAnimations.get(m_direction);
        if (m_lastAnimation != null && m_lastAnimation != animationToUse) {
            animationToUse.continueFromAnimation(m_lastAnimation);
        }
        BufferedImage spriteToUse = animationToUse.getSpriteToDraw();
        m_lastUsedAnimationSprite = spriteToUse;
        return spriteToUse;
    }

    @Override
    public void update() {
        if (m_isStalled) {
            boolean isStallTimerUp = System.nanoTime() >= m_stallStartTime + m_stallDuration * 1000 * 1000 * 1000;
            m_isStalled = !isStallTimerUp;
        }
        tryMove();
    }

    public final void setupAnimations(String skinPath) {
        m_skinPath = skinPath;
        m_idleAnimations.put(Direction.Up, createAnimation(true, Direction.Up));
        m_idleAnimations.put(Direction.Right, createAnimation(true, Direction.Right));
        m_idleAnimations.put(Direction.Down, createAnimation(true, Direction.Down));
        m_idleAnimations.put(Direction.Left, createAnimation(true, Direction.Left));
        m_movementAnimations.put(Direction.Up, createAnimation(false, Direction.Up));
        m_movementAnimations.put(Direction.Right, createAnimation(false, Direction.Right));
        m_movementAnimations.put(Direction.Down, createAnimation(false, Direction.Down));
        m_movementAnimations.put(Direction.Left, createAnimation(false, Direction.Left));
        
        m_deathAnimation = createDeathAnimation();
        
        // Todo: Improve and put this in a better spot.
        try {
            m_lastUsedAnimationSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Monsters/Ghost/Skin_0/Idle_Down_0.png"));
        }
        catch (IOException ex) {
            Logger.getLogger(MovingEntityAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract int getSpeed();

    private Animation createAnimation(boolean isIdleAnimation, Direction direction) {
        String idleString = isIdleAnimation ? "Idle" : "Moving";
        String spriteFilePath = m_skinPath + idleString + "_" + direction;
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        int i = 0;
//        spriteFilePath += "_" + i + ".png";
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(spriteFilePath + "_" + i + ".png");
        while (resourceStream != null) {
            BufferedImage nextUpSprite = load(resourceStream);
            sprites.add(nextUpSprite);
            i++;
            resourceStream = getClass().getClassLoader().getResourceAsStream(spriteFilePath + "_" + i + ".png");
        }
        return new Animation(sprites);
    }

    private Animation createDeathAnimation() {
        int i = 0;
        String spriteFilePath = m_skinPath + "Death_";
        ArrayList<BufferedImage> sprites = new ArrayList<>();
//        spriteFilePath += i + ".png";
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(spriteFilePath + i + ".png");
        while (new File(spriteFilePath).exists()) {
            BufferedImage nextDeathSprite = load(resourceStream);
            sprites.add(nextDeathSprite);
            i++;
            resourceStream = getClass().getClassLoader().getResourceAsStream(spriteFilePath + i + ".png");
        }
        return new Animation(sprites);
    }

    private void tryMove() {
        int speed = getSpeed();
        int fps = 240; // Todo: get from GameManagementService or something.
        boolean isMovementFrame = m_framesSinceLastMovement >= fps / (speed * 12); // Todo: create a constant for 12. (scale factor or so)
        boolean hasMoved = false;
        if (isMovementFrame && !m_isStalled) {
            Direction direction = getMovementDirection();
            m_isIdle = direction == Direction.NoDirection;
            if (!m_isIdle) {
                m_position.translate(direction, STEP_SIZE);
                hasMoved = true;
                m_direction = direction;
            }
        }

        if (hasMoved) {
            m_framesSinceLastMovement = 0;
        }
        else {
            m_framesSinceLastMovement++;
        }
    }
    
    // Todo: move this to MovingEntityAbs
//    private void initSprites(){
//        ArrayList<BufferedImage> idleUpSprites = new ArrayList();
//        ArrayList<BufferedImage> idleRightSprites = new ArrayList();
//        ArrayList<BufferedImage> idleDownSprites = new ArrayList();
//        ArrayList<BufferedImage> idleLeftSprites = new ArrayList();
//        // Todo: get all sprites.
//        try {
//            idleUpSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream(m_skinPath + "_Idle_Up_0.png")));
//            idleRightSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream(m_skinPath + "_Idle_Right_0.png")));
//            idleDownSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream(m_skinPath + "_Idle_Down_0.png")));
//            idleLeftSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream(m_skinPath + "_Idle_Left_0.png")));
//        }
//        catch (IOException ex) {
//            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        m_idleAnimations.put(Direction.Up, new Animation(idleUpSprites));
//        m_idleAnimations.put(Direction.Right, new Animation(idleRightSprites));
//        m_idleAnimations.put(Direction.Down, new Animation(idleDownSprites));
//        m_idleAnimations.put(Direction.Left, new Animation(idleLeftSprites));
//        
//        m_movementAnimations.put(Direction.Up, new Animation(idleUpSprites));
//        m_movementAnimations.put(Direction.Right, new Animation(idleRightSprites));
//        m_movementAnimations.put(Direction.Down, new Animation(idleDownSprites));
//        m_movementAnimations.put(Direction.Left, new Animation(idleLeftSprites));
//    }

    // Todo: find appropriate existing methods.
    private BufferedImage load(InputStream fileStream) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(fileStream);
        }
        catch (IOException ex) {
            Logger.getLogger(MovingEntityAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sprite;
    }

    protected ArrayList<EntityAbs> getCollisions() {
        // todo: implement this.
        return new ArrayList();
    }

    /**
     * Gets the direction the entity will move in next. This direction is based
     * on the position in the stage.
     *
     * @return next direction the player moves in.
     */
    protected abstract Direction getMovementDirection();

    private boolean m_isIdle;
    private boolean m_isStalled;
    private long m_stallStartTime;
    private int m_stallDuration;
    private Animation m_lastAnimation = null;
    private BufferedImage m_lastUsedAnimationSprite;
    private String m_skinPath;

//    protected int m_speed;
    private static final int STEP_SIZE = 1;
    private int m_framesSinceLastMovement;
    protected final HashMap<Direction, Animation> m_idleAnimations;
    protected final HashMap<Direction, Animation> m_movementAnimations;

    protected MovementServiceIfc m_movementService;
}