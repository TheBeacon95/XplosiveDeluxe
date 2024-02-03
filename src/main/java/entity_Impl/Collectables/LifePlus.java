package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class LifePlus extends CollectableAbs {

    public LifePlus(Coordinates position) {
        super(position, CollectableType.LifePlus);
    }

    @Override
    public void collect(PlayerIfc collector) {
        collector.increaseLives();
    }
}
