package level_Impl;

import level_Interfaces.BlockType;
import common.Animation;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yanick
 */
public abstract class BlockAbs {

    public BlockAbs(BlockType type) {
        m_type = type;
        loadSprites(type.name());
    }

    /**
     * Shows if the block can be walked over.
     *
     * @return true if the block can be walked over. Otherwise false.
     */
    public boolean isWalkable() {
        return m_isBeingDestroyed;
    }

    /**
     * Shows if the block can be walked through.
     *
     * @return true if a player with ghost effect can pass through. Otherwise
     * false.
     */
    public boolean isPhaseable() {
        return true;
    }

    /**
     * Shows if the block can be eaten.
     *
     * @return true if the block can be eaten. Otherwise false.
     */
    public boolean isEatable() {
        return false;
    }

    /**
     * Shows if the block stops explosions.
     *
     * @return true if the block stops explosions. Otherwise false.
     */
    public boolean canBlockExplosions() {
        return !m_isBeingDestroyed;
    }

    /**
     * Gets called, whenever an explosion (of any kind) hits the block.
     */
    public void explode() {
        m_isBeingDestroyed = true;
        m_explosionStartTime = System.nanoTime();
        m_explosionAnimation.setSingleAnimation();
        m_explosionAnimation.setSpriteDuration(40);
        onExplode();
    }

    /**
     * Updates the Block.
     */
    public final void update() {
        if (m_isBeingDestroyed) {
            long currentTime = System.nanoTime();
            if (currentTime >= m_explosionStartTime + EXPLOSION_TIME) {
                destroy();
            }
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
     *
     * @return the next sprite in the blocks animation.
     */
    public BufferedImage getSpriteToDraw() {
        if (!m_isBeingDestroyed) {
            return m_idleAnimation.getSpriteToDraw();
        }
        else {
            return m_explosionAnimation.getSpriteToDraw();
        }
    }

    public boolean isDestroyed() {
        return m_isDestroyed;
    }
    
    public BlockType getType() {
        return m_type;
    }

    private void loadSprites(String folderName) {
        m_idleAnimation = createIdleAnimation(folderName);
        m_explosionAnimation = createExplosionAnimation(folderName);
    }
    
    private Animation createIdleAnimation(String folderName) {
        ArrayList<BufferedImage> idleSprites = new ArrayList<>();
        int i = 0;
        InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/Idle_" + i + ".png");
        try {
            while (fileInputStream != null) {
                idleSprites.add(ImageIO.read(fileInputStream));
                i++;
                fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/Idle_" + i + ".png");
            }
        }
        catch (Exception e) {
            Logger.getLogger(BlockAbs.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return new Animation(idleSprites);
    }
    
    private Animation createExplosionAnimation(String folderName) {
        ArrayList<BufferedImage> explosionSprites = new ArrayList<>();
        int i = 0;
        InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/Exploding_" + i + ".png");
        try {
            while (fileInputStream != null) {
                explosionSprites.add(ImageIO.read(fileInputStream));
                i++;
                fileInputStream = getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/Exploding_" + i + ".png");
            }
        }
        catch (Exception e) {
            Logger.getLogger(BlockAbs.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return new Animation(explosionSprites);
    }

    private Animation m_idleAnimation;
    private Animation m_explosionAnimation;
    private boolean m_isBeingDestroyed;
    private boolean m_isDestroyed;
    private long m_explosionStartTime;
    private final BlockType m_type;

    private static final long EXPLOSION_TIME = 250 * 1000 * 1000; // Explosion animation time in nanoseconds.
}