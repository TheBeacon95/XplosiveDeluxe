package common;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the sprites as Buffered image and updates the sprite to be drawn
 * automatically after being drawn.
 * @author Yanick
 */
public class Animation {
    
    public Animation(List<BufferedImage> animationSprites, int spriteDuration) {
        m_animationSprites = new ArrayList<>(animationSprites);
        m_spriteCount = animationSprites.size();
        m_spriteDuration = spriteDuration;
    }
    
    public Animation(List<BufferedImage> animationSprites) {
        this(animationSprites, DEFAULT_SPRITE_DURATION);
    }
    
    /**
     * Tries to take over the animation from the last one to make it seamless.
     * @param lastAnimation 
     */
    public void continueFromAnimation(Animation lastAnimation) {
        boolean doSpriteCountsMatch = m_spriteCount == lastAnimation.m_spriteCount;
        boolean doSpriteDurationsMatch = m_spriteDuration == lastAnimation.m_spriteDuration;
        if (doSpriteCountsMatch && doSpriteDurationsMatch) {
            m_currentSpriteCounter = lastAnimation.m_currentSpriteCounter;
        }
    }
    
    /**
     * Retrieves the next sprite that needs to be drawn.
     * @return the next sprite in the animation.
     */
    public BufferedImage getSpriteToDraw() {
        BufferedImage spriteToDraw = m_animationSprites.get(m_currentSpriteCounter / m_spriteDuration);
        m_currentSpriteCounter++;
        if (m_currentSpriteCounter >= m_spriteCount * m_spriteDuration) {
            m_currentSpriteCounter = 0;
        }
        return spriteToDraw;
    }
    
    private int m_spriteDuration;       // Represents the amount of frames an animationSprite is shown for.
    private final int m_spriteCount;    // Represents the number of sprites there are.
    private int m_currentSpriteCounter; // Acts as an index for the animation.
    private final ArrayList<BufferedImage> m_animationSprites;
    private static final int DEFAULT_SPRITE_DURATION = 15; // Duration of each sprite in frames.
}
