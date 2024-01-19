package entity_Impl;

import level_Interfaces.*;
import common.*;
import entity_Interfaces.*;
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
        m_globalPosition = newPosition;
    }

    /**
     * Stalls the Entity for any amount of time.
     *
     * @param stallDuration stall time in seconds.
     */
    public final void stall(long stallDuration) {
        long currentTime = System.nanoTime();
        long currentStallEndTime = m_stallStartTime + m_stallDuration;
        if (!m_isStalled || currentStallEndTime < currentTime + stallDuration) {
            m_isStalled = true;
            m_stallStartTime = currentTime;
            m_stallDuration = stallDuration;
        }
    }

    public final boolean isStalled() {
        return m_isStalled;
    }

    @Override
    protected final BufferedImage getSpriteToDraw() {
        if (m_isDieing) {
            return m_deathAnimation.getSpriteToDraw();
        }
        Animation animationToUse = m_isIdle ? m_idleAnimations.get(getDisplayDirection()) : m_movementAnimations.get(getDisplayDirection());
        if (m_lastAnimation != null && m_lastAnimation != animationToUse) {
            animationToUse.continueFromAnimation(m_lastAnimation);
        }
        BufferedImage spriteToUse = animationToUse.getSpriteToDraw();
        m_lastUsedAnimationSprite = spriteToUse;
        return spriteToUse;
    }

    @Override
    protected void onUpdate() {
        if (!isDieing() && !isDead()) {
            if (m_isStalled) {
                boolean isStallTimerUp = System.nanoTime() >= m_stallStartTime + m_stallDuration;
                m_isStalled = !isStallTimerUp;
            }
            tryMove();
            act();
        }
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
        
        try {
            m_lastUsedAnimationSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(skinPath + "Idle_Down_0.png"));
        }
        catch (IOException ex) {
            Logger.getLogger(MovingEntityAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract Speed getSpeed();

    protected void act() {
        // Do nothing.
    }
    
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
        int speed = getSpeed().toInt();
        int fps = 240; // Todo: get from GameManagementService or something.
        boolean isMovementFrame = m_framesSinceLastMovement >= fps / (speed * 12); // Todo: create a constant for 12. (scale factor or so)
        boolean hasMoved = false;
        if (isMovementFrame && !m_isStalled) {
            Direction direction = getMovementDirection();
            m_isIdle = direction == Direction.NoDirection;
            if (!m_isIdle) {
                m_globalPosition.translate(direction, STEP_SIZE);
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

    // Todo: find appropriate existing methods.

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
    
    /**
     * Gets the direction the entity is facing.
     * @return 
     */
    protected abstract Direction getDisplayDirection();

    private boolean m_isIdle;
    private boolean m_isStalled;
    private long m_stallStartTime;
    private long m_stallDuration;
    private Animation m_lastAnimation;
    private BufferedImage m_lastUsedAnimationSprite;
    private String m_skinPath;

//    protected int m_speed;
    private static final int STEP_SIZE = 1;
    private int m_framesSinceLastMovement;
    protected final HashMap<Direction, Animation> m_idleAnimations;
    protected final HashMap<Direction, Animation> m_movementAnimations;

    protected MovementServiceIfc m_movementService;
}