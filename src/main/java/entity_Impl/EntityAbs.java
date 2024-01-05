package entity_Impl;

import common.*;
import entity_Interfaces.EntityIfc;
import level_Interfaces.*;
import ui_Interfaces.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public abstract class EntityAbs implements EntityIfc {

    public EntityAbs(Coordinates position) {
        m_direction = Direction.Down;
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
        m_movementService = ((MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService));
        m_stageManagementService = ((StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService));
        m_blockSegments = m_movementService.getBlockSegments();
        m_globalPosition = new Coordinates(position).scale(m_blockSegments);
    }

    @Override
    public Coordinates getGlobalPosition() {
        return new Coordinates(m_globalPosition);
    }

    @Override
    public Coordinates getGridPosition() {
        return m_stageManagementService.roundToGridPosition(m_globalPosition);
    }

    @Override
    public Direction getDirection() {
        return m_direction;
    }
    
    public final void draw(Graphics2D g2) {
        int tileSize = m_screenService.getTileSize();
        int xDrawPosition = getGlobalPosition().x * tileSize / m_blockSegments;
        int yDrawPosition = getGlobalPosition().y * tileSize / m_blockSegments;
        g2.drawImage(getSpriteToDraw(), xDrawPosition, yDrawPosition, tileSize, tileSize, null);
    }
    
    public final boolean isColliding(EntityAbs otherEntity) {
        Coordinates myPosition = getGlobalPosition();
        Coordinates otherPosition = otherEntity.getGlobalPosition();
        int tileSize = m_screenService.getTileSize();
        boolean isXCloseEnough = Math.abs(otherPosition.x - myPosition.x) < tileSize / m_blockSegments;
        boolean isYCloseEnough = Math.abs(otherPosition.y - myPosition.y) < tileSize / m_blockSegments;
        
        // Since we are working with grid movement, if x and y are both close, either both x's or boty y's are equal.
        return isXCloseEnough && isYCloseEnough;
    }
    
    public void collide(EntityAbs otherEntity) {
        // Do nothing
    }
    
    public abstract void kill();
    
    public void explode() {
        // Do nothing
    }
            
    protected static BufferedImage load(InputStream fileStream) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(fileStream);
        }
        catch (IOException ex) {
            Logger.getLogger(MovingEntityAbs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return sprite;
    }

    protected abstract BufferedImage getSpriteToDraw();
    
    protected Coordinates m_globalPosition;
    protected Direction m_direction;
    protected boolean m_isDieing;
    protected Animation m_deathAnimation;
    
    private final ScreenServiceIfc m_screenService;
    private final MovementServiceIfc m_movementService;
    private final StageManagementServiceIfc m_stageManagementService;
    protected final int m_blockSegments;
}