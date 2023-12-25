package game_Impl;

import common.stateMachine.StateAbs;
import common.stateMachine.StateMachineAbs;
import common.stateMachine.TransitionAbs;
import game_Impl.gameStates.*;

/**
 *
 * @author Yanick
 */
public class GameStateMachine extends StateMachineAbs {
    
    public GameStateMachine() {
        // Initialise states
        m_showMenuState = new ShowMenuState();
        m_loadLevelState = new LoadLevelState();
        m_runLevelState = new RunLevelState();
        
        // Initialise transitions
//        m_showMenuState.AddTransition(new TransitionAbs(m_loadLevelState) {
//            @Override
//            public void transitionToNextState() {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//            @Override
//            public boolean isExitConditionSet() {
//                return false;
//            }
//        });
    }
    
    @Override
    protected void onBeforeStart() {
//        setInitialState(m_showMenuState); // TODO: Start with the menu when it's implemented.
        setInitialState(m_runLevelState);
    }
    
    private StateAbs m_showMenuState;
    private StateAbs m_loadLevelState;
    private StateAbs m_runLevelState;
}
