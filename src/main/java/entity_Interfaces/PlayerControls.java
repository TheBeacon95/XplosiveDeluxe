package entity_Interfaces;

import java.awt.event.KeyEvent;
// Todo: see if this is still needed.
/**
 * Represents the controls the Player will use.
 * @author Yanick
 */
public final class PlayerControls {
    
    public PlayerControls() {
        this(DEFAULT_UP_KEY, DEFUALT_RIGHT_KEY, DEFAULT_DOWN_KEY, DEFAULT_LEFT_KEY, DEFAULT_FIRE_KEY);
    }
    
    public PlayerControls(int upKey, int rightKey, int downKey, int leftKey, int fireKey) {
        ConfigureKeys(upKey, rightKey, downKey, leftKey, fireKey);
    }
    
    public int getUpKey() {
        return m_upKey;
    }
    
    public int getRightKey() {
        return m_rightKey;
    }
    
    public int getDownKey() {
        return m_downKey;
    }
    
    public int getLeftKey() {
        return m_leftKey;
    }
    
    public int getFireKey() {
        return m_fireKey;
    }
    
    public void ConfigureKeys(int upKey, int rightKey, int downKey, int leftKey, int fireKey) {
        m_upKey = upKey;
        m_rightKey = rightKey;
        m_downKey = downKey;
        m_leftKey = leftKey;
        m_fireKey = fireKey;
    }
    
    private int m_upKey;
    private int m_rightKey;
    private int m_downKey;
    private int m_leftKey;
    private int m_fireKey;
    
    private final static int DEFAULT_UP_KEY = KeyEvent.VK_W;
    private final static int DEFUALT_RIGHT_KEY = KeyEvent.VK_D;
    private final static int DEFAULT_DOWN_KEY = KeyEvent.VK_S;
    private final static int DEFAULT_LEFT_KEY = KeyEvent.VK_A;
    private final static int DEFAULT_FIRE_KEY = KeyEvent.VK_SPACE;
}
