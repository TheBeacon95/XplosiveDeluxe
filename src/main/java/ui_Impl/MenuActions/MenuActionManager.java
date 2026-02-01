package ui_Impl.MenuActions;

import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class MenuActionManager {

    public MenuActionManager() {
        m_menuInput = new MenuInput();
        m_menuUpButtonPressed = new UpButtonPressed(m_menuInput);
        m_menuUpButtonReleased = new UpButtonReleased(m_menuInput);
        m_menuRightButtonPressed = new RightButtonPressed(m_menuInput);
        m_menuRightButtonReleased = new RightButtonReleased(m_menuInput);
        m_menuDownButtonPressed = new DownButtonPressed(m_menuInput);
        m_menuDownButtonReleased = new DownButtonReleased(m_menuInput);
        m_menuLeftButtonPressed = new LeftButtonPressed(m_menuInput);
        m_menuLeftButtonReleased = new LeftButtonReleased(m_menuInput);
        m_menuFireButtonPressed = new SelectButtonPressed(m_menuInput);
        m_menuFireButtonReleased = new SelectButtonReleased(m_menuInput);
    }

    public final MenuInput m_menuInput;
    public final UpButtonPressed m_menuUpButtonPressed;
    public final UpButtonReleased m_menuUpButtonReleased;
    public final RightButtonPressed m_menuRightButtonPressed;
    public final RightButtonReleased m_menuRightButtonReleased;
    public final DownButtonPressed m_menuDownButtonPressed;
    public final DownButtonReleased m_menuDownButtonReleased;
    public final LeftButtonPressed m_menuLeftButtonPressed;
    public final LeftButtonReleased m_menuLeftButtonReleased;
    public final SelectButtonPressed m_menuFireButtonPressed;
    public final SelectButtonReleased m_menuFireButtonReleased;
}