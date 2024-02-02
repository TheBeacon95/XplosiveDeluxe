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
        m_deathAnimation = new Animation(m_defaultAnimation);
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
        die();
    }
    
    @Override
    public void collide(EntityAbs otherEntity) {
        if (otherEntity instanceof PlayerIfc player) {
            collect(player);
            die();
        }
    }

    protected final CollectableType m_type;
}