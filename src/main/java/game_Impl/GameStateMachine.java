package game_Impl;

import common.*;
import common.stateMachine.*;
import game_Impl.gameStates.*;
import level_Interfaces.*;

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
        
        PlayerWonState playerWonState = new PlayerWonState();
        
        // Initialise transitions
        m_runLevelState.AddTransition(new TransitionAbs(playerWonState) {
            @Override
            public boolean isExitConditionSet() {
                return ((StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService)).isOnePlayerLeft();
            }
        });
        
        playerWonState.AddTransition(new TransitionAbs(m_runLevelState){
            @Override
            public boolean isExitConditionSet() {
                return playerWonState.isDoneWaiting();
            }
        });
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
