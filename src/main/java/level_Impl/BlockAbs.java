package level_Impl;

import common.Animation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import level_Interfaces.BlockType;

/**
 *
 * @author Yanick
 */
public abstract class BlockAbs {

    public BlockAbs(BlockType type) {
        m_type = type;
        loadSprites();
    }

    /**
     * Shows if the block can be walked over.
     * @return true if the block can be walked over. Otherwise false.
     */
    public boolean isWalkable() {
        return m_isBeingDestroyed;
    }

    /**
     * Shows if the block can be walked through.
     * @return true if a player with ghost effect can pass through. Otherwise
     * false.
     */
    public boolean isPhaseable() {
        return true;
    }

    /**
     * Shows if the block can be eaten.
     * @return true if the block can be eaten. Otherwise false.
     */
    public boolean isEatable() {
        return false;
    }

    /**
     * Shows if the block can be destroyed.
     * @return true if the block can be destroyed. Otherwise false.
     */
    public boolean isDestructible() {
        return true;
    }

    /**
     * Shows if the block stops explosions.
     * @return true if the block stops explosions. Otherwise false.
     */
    public boolean canBlockExplosions() {
        return !m_isBeingDestroyed;
    }

    /**
     * Shows if the block can be replaced by another.
     * @return true if it can be replaced. false otherwise.
     */
    public boolean isReplaceable() {
        return true;
    }

    /**
     * Gets called, whenever an explosion (of any kind) hits the block.
     */
    public final void explode() {
        if (isDestructible()) {
            m_isBeingDestroyed = true;
            m_explosionAnimation.start();
        }
        onExplode();
    }

    /**
     * Updates the Block.
     */
    public final void update() {
        if (m_isBeingDestroyed && m_explosionAnimation.isDone()) {
            destroy();
        }
        onUpdate();
    }

    /**
     * Destroys this block.
     */
    protected final void destroy() {
        m_isDestroyed = true;
        onDestroyed();
    }

    protected void onUpdate() {
        // Do nothing.
    }

    protected void onExplode() {
        // Do nothing.
    }

    protected void onDestroyed() {
        // Do nothing.
    }

    /**
     * Returns the next sprite to be drawn.
     * @return the next sprite in the blocks animation.
     */
    public BufferedImage getSpriteToDraw() {
        return getCurrentAnimation().getSpriteToDraw();
    }

    public boolean isDestroyed() {
        return m_isDestroyed;
    }

    public BlockType getType() {
        return m_type;
    }

    private void loadSprites() {
        m_idleAnimation = createAnimation("Idle");
        m_explosionAnimation = createAnimation("Exploding");
        m_explosionAnimation.setSingleAnimation();
        m_explosionAnimation.setAnimationDuration(EXPLOSION_TIME);
    }

    protected Animation getCurrentAnimation() {
        if (!m_isBeingDestroyed) {
            return m_idleAnimation;
        }
        else {
            return m_explosionAnimation;
        }
    }

    protected final Animation createAnimation(String animationName) {
        String folderName = m_type.name();
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        int i = 0;
        InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/" + animationName + "_" + i + ".png");
        try {
            while (fileInputStream != null) {
                sprites.add(ImageIO.read(fileInputStream));
                i++;
                fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/" + animationName + "_" + i + ".png");
            }
        }
        catch (IOException e) {
            Logger.getLogger(BlockAbs.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        Animation Animation = new Animation(sprites);
        return Animation;
    }

    protected final void setExplosionDuration(long explosionTime) {
        m_explosionAnimation.setAnimationDuration(explosionTime);
    }

    private Animation m_idleAnimation;
    private Animation m_explosionAnimation;
    private boolean m_isBeingDestroyed;
    private boolean m_isDestroyed;
    private final BlockType m_type;

    private static final long EXPLOSION_TIME = 500 * 1000 * 1000; // Explosion animation time in nanoseconds.
}