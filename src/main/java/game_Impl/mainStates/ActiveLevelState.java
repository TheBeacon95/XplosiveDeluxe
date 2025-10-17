package game_Impl.mainStates;

import common.*;
import common.stateMachine.*;
import entity_Interfaces.*;
import game_Impl.mainStates.GamePanels.*;
import level_Interfaces.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class RunStageState extends StateAbs {

    public RunStageState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
        m_displayService.setPanel(new StagePanel());
    }

    @Override
    public void run() {
        if (m_stageManagementService.isReady()) {
            m_stageManagementService.updateStage();
            m_entityManagementService.updateEntities();

            m_displayService.draw();
        }
    }

    public boolean IsPauseRequested() {

    }

    public static final String STATE_NAME = "RunLevelState";

    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
    private PauseKeyHandler m_pauseInput;
}