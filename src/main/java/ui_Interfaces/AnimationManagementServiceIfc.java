package ui_Interfaces;

import common.ServiceIfc;

/**
 *
 * @author Yanick
 */
public interface AnimationManagementServiceIfc extends ServiceIfc {
    /**
     * Allows all animations to play.
     */
    void startAnimations();

    /**
     * Pauses all animations.
     */
    void pauseAllAnimations();

    /**
     * Shows if animations can currently be played or not.
     * @return true if animations are supposed to be played. false if they are paused or havent been started yet.
     */
    boolean areAnimationsActive();
}