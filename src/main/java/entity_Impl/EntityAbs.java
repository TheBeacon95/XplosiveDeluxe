package entity_Impl;

import common.*;
import entity_Interfaces.EntityIfc;
import level_Interfaces.*;
import ui_Interfaces.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class EntityAbs implements EntityIfc {

    public EntityAbs(Coordinates position) {
        m_position = position;
        m_direction = Direction.Down;
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
        m_blockSegments = ((MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService)).getBlockSegments();
    }

    @Override
    public Coordinates getPosition() {
        return new Coordinates(m_position);
    }

    @Override
    public Direction getDirection() {
        return m_direction;
    }
    
    public final void draw(Graphics2D g2) {
        int tileSize = m_screenService.getTileSize();
        int xDrawPosition = m_position.x * tileSize / m_blockSegments;
        int yDrawPosition = m_position.y * tileSize / m_blockSegments;
        g2.drawImage(getSpriteToDraw(), xDrawPosition, yDrawPosition, tileSize, tileSize, null);
    }
    
    public final void kill() {
        // Todo: Implement
        // Unregister from everywhere
        // Disable movement
        // Disable behaviors
        // Start playing death animation
    }

    protected abstract BufferedImage getSpriteToDraw();
    
    protected Coordinates m_position;
    protected Direction m_direction;
    protected boolean m_isDieing;
    protected Animation m_deathAnimation;
    
    private final ScreenServiceIfc m_screenService;
    private final int m_blockSegments;
}
