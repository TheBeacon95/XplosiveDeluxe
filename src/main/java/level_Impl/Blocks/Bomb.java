package level_Impl.Blocks;

import level_Interfaces.BombListenerIfc;
import level_Interfaces.BombIfc;
import common.*;
import entity_Interfaces.*;
import java.util.ArrayList;
import level_Impl.*;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class Bomb extends BlockAbs implements BombIfc {
    
    public Bomb() {
        this(null);
    }
    
    public Bomb(Coordinates position) {
        super(BlockType.Bomb);
        m_position = position;
        m_listeners = new ArrayList<>();
    }
    
    @Override
    public boolean isPhaseable() {
        return true;
    }
    
    @Override
    public boolean canBlockExplosions() {
        return false;
    }
    
    public void activate(int strength, ExplosionType explosionType) {
        if (m_position != null) {
            m_strength = strength;
            m_explosionType = explosionType;
            m_activationTime = System.nanoTime();
            m_isActive = true;
            fireOnPlacedEvent();
        }
    }
    
    @Override
    protected void onUpdate() {
        if (!m_isDetonated && m_isActive && System.nanoTime() > m_activationTime + FUSE_TIME) {
            detonate();
        }
    }
    
    @Override
    protected void onExplode() {
        if (!m_isDetonated && m_isActive) {
            detonate();
            fireOnExplodedEvent();
        }
        
    }
    
    @Override
    protected final void onDestroyed() {
        fireOnDestroyedEvent();
        m_listeners.clear();
    }

    @Override
    public void AttachListener(BombListenerIfc listener) {
        m_listeners.add(listener);
    }
    
    private void detonate() {
        m_isDetonated = true;
        EntityManagementServiceIfc entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        entityManagementService.createExplosion(m_explosionType, m_strength, m_position);
        explode();
    }

    public boolean isActive() {
        return m_isActive;
    }
    
    private void fireOnExplodedEvent() {
        for (BombListenerIfc listener: m_listeners) {
            listener.onBombExploded();
        }
    }
    
    private void fireOnPlacedEvent() {
        for (BombListenerIfc listener: m_listeners) {
            listener.onBombPlaced();
        }
    }
    
    private void fireOnDestroyedEvent() {
        for (BombListenerIfc listener: m_listeners) {
            listener.onBombDestroyed();
        }
    }
    
    private int m_strength;
    private ExplosionType m_explosionType;
    private boolean m_isActive;
    private boolean m_isDetonated;
    private long m_activationTime;
    private final Coordinates m_position;
    private static final long FUSE_TIME = 2 * 1000 * 1000 * 1000; // Fuse time in nanoseconds.
    
    private final ArrayList<BombListenerIfc> m_listeners;
}