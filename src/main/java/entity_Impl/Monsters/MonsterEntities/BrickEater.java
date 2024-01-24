package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.BrickEaterMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Impl.Monsters.Behaviors.SpecialBehaviors.EatBricksBehavior;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class BrickEater extends MonsterAbs {

    public BrickEater(Coordinates position) {
        super(position, MonsterType.BrickEater);
        setMovementBehavior(new BrickEaterMovement());
        setExplosionBehavior(new DieBehavior());
        addSpecialBehavior(new EatBricksBehavior());
    }

    @Override
    protected Speed getSpeed() {
        return Speed.SlowMonster;
    }
}