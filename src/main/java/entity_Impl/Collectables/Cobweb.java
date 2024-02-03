package entity_Impl.Collectables;

import common.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Cobweb extends CollectableAbs {

    public Cobweb(Coordinates position) {
        super(position, CollectableType.Cobweb);
    }

    @Override
    public void collect(PlayerIfc collector) {
        collector.stall(STALL_DURATION);
    }

    private static final long STALL_DURATION = 1 * 1000 * 1000 * 1000;
}
