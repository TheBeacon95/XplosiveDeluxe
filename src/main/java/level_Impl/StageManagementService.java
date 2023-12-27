package level_Impl;

import level_Interfaces.*;
import common.Coordinates;
import common.ServiceManager;
import entity_Interfaces.*;
import ui_Interfaces.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
        createStage(level);
        MovementService.getInstance().setStage(m_stage);
        m_isReady = true;
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
//        Level level = new Level();
//        level.setupTestLevel();
        setStage(Level.readLevelConfig());
        setBackground();
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
                m_entityManagementService.createMonster(monster, monsterList.getKey().scale(8));
            }
        }
        m_entityManagementService.createMonster(MonsterType.Ghost, new Coordinates(8, 8));

        Map<Coordinates, CollectableType> collectables = level.getCollectables();
        for (Map.Entry<Coordinates, CollectableType> collectable : collectables.entrySet()) {
            m_entityManagementService.createCollectable(collectable.getValue(), collectable.getKey());
        }
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
        for (Map.Entry<Coordinates, BlockAbs> block : m_stage.getBlocks().entrySet()) {
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

    private BlockFactory m_blockFactory;
    private EntityManagementServiceIfc m_entityManagementService;
    private ArrayList<Coordinates> m_explosions;
    private Stage m_stage;
    private ScreenServiceIfc m_screenService;
    private BufferedImage m_backgroundSprite;
    private boolean m_isReady;

    private final String DEFAULT_BACKGROUND_STYLE = "Default";
}