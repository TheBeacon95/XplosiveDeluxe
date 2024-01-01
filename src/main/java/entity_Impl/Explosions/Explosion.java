package entity_Impl.Explosions;

import common.*;
import entity_Impl.StillEntityAbs;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Explosion extends StillEntityAbs {

    public Explosion(Coordinates position, ExplosionType type) {
        super(position);
        m_type = type;
        setDefaultAnimation("Sprites/Explosions/Normal");
        m_startTime = System.nanoTime();
    }
    
    @Override
    public void explode(ExplosionIfc explosion) {
        // Do nothing
    }
    
    @Override
    protected void onUpdate() {
        if (m_startTime + LIFE_SPAN <= System.nanoTime()) {
            finish();
        }
    }
    
    private void finish() {
        ((EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService)).removeExplosion(getGridPosition());
    }

    private final ExplosionType m_type;

    private final long m_startTime;
    private final static long LIFE_SPAN = 500 * 1000 * 1000;
}