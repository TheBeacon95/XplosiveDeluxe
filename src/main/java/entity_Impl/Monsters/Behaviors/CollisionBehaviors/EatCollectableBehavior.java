package entity_Impl.Monsters.Behaviors.CollisionBehaviors;

import entity_Impl.*;
import entity_Impl.Collectables.CollectableAbs;

/**
 *
 * @author Yanick
 */
public class EatCollectableBehavior extends CollisionBehaviorAbs {

    @Override
    public void collide(EntityAbs entity) {
        if (entity instanceof CollectableAbs) {
            entity.tryKill();
        }
    }
}