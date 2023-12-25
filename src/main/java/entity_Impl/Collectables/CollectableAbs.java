package entity_Impl.Collectables;

import common.Animation;
import common.Coordinates;
import entity_Impl.EntityAbs;
import entity_Interfaces.PlayerIfc;
import java.awt.image.BufferedImage;

/**
 * Abstraction for all items, power-ups and effectors.
 *
 * @author Yanick
 */
public abstract class CollectableAbs extends EntityAbs {

    CollectableAbs(Coordinates position) {
        super(position);
    }

    /**
     * Gets called when a player touches the Collectable.
     *
     * @param collector the player that touched the Collectable.
     */
    public abstract void collect(PlayerIfc collector);

    @Override
    protected final BufferedImage getSpriteToDraw() {
        if (m_isDieing) {
            return m_deathAnimation.getSpriteToDraw();
        }
        else {
            return m_defaultAnimation.getSpriteToDraw();
        }
    }

    protected Animation m_defaultAnimation;
}