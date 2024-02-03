package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class StrengthPlus extends CollectableAbs {

    public StrengthPlus(Coordinates position) {
        super(position, CollectableType.StrengthPlus);
    }

    @Override
    public void collect(PlayerIfc collector) {
        collector.increaseStrength();
    }
}
