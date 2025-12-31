package game_Impl;

import common.stateMachine.*;
import game_Impl.mainStates.*;

/**
 *
 * @author Yanick
 */
public class MainStateMachine extends StateMachineAbs {

    public MainStateMachine() {
        // Initialize UI states
        m_mainMenuState = new MainMenuState();
        m_runStageState = new RunStageState();

        // Initialize other states
        m_prepareStageState = new PrepareStageState();
        m_pauseLevelState = new PauseStageState();

        // Initialize FadeStates
        m_fadeOutToPrepareState = ScreenFadeState.createFadeOutState();
        m_fadeInToRunStageState = ScreenFadeState.createFadeInState(m_runStageState);
        m_fadeOutToFadeInState = ScreenFadeState.createFadeOutState();
        m_fadeInToMainMenuState = ScreenFadeState.createFadeInState(m_mainMenuState);
//        m_gameOverState = new GameOverState();

        configureTransitions();
    }

    @Override
    protected void onBeforeStart() {
//        setInitialState(m_showMenuState); // TODO: Start with the menu when it's implemented.
        setInitialState(m_fadeInToMainMenuState);
    }

    private void configureTransitions() {
        m_mainMenuState.addTransition(new TransitionAbs(m_fadeOutToPrepareState) {
            @Override
            public boolean isExitConditionSet() {
                return m_mainMenuState.isStartGameSelected();
            }
        });

        m_fadeOutToPrepareState.addTransition(new TransitionAbs(m_prepareStageState) {
            @Override
            public boolean isExitConditionSet() {
                return m_fadeOutToPrepareState.isFadeDone();
            }
        });

        m_prepareStageState.addTransition(new TransitionAbs(m_fadeInToRunStageState) {
            @Override
            public boolean isExitConditionSet() {
                return m_prepareStageState.isLoadingDone();
            }
        });

        m_fadeInToRunStageState.addTransition(new TransitionAbs(m_runStageState) {
            @Override
            public boolean isExitConditionSet() {
                return m_fadeInToRunStageState.isFadeDone();
            }
        });

        m_runStageState.addTransition(new TransitionAbs(m_pauseLevelState) {
            @Override
            public boolean isExitConditionSet() {
                return m_runStageState.IsPauseRequested();
            }
        });
        m_runStageState.addTransition(new TransitionAbs(m_fadeOutToPrepareState) {
            @Override
            public boolean isExitConditionSet() {
                return m_runStageState.areWinOrLoseConditionsMet();
            }
        });
        m_runStageState.addTransition(new TransitionAbs(m_fadeOutToFadeInState) {
            @Override
            public boolean isExitConditionSet() {
                return m_runStageState.areGameOverConditionsMet();
            }
        });

        m_pauseLevelState.addTransition(new TransitionAbs(m_runStageState){
            @Override
            public boolean isExitConditionSet() {
                return m_pauseLevelState.isResumeClicked();
            }
        });
        m_pauseLevelState.addTransition(new TransitionAbs(m_fadeOutToPrepareState){
            @Override
            public boolean isExitConditionSet() {
                return m_pauseLevelState.isResetClicked();
            }
        });
        m_pauseLevelState.addTransition(new TransitionAbs(m_fadeOutToFadeInState){
            @Override
            public boolean isExitConditionSet() {
                return m_pauseLevelState.isExitClicked();
            }
        });

//        m_gameOverState.addTransition(new TransitionAbs(m_prepareStageState){
//            @Override
//            public boolean isExitConditionSet() {
//                return m_pauseLevelState.isContinueClicked();
//            }
//        });
//        m_gameOverState.addTransition(new TransitionAbs(m_mainMenuState){
//            @Override
//            public boolean isExitConditionSet() {
//                return m_pauseLevelState.isExitClicked();
//            }
//        });
    }

    private final MainMenuState m_mainMenuState;
    private final ScreenFadeState m_fadeOutToPrepareState;
    private final PrepareStageState m_prepareStageState;
    private final ScreenFadeState m_fadeInToRunStageState;
    private final RunStageState m_runStageState;
    private final PauseStageState m_pauseLevelState;
//    private final GameOverState m_gameOverState;
    private final ScreenFadeState m_fadeOutToFadeInState;
    private final ScreenFadeState m_fadeInToMainMenuState;
}
