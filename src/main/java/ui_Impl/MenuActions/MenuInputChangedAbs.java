package ui_Impl.MenuActions;

import javax.swing.AbstractAction;
import ui_Interfaces.MenuInput;

/**
 *
 * @author Yanick
 */
public abstract class MenuInputChangedAbs extends AbstractAction {

    public MenuInputChangedAbs(MenuInput menuInput) {
        m_menuInput = menuInput;
    }

    protected final MenuInput m_menuInput;
}