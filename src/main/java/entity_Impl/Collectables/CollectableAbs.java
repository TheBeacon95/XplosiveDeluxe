package entity_Impl.Collectables;

import common.*;
import entity_Impl.*;
import entity_Interfaces.PlayerIfc;

/**
 * Abstraction for all items, power-ups and effectors.
 *
 * @author Yanick
 */
public abstract class CollectableAbs extends StillEntityAbs {

    public CollectableAbs(Coordinates position) {
        super(position);
        m_deathDuration = 0;
    }

    /**
     * Gets called when a player touches the Collectable.
     *
     * @param collector the player that touched the Collectable.
     */
    public abstract void collect(PlayerIfc collector);

    protected Animation m_defaultAnimation;
}