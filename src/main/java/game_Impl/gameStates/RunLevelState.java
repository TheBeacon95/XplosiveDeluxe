package game_Impl.gameStates;

import common.ServiceManager;
import common.stateMachine.StateAbs;
import entity_Interfaces.*;
import level_Impl.RandomLevelGenerator;
import level_Interfaces.*;
import ui_Interfaces.*;

/**
 * This is the state in which the level is played.
 *
 * @author Yanick
 */
public class RunLevelState extends StateAbs {

    public RunLevelState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);

        RandomLevelGenerator generator = new RandomLevelGenerator();
        generator.setBlockDensity(5);
        generator.setMonsterDensity(0);
        generator.addMonsterType(MonsterType.BrickEater);
        m_stageManagementService.setStage(generator.generateRandomLevel());
        m_entityManagementService.startEntities();
    }

    @Override
    public void run() {
        if (m_stageManagementService.isReady()) {
            m_stageManagementService.updateStage();
            m_entityManagementService.updateEntities();

            m_displayService.draw();
        }
    }

    public static final String STATE_NAME = "RunLevelState";

    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
}
