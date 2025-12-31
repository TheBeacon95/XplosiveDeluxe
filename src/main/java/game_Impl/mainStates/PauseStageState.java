package game_Impl.mainStates;

import common.stateMachine.StateAbs;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Yanick
 */
public class PauseStageState extends StateAbs {

    public PauseStageState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {

    }

    @Override
    public void run() {

    }

    @Override
    public void exit() {

    }

    public boolean isResumeClicked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isResetClicked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isExitClicked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static final String STATE_NAME = "PauseStageState";

    private final Action m_upAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };

    private final Action m_downAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
}
