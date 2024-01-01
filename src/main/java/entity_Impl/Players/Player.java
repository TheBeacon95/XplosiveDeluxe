package entity_Impl.Players;

import common.*;
import entity_Impl.MovingEntityAbs; // Todo: this class shouldn't reference a higher package.
import entity_Interfaces.*;
import level_Interfaces.*;
import ui_Interfaces.*;

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
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
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
            return m_movementService.convertDesiredDirection(getGlobalPosition(), desiredDirection, m_status.getEffect() == PlayerEffect.Ghost);
        }
    }
    
    @Override
    protected final void onUpdate() {
        // Todo: implement fire.
        placeBomb();
    }

    @Override
    public void onBombPlaced() {
        m_activeBombCount++;
    }

    @Override
    public void onBombExploded() {
        // Do nothing
    }

    @Override
    public void onBombDestroyed() {
        m_activeBombCount--;
    }

    @Override
    public final String getId() {
        return m_playerId;
    }
    
    private void placeBomb() {
        boolean isFirePressed = m_keyHandler.isFirePressed();
        boolean areBombsAvailable = m_activeBombCount < m_status.getBombCount();
        
        if (isFirePressed && areBombsAvailable) {
            m_stageManagementService.placeBomb(BombType.FireBomb, getGridPosition(), m_status.getStrength(), this);
        }
    }
    
    private void clearEffect() {
        if (m_status.getEffect() != PlayerEffect.None && System.nanoTime() >= m_effectEndTime) {
            m_status.setEffect(PlayerEffect.None);
        }
    }
    
    private final String m_playerId;
    private final PlayerControls m_controls;
    private final PlayerStatus m_status;
    
    private long m_effectEndTime;
    private final KeyHandlerIfc m_keyHandler;
    
    private int m_activeBombCount;
    private final StageManagementServiceIfc m_stageManagementService;
}