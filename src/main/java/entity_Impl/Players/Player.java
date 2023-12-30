package entity_Impl.Players;

import common.Coordinates;
import common.Direction;
import common.IdentifiableIfc;
import common.ServiceManager;
import entity_Impl.MovingEntityAbs;
import entity_Interfaces.ExplosionIfc;
import entity_Interfaces.PlayerControls;
import entity_Interfaces.PlayerEffect;
import entity_Interfaces.PlayerIfc;
import entity_Interfaces.PlayerStatusIfc;
import ui_Interfaces.InputServiceIfc;
import ui_Interfaces.KeyHandlerIfc;
import ui_Interfaces.UiNames;

/**
 * Represents a Player entity.
 * @author Yanick
 */
public class Player extends MovingEntityAbs implements PlayerIfc, IdentifiableIfc {

    public Player(String playerId, Coordinates position, int lifeCount, String skinPath){
        super(position, skinPath);
        m_playerId = playerId;
        m_status = new PlayerStatus(lifeCount);
        m_controls = new PlayerControls();
        InputServiceIfc inputService = (InputServiceIfc) ServiceManager.getService(UiNames.Services.InputService);
        m_keyHandler = inputService.getInput(getId());
    }
    
    @Override
    public void explode(ExplosionIfc explosion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PlayerStatusIfc getStatus() {
        return m_status;
    }

    @Override
    public PlayerControls getControls() {
        return m_controls;
    }

    @Override
    public void IncreaseLives() {
        m_status.IncreaseLives();
    }

    @Override
    public void IncreaseSpeed() {
        m_status.IncreaseSpeed();
    }

    @Override
    public void IncreaseBombCount() {
        m_status.IncreaseBombCount();
    }

    @Override
    public void IncreaseStrength() {
        m_status.IncreaseStrength();
    }

    @Override
    public void Effect(PlayerEffect effect, int effectTime) {
        m_status.setEffect(effect);
        m_effectEndTime = System.nanoTime() + effectTime * 1000 * 1000 * 1000;
    }
    
    @Override
    protected int getSpeed() {
        return m_status.getSpeed();
    }

    @Override
    protected Direction getMovementDirection() {
        Direction desiredDirection = m_keyHandler.getPressedDirection();
        if (desiredDirection == Direction.NoDirection) {
            return desiredDirection;
        }
        else {
            return m_movementService.convertDesiredDirection(m_position, desiredDirection, m_status.getEffect() == PlayerEffect.Ghost);
        }
    }

    @Override
    public String getId() {
        return m_playerId;
    }
    
    private void clearEvent() {
        if(m_status.getEffect() != PlayerEffect.None && System.nanoTime() >= m_effectEndTime) {
            m_status.setEffect(PlayerEffect.None);
        }
    }
    
    private final String m_playerId;
    private PlayerControls m_controls;
    private PlayerStatus m_status;
    
    private long m_effectEndTime;
    private KeyHandlerIfc m_keyHandler;
}
