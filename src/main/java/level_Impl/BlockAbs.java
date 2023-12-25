package level_Impl;

import common.Animation;
import entity_Interfaces.ExplosionIfc;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Yanick
 */
public abstract class BlockAbs {

    public BlockAbs(BlockType type) {
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
     *
     * @param explosion the explosion that hit the block
     */
    public void explode(ExplosionIfc explosion) {
        m_isBeingDestroyed = true;
        m_explosionStartTime = System.nanoTime();
    }

    /**
     * Updates the Block.
     */
    public void update() {
        if (m_isBeingDestroyed) {
            long currentTime = System.nanoTime();
            if (currentTime >= m_explosionStartTime + EXPLOSION_TIME) {
                m_isDestroyed = true;
            }
        }
    }

    /**
     * Returns the next sprite to be drawn.
     *
     * @return the next sprite in the blocks animation.
     */
    public BufferedImage getSpriteToDraw() {
        if (!m_isBeingDestroyed) {
            return m_idleSprite;
        }
        else {
            return m_explosionAnimation.getSpriteToDraw();
        }
    }

    public boolean isDestroyed() {
        return m_isDestroyed;
    }

    private final void loadSprites(String folderName) {
        // Todo implement: this gets the sprites and then creates an ExplosionAnimation and an Idle Sprite.
        // Then set setIdleSprite and setExplosionAnimation to private.
        try {
            m_idleSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/" + folderName + "_Idle.png"));

            m_explosionAnimation = createExplosionAnimation(folderName);
        }
        catch (Exception ex) {

        }
    }

//    private final void setIdleSprite(BufferedImage sprite) {
//        m_idleSprite = sprite;
//    }
//    
    private final Animation createExplosionAnimation(String folderName) {
        ArrayList<BufferedImage> explosionSprites = new ArrayList<>();
        int i = 0;
        try {
            BufferedImage explosionSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/" + folderName + "_Explosion_" + i + ".png"));
            while (explosionSprite != null) {
                i++;
                explosionSprites.add(explosionSprite);
                explosionSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Level/" + folderName + "/" + folderName + "_Explosion_" + i + ".png"));
            }
        }
        catch (Exception e) {
            
        }
        return new Animation(explosionSprites);
    }

    private BufferedImage m_idleSprite;
    private Animation m_explosionAnimation;
    private boolean m_isBeingDestroyed;
    private boolean m_isDestroyed;
    private long m_explosionStartTime;

    private static final long EXPLOSION_TIME = 1 * 1000 * 1000 * 1000; // Explosion animation time in nanoseconds.
}
