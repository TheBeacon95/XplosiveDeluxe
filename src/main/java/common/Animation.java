package common;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the sprites as Buffered image and updates the sprite to be drawn
 * automatically after being drawn.
 * @author Yanick
 */
public class Animation implements AnimationIfc {

    public Animation(List<BufferedImage> animationSprites, int animationDuration) {
        m_animationSprites = new ArrayList<>(animationSprites);
        m_spriteCount = animationSprites.size();
        m_animationDuration = animationDuration > 0 ? animationDuration : DEFAULT_ANIMATION_DURATION;
        m_isRepeating = true;
        m_animationStartTime = System.nanoTime();
    }

    public Animation(List<BufferedImage> animationSprites) {
        this(animationSprites, DEFAULT_ANIMATION_DURATION);
    }

    private Animation(Animation animationToCopy) {
        m_animationSprites = animationToCopy.m_animationSprites;
        m_spriteCount = animationToCopy.m_spriteCount;
        m_animationDuration = animationToCopy.m_animationDuration;
        m_isRepeating = animationToCopy.m_isRepeating;
        m_animationStartTime = System.nanoTime();
    }

    /**
     * Tries to take over the animation from the last one to make it seamless.
     * @param lastAnimation
     */
    public void continueFromAnimation(Animation lastAnimation) {
        boolean doSpriteCountsMatch = m_spriteCount == lastAnimation.m_spriteCount;
        boolean doSpriteDurationsMatch = m_animationDuration == lastAnimation.m_animationDuration;
        if (doSpriteCountsMatch && doSpriteDurationsMatch) {
            m_animationStartTime = lastAnimation.m_animationStartTime;
        }
    }

    /**
     * Retrieves the next sprite that needs to be drawn.
     * @return the next sprite in the animation.
     */
    @Override
    public BufferedImage getSpriteToDraw() {
        BufferedImage spriteToDraw = null;
        updateAnimationValues();
        if (!m_isDone && !m_animationSprites.isEmpty()) {
            spriteToDraw = m_animationSprites.get(m_currentSpriteIndex);
        }
        return spriteToDraw;
    }

    @Override
    public Animation copy() {
        return new Animation(this);
    }

    public boolean isDone() {
        return m_isDone;
    }

    public void setAnimationDuration(long durationNs) {
        if (durationNs > 0) {
            m_animationDuration = durationNs;
        }
    }

    public void start() {
        m_animationStartTime = System.nanoTime();
        m_isDone = false;
    }

    public void setRepeatingAnimation() {
        m_isRepeating = true;
    }

    public void setSingleAnimation() {
        m_isRepeating = false;
    }

    private void refreshAnimationStartTime() {
        long currentTime = System.nanoTime();
        while (currentTime - m_animationStartTime >= m_animationDuration) {
                m_animationStartTime += m_animationDuration;
        }
    }

    private void updateAnimationValues() {
        long currentTime = System.nanoTime();
        m_isDone = !m_isRepeating && currentTime - m_animationStartTime >= m_animationDuration;

        if (!m_isDone) {
            refreshAnimationStartTime();
            m_currentSpriteIndex = (int) ((currentTime - m_animationStartTime) * m_spriteCount / m_animationDuration);
        }
        else {
            m_currentSpriteIndex = 0;
        }
    }

    private boolean m_isRepeating;
    private boolean m_isDone;
    private long m_animationDuration;       // Represents the duration of the animation
    private final int m_spriteCount;    // Represents the number of sprites there are.
    private int m_currentSpriteIndex; // Acts as an index for the animation.
    private long m_animationStartTime; // Shows when the animation has started.
    private final ArrayList<BufferedImage> m_animationSprites;
    private static final int DEFAULT_ANIMATION_DURATION = 1000 * 1000 * 1000; // Duration of each sprite in nanoseconds.
}