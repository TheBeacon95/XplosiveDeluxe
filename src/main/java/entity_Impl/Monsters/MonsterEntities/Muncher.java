package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.StallPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.RandomMovement;
import entity_Impl.Monsters.*;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Muncher extends MonsterAbs {

    public Muncher(Coordinates position) {
        super(position, MonsterType.Muncher);
        setMovementBehavior(new RandomMovement());
        setCollisionBehavior(new StallPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.FastMonster;
    }
}