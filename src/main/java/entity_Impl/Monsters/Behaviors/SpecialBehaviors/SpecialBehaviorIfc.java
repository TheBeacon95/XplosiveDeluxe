package entity_Impl.Monsters.Behaviors.SpecialBehaviors;

import entity_Impl.Monsters.MonsterAbs;

/**
 * Defines a common interface for all monsters that behave in a special way.
 *
 * @author Yanick
 */
public interface SpecialBehaviorIfc {

    /**
     * Starts the special behavior.
     */
    void start();
    
    /**
     * Performs the special behavior.
     *
     * @param monster the monster that has a special behavior.
     */
    void perform(MonsterAbs monster);
}
