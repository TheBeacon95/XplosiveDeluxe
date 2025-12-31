package entity_Impl.Players;

import ui_Interfaces.PlayerInput;
import common.*;
import java.awt.event.KeyEvent;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class PlayerKeyHandler extends KeyHandlerAbs {

    public Direction getPressedDirection() {
        // Todo: change this so that the newest direction is picked.
        Direction direction;
        if (m_isUpPressed) {
            direction = Direction.Up;
        }
        else if (m_isRightPressed) {
            direction = Direction.Right;
        }
        else if (m_isDownPressed) {
            direction = Direction.Down;
        }
        else if (m_isLeftPressed) {
            direction = Direction.Left;
        }
        else {
            direction = Direction.NoDirection;
        }
        return direction;
    }

    public boolean isFirePressed() {
        return m_isFirePressed;
    }

    public int upKey() {
        return m_upKey;
    }

    public int rightKey() {
        return m_rightKey;
    }

    public int downKey() {
        return m_downKey;
    }

    public int leftKey() {
        return m_leftKey;
    }

    public int fireKey() {
        return m_fireKey;
    }

    public void setUpKey(int upKey) {
        m_upKey = upKey;
    }

    public void setRightKey(int rightKey) {
        m_rightKey = rightKey;
    }

    public void setDownKey(int downKey) {
        m_downKey = downKey;
    }

    public void setLeftKey(int leftKey) {
        m_leftKey = leftKey;
    }

    public void setFireKey(int fireKey) {
        m_fireKey = fireKey;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlayerInput input = convertKeyCodeToInput(e.getKeyCode());
        switch (input) {
            case Up -> m_isUpPressed = true;
            case Right -> m_isRightPressed = true;
            case Down -> m_isDownPressed = true;
            case Left -> m_isLeftPressed = true;
            case Fire -> m_isFirePressed = true;
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerInput input = convertKeyCodeToInput(e.getKeyCode());
        switch (input) {
            case Up -> m_isUpPressed = false;
            case Right -> m_isRightPressed = false;
            case Down -> m_isDownPressed = false;
            case Left -> m_isLeftPressed = false;
            case Fire -> m_isFirePressed = false;
            default -> {}
        }
    }

    private PlayerInput convertKeyCodeToInput(int keyCode) {
        PlayerInput input = PlayerInput.None;
        if (keyCode == m_upKey) {
            input = PlayerInput.Up;
        }
        else if (keyCode == m_rightKey) {
            input = PlayerInput.Right;
        }
        else if (keyCode == m_downKey) {
            input = PlayerInput.Down;
        }
        else if (keyCode == m_leftKey) {
            input = PlayerInput.Left;
        }
        else if (keyCode == m_fireKey) {
            input = PlayerInput.Fire;
        }
        return input;
    }

    private transient boolean m_isUpPressed;
    private transient boolean m_isRightPressed;
    private transient boolean m_isDownPressed;
    private transient boolean m_isLeftPressed;
    private transient boolean m_isFirePressed;

//    private transient Direction m_lastPressedDirection = Direction.NoDirection;

    private int m_upKey = KeyEvent.VK_W;
    private int m_rightKey = KeyEvent.VK_D;
    private int m_downKey = KeyEvent.VK_S;
    private int m_leftKey = KeyEvent.VK_A;
    private int m_fireKey = KeyEvent.VK_SPACE;
}