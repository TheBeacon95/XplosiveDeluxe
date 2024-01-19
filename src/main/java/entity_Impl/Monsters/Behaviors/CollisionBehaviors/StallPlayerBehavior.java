package entity_Impl.Monsters.Behaviors.CollisionBehaviors;

import entity_Impl.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class StallPlayerBehavior implements CollisionBehaviorIfc {

    @Override
    public void collide(EntityAbs entity) {
        if (entity instanceof PlayerIfc player) {
            player.stall(STALL_DURATION);
        }
    }
    
    private static final long STALL_DURATION = 1 * 1000 * 1000 * 1000;
}