package game_Impl.mainStates.Level;

import common.stateMachine.*;
import game_Impl.mainStates.Level.LevelStates.PlayLevelState;
import game_Impl.mainStates.Level.LevelStates.WarmupState;

/**
 *
 * @author Yanick
 */
public class LevelStateMachine extends StateMachineAbs {

    public LevelStateMachine() {
        // Initialise states
        m_warmupState = new WarmupState();
        m_playLevelState = new PlayLevelState();
//        m_pauseState = new PauseState();
//        m_winState = new WinState();

        // Initialise transitions
        m_warmupState.AddTransition(new TransitionAbs(m_playLevelState) {
            @Override
            public boolean isExitConditionSet() {
                return ((WarmupState)m_warmupState).hasTimerElapsed();
            }
        });
    }

    @Override
    public void onBeforeStart() {
        setInitialState(m_warmupState);
    }

    private final StateAbs m_warmupState;
    private final StateAbs m_playLevelState;
//    private final StateAbs m_pauseState;
//    private final StateAbs m_winState;
}