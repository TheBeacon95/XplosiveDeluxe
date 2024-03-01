package entity_Impl;

import common.*;
import java.awt.image.BufferedImage;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class MovingEntityAnimationGroup extends AnimationGroupAbs {
    private final MovingEntityAbs m_entity;
    private final AnimationManagementServiceIfc m_animationManagementService;
    private static final String IDLE_TEXT = "Idle";
    private static final String MOVING_TEXT = "Moving";
    private static final String GROUP_NAME = "MovingEntityAnimationGroup";

    public MovingEntityAnimationGroup(MovingEntityAbs entity) {
        m_entity = entity;
        m_animationManagementService = (AnimationManagementServiceIfc) ServiceManager.getService(UiNames.Services.AnimationManagementService);
    }

    @Override
    public void setSprites(String folderPath) {
        clear();

        for (Direction direction: Direction.values()) {
            if (direction == Direction.NoDirection) {
                break;
            }

            String animationName = IDLE_TEXT + "_" + direction.name();
            Animation idleAnimation = AnimationMaker.createAnimation(folderPath, animationName);
            add(animationName, idleAnimation);

            animationName = MOVING_TEXT + "_" + direction.name();
            Animation movingAnimation = AnimationMaker.createAnimation(folderPath, animationName);
            add(animationName, movingAnimation);
        }

        // TODO: implement adding animations to the service.
//        m_animationManagementService.setAnimation(GROUP_NAME + "_" + folderPath, this);
    }

    @Override
    public BufferedImage getSpriteToDraw() {
        // Todo: Improve so new animations take over from old ones (continueFromAnimation())
        String id = m_entity.isIdle() ? IDLE_TEXT : MOVING_TEXT;
        id += "_" + m_entity.getDisplayDirection().name();
        return get(id).getSpriteToDraw();
    }

    @Override
    public MovingEntityAnimationGroup copy() {
        // TODO: implement adding animations to the service.
        return null;
    }
}