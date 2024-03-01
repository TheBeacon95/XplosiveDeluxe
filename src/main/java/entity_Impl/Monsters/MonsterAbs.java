package entity_Impl.Monsters;

import common.*;
import entity_Impl.*;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.CollisionBehaviorAbs;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.ExplosionBehaviorIfc;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.MovementBehaviorAbs;
import entity_Impl.Monsters.Behaviors.SpecialBehaviors.SpecialBehaviorAbs;
import entity_Interfaces.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yanick
 */
public abstract class MonsterAbs extends MovingEntityAbs implements MonsterIfc {

    protected MonsterAbs(Coordinates position, MonsterType monsterType) {
        super(position, "Sprites/Monsters/" + monsterType.name() + "/Skin_0/");
        m_specialBehaviors = new ArrayList<>();
        m_collisionBehaviors = new ArrayList<>();
    }

    @Override
    public final void start() {
        // Todo: implement.
        for (SpecialBehaviorAbs specialBehavior: m_specialBehaviors) {
            specialBehavior.start();
        }
    }

    @Override
    public final void explode(ExplosionIfc explosion) {
        m_explosionBehavior.onExplode(this, explosion);
    }

    @Override
    protected boolean canBeKilled() {
        return true;
    }

    @Override
    public final void collide(EntityAbs otherEntity) {
        if (!isStalled()) {
            for (CollisionBehaviorAbs collisionBehavior: m_collisionBehaviors) {
                collisionBehavior.collide(otherEntity);
            }
        }
    }

    @Override
    protected Direction getMovementDirection() {
        Direction movementDirection = m_movementBehavior.getNextMovementDirection(this);
        if (movementDirection == null) {
            return Direction.NoDirection;
        }
        else {
            return movementDirection;
        }
    }

    @Override
    public final Direction getDisplayDirection() {
        return m_direction == Direction.NoDirection ? Direction.Down : m_direction;
    }

    @Override
    protected final void act() {
        for (SpecialBehaviorAbs specialBehavior: m_specialBehaviors) {
            specialBehavior.perform(this);
        }
    }

    protected void setMovementBehavior(MovementBehaviorAbs movementBehavior) {
        m_movementBehavior = movementBehavior;
    }

    protected void addSpecialBehavior(SpecialBehaviorAbs specialBehavior) {
        m_specialBehaviors.add(specialBehavior);
    }

    protected void setCollisionBehavior(CollisionBehaviorAbs collisionBehavior) {
        m_collisionBehaviors.add(collisionBehavior);
    }

    protected void setExplosionBehavior(ExplosionBehaviorIfc explosionBehavior) {
        m_explosionBehavior = explosionBehavior;
    }

    protected MovementBehaviorAbs m_movementBehavior;
    protected List<SpecialBehaviorAbs> m_specialBehaviors;
    protected List<CollisionBehaviorAbs> m_collisionBehaviors;
    protected ExplosionBehaviorIfc m_explosionBehavior;
}