package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class SpeedPlus extends CollectableAbs {

    public SpeedPlus(Coordinates position) {
        super(position, CollectableType.SpeedPlus);
    }

    @Override
    public void collect(PlayerIfc collector) {
        collector.increaseSpeed();
    }
}
