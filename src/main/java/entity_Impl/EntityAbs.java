package entity_Impl;

import common.Animation;
import common.Coordinates;
import common.Direction;
import common.ServiceManager;
import entity_Interfaces.EntityIfc;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import ui_Interfaces.ScreenServiceIfc;
import ui_Interfaces.UiNames;

public abstract class EntityAbs implements EntityIfc {

    public EntityAbs(Coordinates position) {
        m_position = position;
        m_direction = Direction.Down;
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
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
        int xDrawPosition = m_position.x * tileSize / 8;
        int yDrawPosition = m_position.y * tileSize / 8;
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
}
