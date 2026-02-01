package ui_Impl.MenuActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public class SelectButtonPressed extends MenuInputChangedAbs {

    public SelectButtonPressed(MenuInput menuInput) {
        super(menuInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_menuInput.isSelectPressed = true;
    }
}