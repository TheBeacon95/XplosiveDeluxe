package entity_Impl.Monsters;

import entity_Impl.Monsters.Behaviors.CollisionBehaviors.CollisionBehaviorIfc;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.ExplosionBehaviorIfc;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.MovementBehaviorIfc;
import entity_Impl.Monsters.Behaviors.SpecialBehaviors.SpecialBehaviorIfc;
import common.*;
import entity_Impl.*;
import entity_Interfaces.*;
import java.util.List;

/**
 *
 * @author Yanick
 */
public abstract class MonsterAbs extends MovingEntityAbs implements MonsterIfc {

    protected MonsterAbs(Coordinates position, MonsterType monsterType) {
        super(position, "Sprites/Monsters/" + monsterType.name() + "/Skin_0/");
    }

    public final void Start() {
        // Todo: implement.
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
        m_collisionBehavior.collide(otherEntity);
    }

    @Override
    protected final Direction getMovementDirection() {
        return m_movementBehavior.getNextMovementDirection(this);
    }

    @Override
    protected final Direction getDisplayDirection() {
        return m_direction == Direction.NoDirection ? Direction.Down : m_direction;
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