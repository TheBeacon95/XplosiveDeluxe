package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.MonsterType;

/**
 *
 * @author Yanick
 */
public final class Ghost extends MonsterAbs {

    public Ghost(Coordinates position) {
        super(position, MonsterType.Ghost);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
//        setCollisionBehavior(new KillPlayerBehavior());
//        setExplosionBehavior(new DeathOnExplodeBehavior());
    }

    @Override
    protected int getSpeed() {
        return 1;
    }
}