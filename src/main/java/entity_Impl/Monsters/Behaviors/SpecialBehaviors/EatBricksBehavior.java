package entity_Impl.Monsters.Behaviors.SpecialBehaviors;

import common.ServiceManager;
import entity_Impl.Monsters.MonsterAbs;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class EatBricksBehavior implements SpecialBehaviorIfc {

    @Override
    public void start() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
    }

    @Override
    public void perform(MonsterAbs monster) {
        if (!m_movementService.isBetweenCells(monster.getGlobalPosition())) {
            if (m_stageManagementService.tryEatBlock(monster.getGridPosition())) {
                monster.stall(2 * 1000 * 1000 * 1000);
            }
        }
    }
    
    private MovementServiceIfc m_movementService;
    private StageManagementServiceIfc m_stageManagementService;
}
