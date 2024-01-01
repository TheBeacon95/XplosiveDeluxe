package entity_Impl.Monsters;

import entity_Impl.Monsters.Behaviors.CollisionBehaviorIfc;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviorIfc;
import entity_Impl.Monsters.Behaviors.MovementBehaviorIfc;
import entity_Impl.Monsters.Behaviors.SpecialBehaviorIfc;
import common.Coordinates;
import common.Direction;
import entity_Impl.MovingEntityAbs;
import entity_Interfaces.ExplosionIfc;
import entity_Interfaces.MonsterType;
import java.util.List;

/**
 *
 * @author Yanick
 */
public abstract class MonsterAbs extends MovingEntityAbs {

    protected MonsterAbs(Coordinates position, MonsterType monsterType) {
        super(position, "Sprites/Monsters/" + monsterType.name() + "/Skin_0/");
    }

    public final void Start() {
        // Todo: implement.
    }

    @Override
    protected final void onUpdate() {
        // Todo: do something like this
//        for (EntityAbs entity : getCollisions()) {
//            m_collisionBehavior.react(entity);
//        }
//        for (SpecialBehaviorIfc specialBehavior : m_specialBehaviors) {
//            specialBehavior.perform(this);
//        }
    }
    
    @Override
    public final void explode(ExplosionIfc explosion) {
        m_explosionBehavior.onExplode(this, explosion);
    }

    @Override
    protected final Direction getMovementDirection() {
        return m_movementBehavior.getNextMovementDirection(this);
    }
    
    protected void setMovementBehavior(MovementBehaviorIfc movementBehavior) {
        m_movementBehavior = movementBehavior;
    }
    
    protected void addSpecialBehavior(SpecialBehaviorIfc specialBehavior) {
        m_specialBehaviors.add(specialBehavior);
    }
    
    protected void setCollisionBehavior(CollisionBehaviorIfc collisionBehavior) {
        m_collisionBehavior = collisionBehavior;
    }
    
    protected void setExplosionBehavior(ExplosionBehaviorIfc explosionBehavior) {
        m_explosionBehavior = explosionBehavior;
    }
    
    protected MovementBehaviorIfc m_movementBehavior;
    protected List<SpecialBehaviorIfc> m_specialBehaviors;
    protected CollisionBehaviorIfc m_collisionBehavior;
    protected ExplosionBehaviorIfc m_explosionBehavior;
}