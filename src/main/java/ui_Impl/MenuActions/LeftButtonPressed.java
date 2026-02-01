package ui_Impl.MenuActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public class LeftButtonPressed extends MenuInputChangedAbs {

    public LeftButtonPressed(MenuInput menuInput) {
        super(menuInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_menuInput.isLeftPressed = true;
    }
}