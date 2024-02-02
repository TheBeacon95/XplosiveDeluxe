package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class BombPlus extends CollectableAbs {

    public BombPlus(Coordinates position) {
        super(position, CollectableType.BombPlus);
    }

    @Override
    public void collect(PlayerIfc collector) {
        collector.IncreaseBombCount();
    }
}
