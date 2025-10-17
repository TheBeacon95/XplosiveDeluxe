package game_Impl.mainStates.Level;

import common.*;
import common.stateMachine.*;
import game_Impl.mainStates.Level.LevelStates.PlayLevelState;
import game_Impl.mainStates.ScreenFadeState;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class LevelStateMachine extends StateMachineAbs {

    public LevelStateMachine() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);

        // Initialise states
        m_warmupState = new ScreenFadeState(true);
        m_playLevelState = new PlayLevelState();
        m_cooldownState = new ScreenFadeState(false);
//        m_pauseState = new PauseState();
//        m_winState = new WinState();

        // Initialise transitions
        m_warmupState.addTransition(new TransitionAbs(m_playLevelState) {
            @Override
            public boolean isExitConditionSet() {
                return m_warmupState.isFadeDone();
            }
        });

        m_playLevelState.addTransition(new TransitionAbs(m_cooldownState) {
            @Override
            public boolean isExitConditionSet() {
                return m_stageManagementService.isOnePlayerLeft();
            }
        });

        m_cooldownState.addTransition(new TransitionAbs(m_warmupState) {
            @Override
            public boolean isExitConditionSet() {
                return m_cooldownState.isFadeDone();
            }
        });
    }

    @Override
    public void onBeforeStart() {
        setInitialState(m_warmupState);
    }

    private final ScreenFadeState m_warmupState;
    private final PlayLevelState m_playLevelState;
    private final ScreenFadeState m_cooldownState;
//    private final StateAbs m_pauseState;
//    private final StateAbs m_winState;

    private StageManagementServiceIfc m_stageManagementService;
}