package entity_Impl.Monsters.Behaviors.CollisionBehaviors;

import entity_Impl.EntityAbs;

/**
 * Defines a common interface for all monsters when they collide with other entities.
 * @author Yanick
 */
public abstract class CollisionBehaviorAbs {
    /**
     * Gets called, when the monster collides with another entity.
     * @param entity the entity that collided with the monster.
     */
    public abstract void collide(EntityAbs entity);
}
