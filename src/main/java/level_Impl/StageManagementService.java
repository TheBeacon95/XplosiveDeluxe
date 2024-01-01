package level_Impl;

import level_Interfaces.BombIfc;
import level_Interfaces.*;
import common.Coordinates;
import common.ServiceManager;
import entity_Interfaces.*;
import ui_Interfaces.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import level_Impl.Blocks.Bomb;

/**
 *
 * @author Yanick
 */
public class StageManagementService implements StageManagementServiceIfc {

    @Override
    public final void setStage(Level level) {
        // Todo: put in low prio task so it can be loaded duric active.
        m_isReady = false;
        m_stage = new Stage(level);
        // Todo: Move this
        ((InputServiceIfc) ServiceManager.getService(UiNames.Services.InputService)).loadKeyInputs();
        createStage(level);
        MovementService.getInstance().setStage(m_stage);
        m_isReady = true;
    }

    @Override
    public void updateStage() {
        updateBlocks();
    }

    @Override
    public void draw(Graphics2D g2) {
        drawBackground(g2);
        drawBlocks(g2);
        drawEntities(g2);
    }

    @Override
    public void setBackground(String style) {
        try {
            m_backgroundSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Level/Background styles/" + style + "/Idle.png"));
        }
        catch (IOException ex) {
            Logger.getLogger(StageManagementService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isReady() {
        return m_isReady;
    }

    @Override
    public void initializeService() {
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        m_blockSegments = ((MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService)).getBlockSegments();
//        Level level = new Level();
//        level.setupTestLevel();
        setStage(Level.readLevelConfig());
        setBackground();
    }
    
    @Override
    public void placeBomb(BombType bombType, Coordinates position, int strength, BombListenerIfc listener) {
        Bomb newBomb = null;
        if (!isBombHere(position)) {   
            newBomb = (Bomb) m_stage.createBomb(position);
            newBomb.AttachListener(listener);
            newBomb.activate(strength, ExplosionType.FireExplosion);
        }
    }
    
    @Override
    public Coordinates roundToGridPosition(Coordinates position) {
        int gridX = position.x % m_blockSegments < 4 ? position.x / m_blockSegments : position.x / m_blockSegments + 1;
        int gridY = position.y % m_blockSegments < 4 ? position.y / m_blockSegments : position.y / m_blockSegments + 1;
        return new Coordinates(gridX, gridY);
    }
    
    @Override
    public void explode(Coordinates gridPosition) {
        HashMap<Coordinates, BlockAbs> blocks = m_stage.getBlocks();
        if (blocks.containsKey(gridPosition)) {
            blocks.get(gridPosition).explode();
        }
    }
    
    @Override
    public boolean isExplosionStopper(Coordinates gridPosition) {
        HashMap<Coordinates, BlockAbs> blocks = m_stage.getBlocks();
        if (blocks.containsKey(gridPosition)) {
            return blocks.get(gridPosition).canBlockExplosions();
        }
        else {
            return false;
        }
    }

    @Override
    public String getId() {
        return LevelNames.Services.StageManagementService;
    }

    private void createStage(Level level) {
        // Todo: expose the levels fields.
        Map<String, Coordinates> players = level.getPlayers();
        for (Map.Entry<String, Coordinates> player : players.entrySet()) {
            m_entityManagementService.createPlayer(player.getKey(), player.getValue());
        }

        Map<Coordinates, ArrayList<MonsterType>> monsters = level.getMonsters();
        for (Map.Entry<Coordinates, ArrayList<MonsterType>> monsterList : monsters.entrySet()) {
            for (MonsterType monster : monsterList.getValue()) {
                m_entityManagementService.createMonster(monster, monsterList.getKey());
            }
        }

        Map<Coordinates, CollectableType> collectables = level.getCollectables();
        for (Map.Entry<Coordinates, CollectableType> collectable : collectables.entrySet()) {
            m_entityManagementService.createCollectable(collectable.getValue(), collectable.getKey());
        }
    }
    
    private void updateBlocks() {
        for (Map.Entry<Coordinates, BlockAbs> entry: m_stage.getBlocks().entrySet()) {
            entry.getValue().update();
            if (entry.getValue().isDestroyed()) {
                m_stage.removeBlock(entry.getKey());
            }
        }
    }
    
    private boolean isBombHere(Coordinates position) {
        BlockAbs block;
        HashMap<Coordinates, BlockAbs> stageBlocks = m_stage.getBlocks();
        if (stageBlocks.containsKey(position)) {
            block = stageBlocks.get(position);
            if (block.getType() == BlockType.Bomb) {
                return ((Bomb) block).isActive();
            }
        }
        return false;
    }

    private void drawBackground(Graphics2D g2) {
        int tileSize = m_screenService.getTileSize();
        for (int column = 0; column < 19; column++) {
            int xPosition = column * tileSize;
            for (int row = 0; row < 15; row++) {
                int yPosition = row * tileSize;
                g2.drawImage(m_backgroundSprite, xPosition, yPosition, tileSize, tileSize, null);
            }
        }
    }

    private void drawBlocks(Graphics2D g2) {
        Map<Coordinates, BlockAbs> blocks = m_stage.getBlocks();
        for (Map.Entry<Coordinates, BlockAbs> block : blocks.entrySet()) {
            BufferedImage sprite = block.getValue().getSpriteToDraw();
            int tileSize = m_screenService.getTileSize();
            int xPosition = block.getKey().x * tileSize;
            int yPosition = block.getKey().y * tileSize;
            g2.drawImage(sprite, xPosition, yPosition, tileSize, tileSize, null);
        }
    }

    private void drawEntities(Graphics2D g2) {
        m_entityManagementService.drawEntities(g2);
    }

    private void setBackground() {
        setBackground(DEFAULT_BACKGROUND_STYLE);
    }

    private EntityManagementServiceIfc m_entityManagementService;
    private ArrayList<Coordinates> m_explosions;
    private Stage m_stage;
    private ScreenServiceIfc m_screenService;
    private BufferedImage m_backgroundSprite;
    private boolean m_isReady;
    private int m_blockSegments;

    private final String DEFAULT_BACKGROUND_STYLE = "Default";
}
