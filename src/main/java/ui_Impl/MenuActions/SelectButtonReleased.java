package ui_Impl.MenuActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public class SelectButtonReleased extends MenuInputChangedAbs {

    public SelectButtonReleased(MenuInput menuInput) {
        super(menuInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_menuInput.isSelectPressed = false;
    }
}