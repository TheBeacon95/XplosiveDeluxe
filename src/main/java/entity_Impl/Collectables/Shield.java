package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Shield extends CollectableAbs {

    public Shield(Coordinates position) {
        super(position, CollectableType.Shield);
    }

    @Override
    public void collect(PlayerIfc collector) {
        // Todo: give player a default effect time.
        collector.effect(PlayerEffect.Shield, 5l * 1000 * 1000 * 1000);
    }
}
