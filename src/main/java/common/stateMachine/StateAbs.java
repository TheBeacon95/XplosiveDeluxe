package common.stateMachine;

import common.IdentifiableIfc;
import java.util.ArrayList;

/**
 *
 * @author Yanick
 */
public abstract class StateAbs implements IdentifiableIfc {
    public StateAbs(String stateName) {
        m_stateName = stateName;
        m_transitions = new ArrayList<>();
    }
    
    public void enter() {
        // Do nothing.
    }
    
    public void run() {
        // Do nothing.
    }
    
    public void exit() {
        // Do nothing.
    }
    
    @Override
    public String getId() {
        return m_stateName;
    }
    
    public final void AddTransition(TransitionAbs transition) {
        m_transitions.add(transition);
    }
    
//    public final TransitionAbs getTransition(StateAbs nextState) {
//        TransitionAbs transitionToUse = TransitionAbs.emptyTransition();
//        for (TransitionAbs transition : m_transitions) {
//            if (nextState.getId() == transition.nextState().getId()) {
//                transitionToUse = transition;
//                break;
//            }
//        }
//        return transitionToUse;
//    }
    
    public final ArrayList<TransitionAbs> getTransitions() {
        return new ArrayList<>(m_transitions);
    }
    
    protected final String m_stateName;
    
    private final ArrayList<TransitionAbs> m_transitions;
}