package entity_Impl;

import level_Interfaces.LevelNames;
import level_Interfaces.MovementServiceIfc;
import common.Animation;
import common.Coordinates;
import common.Direction;
import common.ServiceManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yanick
 */
public abstract class MovingEntityAbs extends EntityAbs {

    public MovingEntityAbs(Coordinates position) {
        super(position);
        m_idleAnimations = new HashMap<>();
        m_movementAnimations = new HashMap<>();
        m_isIdle = true;
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
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

    public void setupAnimations(String skinPath) {
        m_skinPath = skinPath;
        m_idleAnimations.put(Direction.Up, createAnimation(true, Direction.Up));
        m_idleAnimations.put(Direction.Right, createAnimation(true, Direction.Right));
        m_idleAnimations.put(Direction.Down, createAnimation(true, Direction.Down));
        m_idleAnimations.put(Direction.Left, createAnimation(true, Direction.Left));
        m_movementAnimations.put(Direction.Up, createAnimation(false, Direction.Up));
        m_idleAnimations.put(Direction.Right, createAnimation(false, Direction.Down));
        m_idleAnimations.put(Direction.Down, createAnimation(false, Direction.Right));
        m_idleAnimations.put(Direction.Left, createAnimation(false, Direction.Left));

        m_deathAnimation = createDeathAnimation();
    }

    protected int getSpeed() {
        return m_speed;
    }

    private Animation createAnimation(boolean isIdleAnimation, Direction direction) {
        String idleString = isIdleAnimation ? "Idle" : "Moving";
        int i = 0;
        String nextSpriteFilePath = m_skinPath + idleString + "_" + direction + "_" + i;
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        BufferedImage nextUpSprite = load(nextSpriteFilePath);
        while (nextUpSprite != null) {
            i++;
            sprites.add(nextUpSprite);
            nextUpSprite = load(nextSpriteFilePath);
        }
        return new Animation(sprites);
    }

    private Animation createDeathAnimation() {
        int i = 0;
        String nextSpriteFilePath = m_skinPath + "Death_" + i;
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        BufferedImage nextDeathSprite = load(nextSpriteFilePath);
        while (nextDeathSprite != null) {
            i++;
            sprites.add(nextDeathSprite);
            nextDeathSprite = load(nextSpriteFilePath);
        }
        return new Animation(sprites);
    }

    private void tryMove() {
        int speed = 1;
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

    // Todo: find appropriate existing methods.
    private BufferedImage load(String filePath) {
        return null;
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

    protected int m_speed;
    private static final int STEP_SIZE = 1;
    private int m_framesSinceLastMovement;

    protected final HashMap<Direction, Animation> m_idleAnimations;
    protected final HashMap<Direction, Animation> m_movementAnimations;

    protected MovementServiceIfc m_movementService;
}