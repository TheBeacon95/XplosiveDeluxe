package entity_Impl.Monsters.Behaviors.SpecialBehaviors;

import entity_Impl.Monsters.MonsterAbs;

/**
 * Defines a common interface for all monsters that behave in a special way.
 *
 * @author Yanick
 */
public abstract class SpecialBehaviorAbs {

    /**
     * Starts the special behavior.
     */
    public abstract void start();
    
    /**
     * Performs the special behavior.
     *
     * @param monster the monster that has a special behavior.
     */
    public abstract void perform(MonsterAbs monster);
}
