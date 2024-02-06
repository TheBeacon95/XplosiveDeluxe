package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.KillPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.HostileMovement;
import entity_Impl.Monsters.Behaviors.SpecialBehaviors.PlaceCobwebsBehavior;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class Spider extends MonsterAbs {

    public Spider(Coordinates position) {
        super(position, MonsterType.Spider);
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
        addSpecialBehavior(new PlaceCobwebsBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.NormalMonster;
    }
}
