package ui_Impl.PlayerActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.PlayerInput;

/**
 *
 * @author Yanick
 */
public class RightButtonPressed extends PlayerInputChangedAbs {

    public RightButtonPressed(PlayerInput playerInput) {
        super(playerInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_playerInput.isRightPressed = true;
    }

}