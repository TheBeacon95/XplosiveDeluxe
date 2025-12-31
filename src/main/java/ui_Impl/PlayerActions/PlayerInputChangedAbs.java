package ui_Impl.PlayerActions;

import javax.swing.AbstractAction;
import ui_Interfaces.PlayerInput;

/**
 *
 * @author Yanick
 */
public abstract class PlayerInputChangedAbs extends AbstractAction {

    public PlayerInputChangedAbs(PlayerInput playerInput) {
        m_playerInput = playerInput;
    }

    protected final PlayerInput m_playerInput;
}