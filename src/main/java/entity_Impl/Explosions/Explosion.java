package entity_Impl.Explosions;

import common.*;
import entity_Impl.StillEntityAbs;
import entity_Interfaces.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yanick
 */
public class Explosion extends StillEntityAbs {

    public Explosion(Coordinates position, ExplosionType type) {
        super(position);
        m_type = type;
//        setDefaultAnimation("Sprites/Explosions/Normal");
        m_startTime = System.nanoTime();
        m_animation = new Animation(s_animation);
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

    @Override
    protected BufferedImage getSpriteToDraw() {
        return m_animation.getSpriteToDraw();
    }
    
    public static void loadSprites() {
        s_animation = loadAnimation("Sprites/Explosions/Normal");
        s_animation.setSpriteDuration(5);
        s_animation.setSingleAnimation();
    }
    
    private void finish() {
        ((EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService)).removeExplosion(getGridPosition());
    }

    private final ExplosionType m_type;
    private static Animation s_animation;
    private Animation m_animation;

    private final long m_startTime;
    private final static long LIFE_SPAN = 250 * 1000 * 1000;
}