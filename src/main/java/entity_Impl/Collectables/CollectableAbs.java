package entity_Impl.Collectables;

import common.*;
import entity_Impl.*;
import entity_Interfaces.*;

/**
 * Abstraction for all items, power-ups and effectors.
 *
 * @author Yanick
 */
public abstract class CollectableAbs extends StillEntityAbs {

    public CollectableAbs(Coordinates position, CollectableType type) {
        super(position);
        m_deathDuration = 0;
        m_type = type;
        setDefaultAnimation("Sprites/Collectables/" + m_type.name());
        m_deathAnimation = m_defaultAnimation.copy();
        m_creationTime = System.nanoTime();
    }

    /**
     * Gets called when a player touches the Collectable.
     * @param collector the player that touched the Collectable.
     */
    public abstract void collect(PlayerIfc collector);

    @Override
    protected final boolean canBeKilled() {
        return true;
    }

    @Override
    public void explode(ExplosionIfc explosion) {
        if (m_creationTime + INVINCIBILITY_FRAMES < System.nanoTime()) {
            die();
        }
    }

    @Override
    public void collide(EntityAbs otherEntity) {
        if (otherEntity instanceof PlayerIfc player) {
            collect(player);
            die();
        }
    }

    public CollectableType getType() {
        return m_type;
    }

    protected final CollectableType m_type;
    private final long m_creationTime;
    private final static long INVINCIBILITY_FRAMES = 250 * 1000 * 1000;
}
