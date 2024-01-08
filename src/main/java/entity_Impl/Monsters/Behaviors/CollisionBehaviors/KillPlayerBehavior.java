package entity_Impl.Monsters.Behaviors.CollisionBehaviors;

import entity_Impl.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class KillPlayerBehavior implements CollisionBehaviorIfc {

    @Override
    public void collide(EntityAbs entity) {
        if (entity instanceof PlayerIfc) {
            entity.tryKill();
        }
    }
}