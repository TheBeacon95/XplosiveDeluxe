package entity_Impl;

import common.*;
import entity_Interfaces.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public abstract class MovingEntityAbs extends EntityAbs {

    public MovingEntityAbs(Coordinates position, String skinPath) {
        super(position, skinPath);
        m_idleAnimations = new HashMap<>();
        m_movementAnimations = new HashMap<>();
        m_isIdle = true;
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
    }

    public final void Teleport(Coordinates newPosition) {
        m_globalPosition = newPosition;
    }

    @Override
    public void stall(long stallDuration) {
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

    public final boolean isIdle() {
        return m_isIdle;
    }

    @Override
    protected final BufferedImage getSpriteToDraw() {
        if (m_isDieing) {
            return m_deathAnimation.getSpriteToDraw();
        }
        else {
            return m_animation.getSpriteToDraw();
        }
    }

    @Override
    protected void onUpdate() {
        if (!isDieing() && !isDead()) {
            if (m_isStalled) {
                boolean isStallTimerUp = System.nanoTime() >= m_stallStartTime + m_stallDuration;
                m_isStalled = !isStallTimerUp;
            }
            tryMove();
//            act();
        }
    }

    protected abstract Speed getSpeed();

    protected void act() {
        // Do nothing.
    }

    private void tryMove() {
        int speed = getSpeed().toInt();
        int fps = 240; // Todo: get from GameManagementService or something.
        boolean isMovementFrame = m_framesSinceLastMovement >= fps / (speed * 6); // Todo: create a constant for 6. (scale factor or so)
        boolean hasMoved = false;
        if (isMovementFrame && !m_isStalled) {
            Direction direction = getMovementDirection();
            m_isIdle = direction == Direction.NoDirection;
            if (!m_isIdle) {
                m_globalPosition.translate(direction, STEP_SIZE);
                hasMoved = true;
                m_direction = direction;
            }
            act();
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
    public abstract Direction getDisplayDirection();

    private boolean m_isIdle;
    private boolean m_isStalled;
    private long m_stallStartTime;
    private long m_stallDuration;

    private static final int STEP_SIZE = 1;
    private int m_framesSinceLastMovement;
    protected final HashMap<Direction, Animation> m_idleAnimations;
    protected final HashMap<Direction, Animation> m_movementAnimations;

    protected MovementServiceIfc m_movementService;

    //

    public final void setAnimation(AnimationIfc animation) {
        m_animation = animation;
    }
    private AnimationIfc m_animation;
}
