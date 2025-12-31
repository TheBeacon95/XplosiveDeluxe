package game_Impl;

import ui_Interfaces.MenuInput;
import java.awt.event.KeyEvent;
import ui_Interfaces.KeyHandlerAbs;

/**
 *
 * @author Yanick
 */
public class MenuKeyHandler extends KeyHandlerAbs {

    public MenuInput getCurrentInput() {
        MenuInput input = MenuInput.None;
        if (!m_wasUpPreviouslyPressed && m_isUpCurrentlyPressed) {
            input = MenuInput.Up;
            m_wasDownPreviouslyPressed = true;
        }
        else if (!m_wasDownPreviouslyPressed && m_isDownCurrentlyPressed) {
            input = MenuInput.Down;
            m_wasDownPreviouslyPressed = true;
        }
        else if (!m_wasSelectPreviouslyPressed && m_isSelectCurrentlyPressed) {
            input = MenuInput.Select;
            m_wasSelectPreviouslyPressed = true;
        }
        return ;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        MenuInput input = convertKeyCodeToInput(e.getKeyCode());
        switch (input) {
            case Up -> m_isUpCurrentlyPressed = true;
            case Down -> m_isDownCurrentlyPressed = true;
            case Select -> m_isSelectCurrentlyPressed = true;
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        MenuInput input = convertKeyCodeToInput(e.getKeyCode());
        switch (input) {
            case Up -> {
                m_isUpCurrentlyPressed = false;
                m_wasUpPreviouslyPressed = false;
            }
            case Down -> {
                m_isDownCurrentlyPressed = false;
                m_wasDownPreviouslyPressed = false;
            }
            case Select -> {
                m_isSelectCurrentlyPressed = false;
                m_wasSelectPreviouslyPressed = false;
            }
            default -> {}
        }
    }

    private MenuInput convertKeyCodeToInput(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.VK_UP -> MenuInput.Up;
            case KeyEvent.VK_DOWN -> MenuInput.Down;
            case KeyEvent.VK_ENTER -> MenuInput.Select;
            default -> MenuInput.None;
        };
    }

    private boolean m_isUpCurrentlyPressed;
    private boolean m_isDownCurrentlyPressed;
    private boolean m_isSelectCurrentlyPressed;
    private boolean m_wasUpPreviouslyPressed;
    private boolean m_wasDownPreviouslyPressed;
    private boolean m_wasSelectPreviouslyPressed;
}