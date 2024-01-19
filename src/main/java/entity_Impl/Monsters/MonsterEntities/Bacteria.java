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
public final class Bacteria extends MonsterAbs {

    public Bacteria(Coordinates position) {
        super(position, MonsterType.Bacteria);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.DefaultPlayer;
    }
}