package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.KillPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class Ghost extends MonsterAbs {

    public Ghost(Coordinates position) {
        super(position, MonsterType.Ghost);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.NormalMonster;
    }
}