package level_Impl;

import common.*;
import entity_Interfaces.*;
import java.util.ArrayList;
import java.util.Random;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class RandomLevelGenerator {
    
    public RandomLevelGenerator() {
        m_monsterTypes = new ArrayList<>();
        m_levelManagementService = (LevelManagementServiceIfc) ServiceManager.getService(LevelNames.Services.LevelManagementService);
        m_maxRow = m_levelManagementService.getLevelRowsCount() - 1;
        m_maxColumn = m_levelManagementService.getLevelColumnsCount() - 1;
        m_random = new Random();
        setBlockedCells();
    }
    
    public void setBlockDensity(int density) {
        if (density > MAX_BLOCK_DENSITY) {
            m_blockDensity = MAX_BLOCK_DENSITY;
        }
        else if (density < MIN_BLOCK_DENSITY) {
            m_blockDensity = MIN_BLOCK_DENSITY;
        }
        else {
            m_blockDensity = density;
        }
    }
    
    public void setMonsterDensity(int density) {
        if (density > MAX_MONSTER_DENSITY) {
            m_monsterDensity = MAX_MONSTER_DENSITY;
        }
        else if (density < MIN_MONSTER_DENSITY) {
            m_monsterDensity = MIN_MONSTER_DENSITY;
        }
        else {
            m_monsterDensity = density;
        }
    }
    
    public void addMonsterType(MonsterType monster) {
        m_monsterTypes.add(monster);
    }
    
    public Level generateRandomLevel() {
        Level generatedLevel = new Level();
        for (int row = 1; row < m_maxRow; row++) {
            for (int column = 1; column < m_maxColumn; column++) {
                Coordinates cell = new Coordinates(column, row);
                randomlySetObjects(generatedLevel, cell);
            }
        }
        setPlayers(generatedLevel);
        return generatedLevel;
    }

    private void randomlySetObjects(Level level, Coordinates cell) {
        if (isCellBlocked(cell)) {
            return;
        }
        boolean canSetBlock = m_random.nextInt(12) < m_blockDensity;
        boolean canSetMonster = !canSetBlock && !m_monsterTypes.isEmpty() && m_random.nextInt(24) < m_monsterDensity;
        
        if (canSetBlock) {
            level.setBlock(BlockType.Brick, cell);
        }
        else if (canSetMonster) {
            level.setMonster(m_monsterTypes.get(m_random.nextInt(m_monsterTypes.size())), cell);
        }
    }

    private boolean isCellBlocked(Coordinates cell) {
        if (m_levelManagementService.isGridBlockPosition(cell)) {
            return true;
        }
        
        boolean isPlayer1Space = cell.equals(Player1Space1) || cell.equals(Player1Space2) || cell.equals(Player1Space3);
        boolean isPlayer2Space = cell.equals(Player2Space1) || cell.equals(Player2Space2) || cell.equals(Player2Space3);
        boolean isPlayer3Space = cell.equals(Player3Space1) || cell.equals(Player3Space2) || cell.equals(Player3Space3);
        boolean isPlayer4Space = cell.equals(Player4Space1) || cell.equals(Player4Space2) || cell.equals(Player4Space3);
        
        return isPlayer1Space || isPlayer2Space  || isPlayer3Space || isPlayer4Space;
    }

    private void setBlockedCells() {
        // Todo: set correct cells
        Player1Space1 = new Coordinates(1, 1);
        Player1Space2 = new Coordinates(1, 2);
        Player1Space3 = new Coordinates(2, 1);
        
        Player2Space1 = new Coordinates(1, m_maxRow - 1);
        Player2Space2 = new Coordinates(1, m_maxRow - 2);
        Player2Space3 = new Coordinates(2, m_maxRow - 1);
        
        Player3Space1 = new Coordinates(m_maxColumn - 1, m_maxRow - 1);
        Player3Space2 = new Coordinates(m_maxColumn - 1, m_maxRow - 2);
        Player3Space3 = new Coordinates(m_maxColumn - 2, m_maxRow - 1);
        
        Player4Space1 = new Coordinates(m_maxColumn - 1, 1);
        Player4Space2 = new Coordinates(m_maxColumn - 1, 2);
        Player4Space3 = new Coordinates(m_maxColumn - 2, 1);
        
    }
    
    private int m_blockDensity;
    private int m_monsterDensity;
    
    private final int m_maxRow;
    private final int m_maxColumn;
    private final Random m_random;
    private final LevelManagementServiceIfc m_levelManagementService;
    
    private final ArrayList<MonsterType> m_monsterTypes;
    
    private final static int MIN_BLOCK_DENSITY = 0;
    private final static int MAX_BLOCK_DENSITY = 9;
    private final static int MIN_MONSTER_DENSITY = 0;
    private final static int MAX_MONSTER_DENSITY = 9;
    
    private Coordinates Player1Space1;
    private Coordinates Player1Space2;
    private Coordinates Player1Space3;
    private Coordinates Player2Space1;
    private Coordinates Player2Space2;
    private Coordinates Player2Space3;
    private Coordinates Player3Space1;
    private Coordinates Player3Space2;
    private Coordinates Player3Space3;
    private Coordinates Player4Space1;
    private Coordinates Player4Space2;
    private Coordinates Player4Space3;

    private void setPlayers(Level level) {
        level.setPlayer("Player_1", Player1Space1);
        level.setPlayer("Player_2", Player2Space1);
        level.setPlayer("Player_3", Player3Space1);
        level.setPlayer("Player_4", Player4Space1);
    }
}