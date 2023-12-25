package entity_Impl.Monsters.Behaviors;

import entity_Impl.Monsters.MonsterAbs;

/**
 * Defines a common interface for all monsters that behave in a special way.
 *
 * @author Yanick
 */
public interface SpecialBehaviorIfc {

    /**
     * Performs the special behavior.
     *
     * @param monster the monster that has a special behavior.
     */
    void perform(MonsterAbs monster);
}
