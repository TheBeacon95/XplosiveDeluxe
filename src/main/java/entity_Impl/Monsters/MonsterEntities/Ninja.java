package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.KillPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.HostileMovement;
import entity_Impl.Monsters.Behaviors.SpecialBehaviors.RandomStallingBehavior;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Ninja extends MonsterAbs {
    public Ninja(Coordinates position) {
        super(position, MonsterType.Ninja);
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
        addSpecialBehavior(new RandomStallingBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.NormalMonster;
    }
}
