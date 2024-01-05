package entity_Impl.Monsters.Behaviors.ExplosionBehaviors;

import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.ExplosionIfc;

/**
 *
 * @author Yanick
 */
public class DieBehavior implements ExplosionBehaviorIfc {

    @Override
    public void onExplode(MonsterAbs monster, ExplosionIfc explosion) {
        monster.kill();
    }
    
}