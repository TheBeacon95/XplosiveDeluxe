package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.KillPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.MonsterType;

/**
 *
 * @author Yanick
 */
public final class Phantom extends MonsterAbs {

    public Phantom(Coordinates position) {
        super(position, MonsterType.Phantom);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
    }

    @Override
    protected int getSpeed() {
        return 1;
    }
}