package game_Impl.mainStates.Level.LevelStates;

import common.ServiceManager;
import common.stateMachine.StateAbs;
import entity_Interfaces.EntityManagementServiceIfc;
import entity_Interfaces.EntityNames;
import level_Interfaces.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class PlayLevelState extends StateAbs {

    public PlayLevelState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
    }

    @Override
    public void run() {
        if (m_stageManagementService.isReady()) {
            m_stageManagementService.updateStage();
            m_entityManagementService.updateEntities();

            m_displayService.draw();
        }
    }

    private static final String STATE_NAME = "PlayLevelState";
    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
}
