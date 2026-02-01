package ui_Impl.MenuActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public class LeftButtonReleased extends MenuInputChangedAbs {

    public LeftButtonReleased(MenuInput menuInput) {
        super(menuInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_menuInput.isLeftPressed = false;
    }
}