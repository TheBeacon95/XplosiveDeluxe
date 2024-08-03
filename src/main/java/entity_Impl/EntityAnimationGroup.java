package entity_Impl;

import common.*;
import java.awt.image.BufferedImage;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class EntityAnimationGroup extends AnimationGroupAbs {
    private EntityAbs m_entity;
    private boolean m_isMovingEntity;
    private final AnimationManagementServiceIfc m_animationManagementService;
    private static final String DEATH_TEXT = "Death";
    private static final String IDLE_TEXT = "Idle";
    private static final String MOVING_TEXT = "Moving";
    private static final String GROUP_NAME = "MovingEntityAnimationGroup";

    public EntityAnimationGroup() {
        m_animationManagementService = ((AnimationManagementServiceIfc) ServiceManager.getService(UiNames.Services.AnimationManagementService));
    }

    public void attachEntity(MovingEntityAbs entity) {
        m_entity = entity;
        m_isMovingEntity = true;
    }

    public void attachEntity(EntityAbs entity) {
        m_entity = entity;
        m_isMovingEntity = false;
    }

    @Override
    public void setSprites(String folderPath) {
        clear();

        addAnimation(folderPath, DEATH_TEXT);
        if (!m_isMovingEntity) {
            addAnimation(folderPath, IDLE_TEXT);
        }
        for (Direction direction: Direction.values()) {
            if (direction == Direction.NoDirection) {
                break;
            }

            if (m_isMovingEntity) {
                addAnimation(folderPath, IDLE_TEXT + "_" + direction.name());
                addAnimation(folderPath, MOVING_TEXT + "_" + direction.name());
            }
        }

        m_animationManagementService.setAnimation(GROUP_NAME + "_" + folderPath, this);
    }

    @Override
    public BufferedImage getSpriteToDraw() {
        // Todo: Improve so new animations take over from old ones (continueFromAnimation())

        String id;
        if (m_entity.isDieing()) {
            id = DEATH_TEXT;
        }
        else if (m_isMovingEntity) {
            MovingEntityAbs movingEntity = (MovingEntityAbs) m_entity;
            id = (movingEntity.isIdle() ? IDLE_TEXT : MOVING_TEXT) + "_" + movingEntity.getDisplayDirection().name();
        }
        else {
            id = IDLE_TEXT;
        }
        return get(id).getSpriteToDraw();
    }

    @Override
    public EntityAnimationGroup copy() {
        EntityAnimationGroup copy = new EntityAnimationGroup();
        insertCopies(copy);
        return copy;
    }

    private void addAnimation(String folderPath, String animationName) {
        Animation deathAnimation = AnimationMaker.createAnimation(folderPath, animationName);
        add(animationName, deathAnimation);
    }
}