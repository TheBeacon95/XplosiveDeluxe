package common.stateMachine;

/**
 * Abstraction for all state machines.
 *
 * @author Yanick
 */
public abstract class StateMachineAbs {

    /**
     * Sets the state the state machine will start with.
     *
     * @param state the initial state
     */
    public void setInitialState(StateAbs state) {
        m_initialState = state;
    }

    /**
     * Starts the state machine.
     */
    public final void start() {
        onBeforeStart();
        m_currentState = m_initialState;
        m_currentState.enter();
    }

    /**
     * Runs the state machine for one iteration.
     */
    public void run() {
        TransitionAbs nextTransition = findTranistionWithSetExitCondition();
        if (nextTransition != null) {
            transitionToNextState(nextTransition);
        }
        else {
            m_currentState.run();
        }
    }
    
    protected void onBeforeStart() {
        // Do nothing.
    }

    private TransitionAbs findTranistionWithSetExitCondition() {
        TransitionAbs nextTransition = null;
        for (TransitionAbs transition : m_currentState.getTransitions()) {
            if (transition.isExitConditionSet()) {
                nextTransition = transition;
                break;
            }
        }
        return nextTransition;
    }

    private void transitionToNextState(TransitionAbs nextTransition) {
        m_currentState.exit();
        nextTransition.transitionToNextState();
        m_currentState = nextTransition.nextState();
        m_currentState.enter();
    }

    private StateAbs m_initialState;
    private StateAbs m_currentState;
}
