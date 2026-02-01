package ui_Impl.MenuActions;

import java.awt.event.ActionEvent;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public class DownButtonPressed extends MenuInputChangedAbs {

    public DownButtonPressed(MenuInput menuInput) {
        super(menuInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m_menuInput.isDownPressed = true;
    }
}