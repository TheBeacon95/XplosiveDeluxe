package game_Impl.mainStates;

import common.ServiceManager;
import common.stateMachine.StateAbs;
import level_Interfaces.*;
import ui_Interfaces.*;

/**
 * Loads a Level.
 * @author Yanick
 */
public class PrepareStageState extends StateAbs {

    public PrepareStageState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_threadRunner = new LoadLevelThreadRunner();
        m_threadRunner.start();
    }

        public boolean isLoadingDone() {
            return m_threadRunner.m_isLoadingDone;
        }

    public final static String STATE_NAME = "PrepareStageState";
    private LoadLevelThreadRunner m_threadRunner;

    private static class LoadLevelThreadRunner implements Runnable {

        @Override
        public void run() {
            LevelManagementServiceIfc levelManagementService = (LevelManagementServiceIfc) ServiceManager.getService(LevelNames.Services.LevelManagementService);
            levelManagementService.loadLevel();
            ((InputManagementServiceIfc) ServiceManager.getService(UiNames.Services.InputManagementService)).loadPlayerInputs();
            m_isLoadingDone = true;
        }

        public void start() {
            m_loadLevelThread = new Thread(this);
            m_loadLevelThread.start();
        }

        private boolean m_isLoadingDone;
        private Thread m_loadLevelThread;
    }
}

/*


        if (m_isWarmup) {
            RandomLevelGenerator generator = new RandomLevelGenerator();
            generator.setBlockDensity(6);
            generator.setMonsterDensity(1);
            generator.addMonsterType(MonsterType.Bacteria);
            m_stageManagementService.setStage(generator.generateRandomLevel());
//            Level level = new Level();
//            level.setupTestLevel();
//            m_stageManagementService.setStage(level);

            m_entityManagementService.startEntities();
        }
 */