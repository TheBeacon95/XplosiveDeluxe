package ui_Interfaces;

import common.*;

/**
 * This service is responsible for holding all animations, so they don't need to be created on the fly.
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

    /**
     * Removes all animations
     */
    void clearAnimations();
}