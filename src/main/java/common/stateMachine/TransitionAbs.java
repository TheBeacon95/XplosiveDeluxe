package common.stateMachine;

/**
 * Transition from one state to the next.
 *
 * @author Yanick
 */
public abstract class TransitionAbs {

    public final StateAbs nextState() {
        return m_nextState;
    }

    private TransitionAbs() {
        m_nextState = null;
    }
    
    public TransitionAbs(StateAbs nextState) {
        m_nextState = nextState;
    }

    public void transitionToNextState() {
        // Do nothing.
    }
    
    public boolean isExitConditionSet() {
        return false;
    }

    public static TransitionAbs emptyTransition() {
        return new TransitionAbs() {};
    }

    private StateAbs m_nextState;
}
