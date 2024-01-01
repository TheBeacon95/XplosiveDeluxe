package entity_Impl;

import common.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Abstraction for all items, power-ups and effectors.
 *
 * @author Yanick
 */
public abstract class StillEntityAbs extends EntityAbs {

    public StillEntityAbs(Coordinates position) {
        super(position);
    }

    @Override
    protected BufferedImage getSpriteToDraw() {
        if (m_isDieing) {
            return m_deathAnimation.getSpriteToDraw();
        }
        else {
            return m_defaultAnimation.getSpriteToDraw();
        }
    }
    
    @Override
    public final void update() {
        onUpdate();
    }
    
    protected void onUpdate() {
        // Do nothing.
    }
    
    protected final void setDefaultAnimation(String folderPath) {
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        int i = 0;
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(folderPath + "/Idle_" + i + ".png");
        while (resourceStream != null) {
            BufferedImage nextSprite = load(resourceStream);
            sprites.add(nextSprite);
            i++;
            resourceStream = getClass().getClassLoader().getResourceAsStream(folderPath + "/Idle_" + i + ".png");
        }
        m_defaultAnimation = new Animation(sprites);
    }
    
    protected final void setDeathAnimation(String folderPath) {
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        int i = 0;
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(folderPath + "/Idle_" + i + ".png");
        while (resourceStream != null) {
            BufferedImage nextSprite = load(resourceStream);
            sprites.add(nextSprite);
            i++;
            resourceStream = getClass().getClassLoader().getResourceAsStream(folderPath + "/Idle_" + i + ".png");
        }
        m_deathAnimation = new Animation(sprites);
    }

    protected Animation m_defaultAnimation;
}