package level_Impl.Blocks;

import common.Coordinates;
import common.ServiceManager;
import entity_Interfaces.*;
import level_Impl.BlockAbs;
import level_Interfaces.BlockType;

/**
 *
 * @author Yanick
 */
public final class Bomb extends BlockAbs {
    
    public Bomb() {
        this(null);
    }
    
    public Bomb(Coordinates position) {
        super(BlockType.Bomb);
        m_position = position;
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
        }
    }
    
    @Override
    protected void onUpdate() {
        if (!m_isDetonated && m_isActive && System.nanoTime() > m_activationTime + FUSE_TIME) {
            m_isDetonated = true;
            detonate();
        }
    }
    
    @Override
    protected void onExplode() {
        if (!m_isDetonated && m_isActive) {
            detonate();
        }
    }
    
    private void detonate() {
        EntityManagementServiceIfc entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        entityManagementService.createExplosion(m_explosionType, m_strength, m_position);
        explode();
    }

    public boolean isActive() {
        return m_isActive;
    }
    
    private int m_strength;
    private ExplosionType m_explosionType;
    private boolean m_isActive;
    private boolean m_isDetonated;
    private long m_activationTime;
    private final Coordinates m_position;
    private static final long FUSE_TIME = 2 * 1000 * 1000 * 1000; // Fuse time in nanoseconds.
}