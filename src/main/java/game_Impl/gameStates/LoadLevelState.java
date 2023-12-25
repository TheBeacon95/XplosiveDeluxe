package game_Impl.gameStates;

import level_Interfaces.LevelManagementServiceIfc;
import level_Interfaces.LevelNames;
import common.ServiceManager;
import common.stateMachine.StateAbs;

/**
 * Loads a Level.
 * @author Yanick
 */
public class LoadLevelState extends StateAbs {
    
    public LoadLevelState() {
        super(STATE_NAME);
    }
    
    @Override
    public void enter() {
        m_threadRunner = new LoadLevelThreadRunner();
        m_threadRunner.start();
    }
    
    public final static String STATE_NAME = "LoadLevelState";
    private LoadLevelThreadRunner m_threadRunner;
    
    private static class LoadLevelThreadRunner implements Runnable {

        @Override
        public void run() {
            LevelManagementServiceIfc levelManagementService = (LevelManagementServiceIfc) ServiceManager.getService(LevelNames.Services.LevelManagementService);
            levelManagementService.loadLevel();
            m_isDoneRunning = true;
        }
        
        public void start() {
            m_loadLevelThread = new Thread(this);
            m_loadLevelThread.start();
        }
        
        public boolean isDoneRunning() {
            return m_isDoneRunning;
        }
        
        private boolean m_isDoneRunning;
        private Thread m_loadLevelThread;
    }
}