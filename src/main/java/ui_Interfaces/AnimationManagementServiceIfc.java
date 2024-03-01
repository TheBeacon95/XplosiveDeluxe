package ui_Interfaces;

import common.*;

/**
 *
 * @author Yanick
 */
public interface AnimationManagementServiceIfc extends ServiceIfc {

    /**
     * Either retrieves an existing animation or creates one and then retrieves it.
     * @param animationId
     * @return
     */
    AnimationIfc getAnimation(String animationId);

    /**
     * Sets an animation
     * @param animationId
     * @param animation
     */
    void setAnimation(String animationId, AnimationIfc animation);
}