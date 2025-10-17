 package entity_Impl.Players;

import common.*;
import entity_Impl.MovingEntityAbs; // Todo: this class shouldn't reference a higher package.
import entity_Interfaces.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Logger;
import level_Interfaces.*;

/**
 * Represents a Player entity.
 * @author Yanick
 */
public class Player extends MovingEntityAbs implements PlayerIfc, IdentifiableIfc {

    public Player(String playerId, Coordinates position, int lifeCount, String skinPath) {
        super(position, skinPath);
        m_playerId = playerId;
        m_status = new PlayerStatus(lifeCount);
        m_controls = new PlayerControls();
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_facingDirection = Direction.Down;
        loadKeyInputs();
    }

    @Override
    public void explode(ExplosionIfc explosion) {
        if (!isDieing() && canBeKilled()) {
            kill();
        }
    }

    @Override
    protected boolean canBeKilled() {
        return m_status.getEffect() != PlayerEffect.Shield;
    }

    @Override
    public void onKilled() {
        m_stageManagementService.placeDeathBlock(getGridPosition());
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
    public void increaseLives() {
        m_status.increaseLives();
    }

    @Override
    public void increaseSpeed() {
        m_status.increaseSpeed();
    }

    @Override
    public void increaseBombCount() {
        m_status.increaseBombCount();
    }

    @Override
    public void increaseStrength() {
        m_status.increaseStrength();
    }

    @Override
    public void effect(PlayerEffect effect, long effectTime) {
        m_status.setEffect(effect);
        m_effectEndTime = System.nanoTime() + effectTime;
    }

    @Override
    public void tryEffect(PlayerEffect effect, long effectTime) {
        if (m_status.getEffect() != PlayerEffect.Shield) {
            effect(effect, effectTime);
        }
    }

    @Override
    public void stall(long stallDuration) {
        if (m_status.getEffect() != PlayerEffect.Shield) {
            super.stall(stallDuration);
        }
    }

    @Override
    protected Speed getSpeed() {
        return m_status.getSpeed();
    }

    @Override
    protected Direction getMovementDirection() {
        Direction desiredDirection = m_keyHandler.getPressedDirection();
        if (desiredDirection == Direction.NoDirection) {
            return desiredDirection;
        }
        else {
            Direction convertedDirection = m_movementService.convertDesiredDirection(getGlobalPosition(), desiredDirection, m_status.getEffect() == PlayerEffect.Ghost);
            m_facingDirection = convertedDirection == Direction.NoDirection ? desiredDirection : convertedDirection;
            return convertedDirection;
        }
    }

    @Override
    protected Direction getDisplayDirection() {
        return m_facingDirection;
    }

    @Override
    protected final void act() {
        // Todo: implement fire.
        placeBomb();
        clearEffect();
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

    private void loadKeyInputs() {
        FileInputStream fileInputStream;
        PlayerKeyHandler keyHandler = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\" + getId() + ".xpd");
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                keyHandler = (PlayerKeyHandler) objectInputStream.readObject();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(java.util.logging.Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        m_keyHandler = keyHandler;
    }

    private final String m_playerId;
    private final PlayerControls m_controls;
    private final PlayerStatus m_status;

    private Direction m_facingDirection;

    private long m_effectEndTime;
    private PlayerKeyHandler m_keyHandler;

    private int m_activeBombCount;
    private final StageManagementServiceIfc m_stageManagementService;
}