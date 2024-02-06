package entity_Impl.Monsters.Behaviors.SpecialBehaviors;

import common.ServiceManager;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;
import java.util.Random;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class PlaceCobwebsBehavior extends SpecialBehaviorAbs {

    @Override
    public void start() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_random = new Random();
    }

    @Override
    public void perform(MonsterAbs monster) {
        if (!m_movementService.isBetweenCells(monster.getGlobalPosition()) && m_random.nextBoolean()) {
            if (!m_entityManagementService.isCobwebHere(monster.getGridPosition()) && m_stageManagementService.tryPlaceCobweb(monster.getGridPosition())) {
                monster.stall(1 * 1000 * 1000 * 1000);
            }
        }
    }

    private Random m_random;
    private MovementServiceIfc m_movementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private StageManagementServiceIfc m_stageManagementService;
}
