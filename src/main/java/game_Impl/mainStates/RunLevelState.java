package game_Impl.mainStates;

import common.*;
import common.stateMachine.*;
import entity_Interfaces.*;
import game_Impl.mainStates.GamePanels.StagePanel;
import game_Impl.mainStates.Level.LevelStateMachine;
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
        m_statemachine = new LevelStateMachine();
    }

    @Override
    public void enter() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
        m_displayService.setPanel(new StagePanel());

////        RandomLevelGenerator generator = new RandomLevelGenerator();
////        generator.setBlockDensity(6);
////        generator.setMonsterDensity(1);
////        generator.addMonsterType(MonsterType.Bacteria);
////        m_stageManagementService.setStage(generator.generateRandomLevel());
//        Level level = new Level();
//        level.setupTestLevel();
//        m_stageManagementService.setStage(level);
//
        m_statemachine.start();
//        m_entityManagementService.startEntities();
    }

    @Override
    public void run() {
        /* There should be an internal State Machine:
         * - Level warm up state: The level goes from black to being there.
         *      When it's done it goes to the play level state.
         *
         * - Play level state: The players and monsters etc. start moving.
         *      This state can be exited to the pause state or to the win state or lose state (for combative it's a win state)
         *      The order of things is:
         *      1. Move all entities.
         *      2. Check all collisions.
         *      3. Perform all actions.
         *      4. Redraw.
         *
         * - Ppause state: Shows the players a menu screen.
         *      The menu can be left by either quitting or resuming.
         *
         * - Win/lose states: self explanarory.
         */
        m_statemachine.run();
//        if (m_stageManagementService.isReady()) {
//            m_stageManagementService.updateStage();
//            m_entityManagementService.updateEntities();
//
//            m_displayService.draw();
//        }
    }

    public static final String STATE_NAME = "RunLevelState";

    private final LevelStateMachine m_statemachine;

    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
}
