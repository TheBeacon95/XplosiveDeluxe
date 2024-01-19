package entity_Impl.Players;

import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class PlayerStatus implements PlayerStatusIfc {

    public PlayerStatus(int lifeCount) {
        m_lifeCount = lifeCount < MAX_LIFE_COUNT ? lifeCount : MAX_LIFE_COUNT;
        m_speed = Speed.DefaultPlayer;
        m_bombCount = 1;
        m_strength = 2;
        m_effect = PlayerEffect.None;
    }

    @Override
    public int getLifeCount() {
        return m_lifeCount;
    }

    @Override
    public Speed getSpeed() {
        return m_speed;
    }

    @Override
    public int getBombCount() {
        return m_bombCount;
    }

    @Override
    public int getStrength() {
        return m_strength;
    }

    @Override
    public PlayerEffect getEffect() {
        return m_effect;
    }

    //
    public void IncreaseLives() {
        if (m_lifeCount < MAX_LIFE_COUNT) {
            m_lifeCount++;
        }
    }

    public void IncreaseSpeed() {
            m_speed = m_speed.increase();
    }

    public void IncreaseBombCount() {
        if (m_bombCount < MAX_BOMB_COUNT) {
            m_bombCount++;
        }
    }

    public void IncreaseStrength() {
        if (m_strength < MAX_STRENGTH) {
            m_strength++;
        }
    }
    
    public void setEffect(PlayerEffect effect) {
        m_effect = effect;
    }

    private int m_lifeCount;
    private Speed m_speed;
    private int m_bombCount;
    private int m_strength;
    private PlayerEffect m_effect;

    private final static int MAX_LIFE_COUNT = 9;
    private final static int MAX_SPEED = 4;
    private final static int MAX_BOMB_COUNT = 9;
    private final static int MAX_STRENGTH = 10;
}