package common;

import java.awt.image.BufferedImage;

/**
 *
 * @author Yanick
 */
public interface AnimationIfc {

    /**
     * Gets the current sprite in the animation that needs to be drawn.
     * @return
     */
    BufferedImage getSpriteToDraw();

    /**
     * Copies an animation.
     * @return
     */
    AnimationIfc copy();
}
