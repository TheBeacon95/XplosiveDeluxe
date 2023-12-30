package ui_Impl;

import common.Direction;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import ui_Interfaces.KeyHandlerIfc;

public class KeyHandler implements KeyHandlerIfc, Serializable {
    
    public KeyHandler() {
        int i = 0;
    }
    
    @Override
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

    @Override
    public boolean isFirePressed() {
        return m_isFirePressed;
    }
    
    @Override
    public int upKey() {
        return m_upKey;
    }
    
    @Override
    public int rightKey() {
        return m_rightKey;
    }
    
    @Override
    public int downKey() {
        return m_downKey;
    }
    
    @Override
    public int leftKey() {
        return m_leftKey;
    }
    
    @Override
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
        Input input = convertKeyCodeToInput(e.getKeyCode());
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
        Input input = convertKeyCodeToInput(e.getKeyCode());
        switch (input) {
            case Up -> m_isUpPressed = false;
            case Right -> m_isRightPressed = false;
            case Down -> m_isDownPressed = false;
            case Left -> m_isLeftPressed = false;
            case Fire -> m_isFirePressed = false;
            default -> {}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do Nothing
    }

    private Input convertKeyCodeToInput(int keyCode) {
        Input input = Input.None;
        if (keyCode == m_upKey) {
            input = Input.Up;
        }
        else if (keyCode == m_rightKey) {
            input = Input.Right;
        }
        else if (keyCode == m_downKey) {
            input = Input.Down;
        }
        else if (keyCode == m_leftKey) {
            input = Input.Left;
        }
        else if (keyCode == m_fireKey) {
            input = Input.Fire;
        }
        return input;
    }

    private transient boolean m_isUpPressed;
    private transient boolean m_isRightPressed;
    private transient boolean m_isDownPressed;
    private transient boolean m_isLeftPressed;
    private transient boolean m_isFirePressed;
    
    private transient Direction m_lastPressedDirection = Direction.NoDirection;
    
    private int m_upKey = KeyEvent.VK_W;
    private int m_rightKey = KeyEvent.VK_D;
    private int m_downKey = KeyEvent.VK_S;
    private int m_leftKey = KeyEvent.VK_A;
    private int m_fireKey = KeyEvent.VK_SPACE;
}