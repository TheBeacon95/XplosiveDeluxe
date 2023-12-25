package entity_Impl.Monsters.Behaviors;

import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.ExplosionIfc;

/**
 * Provides a common interface to determine what happens when a monster explodes.
 * @author Yanick
 */
public interface ExplosionBehaviorIfc {
    /**
     * Gets called, when a monster touches an explosion.
     * @param monster the monster that touched the explosion.
     * @param explosion the explosion the monster has touched.
     */
    void onExplode(MonsterAbs monster, ExplosionIfc explosion);
}