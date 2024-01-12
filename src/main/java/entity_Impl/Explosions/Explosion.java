package entity_Impl.Explosions;

import common.*;
import entity_Impl.*;
import entity_Interfaces.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yanick
 */
public class Explosion extends StillEntityAbs implements ExplosionIfc {

    public Explosion(Coordinates position, ExplosionType type) {
        super(position);
        m_type = type;
        m_animation = new Animation(s_animation);
    }
    
    @Override
    public void explode(ExplosionIfc explosion) {
        // Do nothing
    }

    @Override
    protected boolean canBeKilled() {
        return true;
    }

    @Override
    protected BufferedImage getSpriteToDraw() {
        return m_animation.getSpriteToDraw();
    }
    
    @Override
    protected final void onUpdate() {
        if (m_animation.isDone()) {
            die();
        }
    }
    
    @Override
    public void collide(EntityAbs otherEntity) {
        otherEntity.explode(this);
    }

    @Override
    public ExplosionType getType() {
        return m_type;
    }
    
    public static void loadSprites() {
        s_animation = loadAnimation("Sprites/Explosions/Normal");
        s_animation.setAnimationDuration(LIFE_SPAN);
        s_animation.setSingleAnimation();
    }

    private final ExplosionType m_type;
    private static Animation s_animation;
    private Animation m_animation;

    private final static long LIFE_SPAN = 1000 * 1000 * 1000;
}