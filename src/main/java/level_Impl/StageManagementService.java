package level_Impl;

import level_Interfaces.LevelNames;
import level_Interfaces.StageManagementServiceIfc;
import common.Coordinates;
import common.ServiceManager;
import entity_Interfaces.CollectableType;
import entity_Interfaces.EntityManagementServiceIfc;
import entity_Interfaces.EntityNames;
import entity_Interfaces.MonsterType;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import level_Impl.Blocks.Background;
import ui_Interfaces.ScreenServiceIfc;
import ui_Interfaces.UiNames;

/**
 *
 * @author Yanick
 */
public class StageManagementService implements StageManagementServiceIfc {
    
    public StageManagementService() {
        m_blocks = new HashMap<>();
        Level level = new Level();
        level.setupTestLevel();
        setStage(level);
    }
    
    /**
     *
     * @param level
     */
    public final void setStage(Level level) {
       // Todo: put in low prio task so it can be loaded duric active. 
       m_stage = level;
       createStage();
    }
    
    @Override
    public void updateStage() {
        
    }
    
    @Override
    public void draw(Graphics2D g2) {
        drawBackground(g2);
        drawBlocks(g2);
        drawEntities(g2);
    }

    @Override
    public boolean isBetweenCells(Coordinates position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Coordinates> getAllFreeNeighboringCells(Coordinates position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getId() {
        return LevelNames.Services.StageManagementService;
    }
    
    private void createStage() {
        m_blockFactory = new BlockFactory();
        Map<Coordinates, BlockType> blocks = m_stage.getBlocks();
        for (Map.Entry<Coordinates, BlockType> block: blocks.entrySet()) {
            m_blocks.put(block.getKey(), m_blockFactory.createBlock(block.getValue()));
        }
        
        // Todo: expose the levels fields.
        m_screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        Map<String, Coordinates> players = m_stage.getPlayers();
        for (Map.Entry<String, Coordinates> player: players.entrySet()) {
            m_entityManagementService.createPlayer(player.getKey(), player.getValue());
        }
        
        Map<Coordinates, ArrayList<MonsterType>> monsters = m_stage.getMonsters();
        for (Map.Entry<Coordinates, ArrayList<MonsterType>> monsterList: monsters.entrySet()) {
            for (MonsterType monster: monsterList.getValue()) {
                m_entityManagementService.createMonster(monster, monsterList.getKey().scale(8));
            }
        }
        m_entityManagementService.createMonster(MonsterType.Ghost, new Coordinates(3, 9).scale(8));
        
        Map<Coordinates, CollectableType> collectables = m_stage.getCollectables();
        for (Map.Entry<Coordinates, CollectableType> collectable: collectables.entrySet()) {
            m_entityManagementService.createCollectable(collectable.getValue(), collectable.getKey());
        }
    }

    private void drawBackground(Graphics2D g2) {
        BufferedImage sprite = new Background().getSpriteToDraw();
        int tileSize = m_screenService.getTileSize();
        for (int column = 0; column < 19; column++) {
            int xPosition = column * tileSize;
            for (int row = 0; row < 15; row++) {
                int yPosition = row * tileSize;
                g2.drawImage(sprite, xPosition, yPosition, tileSize, tileSize, null);
            }
        }
    }

    private void drawBlocks(Graphics2D g2) {
        for (Map.Entry<Coordinates, BlockAbs> block: m_blocks.entrySet()) {
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
    
    private HashMap<Coordinates, BlockAbs> m_blocks;
    
    private BlockFactory m_blockFactory;
    private EntityManagementServiceIfc m_entityManagementService;
    private ArrayList<Coordinates> m_explosions;
    private Level m_stage;
    private ScreenServiceIfc m_screenService;
}