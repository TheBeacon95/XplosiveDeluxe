package game_Impl.gameStates;

import level_Interfaces.LevelNames;
import level_Interfaces.StageManagementServiceIfc;
import common.ServiceManager;
import common.stateMachine.StateAbs;
import entity_Interfaces.EntityManagementServiceIfc;
import entity_Interfaces.EntityNames;
import ui_Interfaces.DisplayServiceIfc;
import ui_Interfaces.UiNames;

/**
 * This is the state in which the level is played.
 * @author Yanick
 */
public class RunLevelState extends StateAbs {
    
    public RunLevelState() {
        super(STATE_NAME);
    }
    
    @Override
    public void enter() {
        // TODO: initialise all services.
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
    }
    
    @Override
    public void run() {
        m_stageManagementService.updateStage();
        m_entityManagementService.updateEntities();
        
        m_displayService.draw();
    }
    
    public static final String STATE_NAME = "RunLevelState";
    
    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
}
