package game_Impl.mainStates;

import common.ServiceManager;
import common.stateMachine.StateAbs;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public abstract class UiStateAbs extends StateAbs {

    public UiStateAbs(String stateName) {
        super(stateName);
    }

    /**
     * Tells the state to set its self as the next panel to be drawn.
     */
    public final void setAsNextPanel() {
        ((DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService)).setPanel(getGamePanel());
    }

    protected abstract GamePanelAbs getGamePanel();
}