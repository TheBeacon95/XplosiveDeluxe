package level_Impl;

import level_Impl.Blocks.*;
import common.Coordinates;
import entity_Impl.Monsters.MonsterEntities.Ghost;
import entity_Interfaces.CollectableType;
import entity_Interfaces.MonsterType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yanick
 */
public class Level {

    public Level() {
        m_blocks = new HashMap<>();
        m_monsters = new HashMap<>();
        m_players = new HashMap<>();
        m_collectables = new HashMap<>();
    }

    public boolean readLevelConfig() {
        // Todo: implement
        return false;
    }

    public void setupTestLevel() {
        setupGrid();
        placeRandomBlocks();
    }

    public HashMap<Coordinates, ArrayList<MonsterType>> getMonsters() {
        return (HashMap<Coordinates, ArrayList<MonsterType>>) m_monsters.clone();
    }

    public HashMap<String, Coordinates> getPlayers() {
        return (HashMap<String, Coordinates>) m_players.clone();
    }

    public HashMap<Coordinates, CollectableType> getCollectables() {
        return (HashMap<Coordinates, CollectableType>) m_collectables.clone();
    }

    public HashMap<Coordinates, BlockAbs> getBlocks() {
        return (HashMap<Coordinates, BlockAbs>) m_blocks.clone();
    }

    private boolean isLevelValid() {
        // Todo: improve by checking the player strings.
        boolean areAllPlayersRegistered = m_players.size() == 4;
        boolean isAtLeastOneMonsterRegistered = m_monsters.size() >= 1;

        return areAllPlayersRegistered && isAtLeastOneMonsterRegistered;
    }

    private void setupGrid() {
        // Todo: get stage size from service
        for (int column = 0; column < 19; column++) {
            for (int row = 0; row < 15; row++) {
                if (column == 0 || column == 18 || row == 0 || row == 14) {
                    // Wall around the level
                    m_blocks.put(new Coordinates(column, row), new Wall());
                }
                else if ((column % 2 == 0) && (row % 2 == 0)) {
                    // Grid blocks
                    m_blocks.put(new Coordinates(column, row), new Wall());
                }
            }
        }
    }
    
    private void placeRandomBlocks() {
        m_blocks.put(new Coordinates(3, 1), new Brick());
        m_blocks.put(new Coordinates(1, 3), new Brick());
        m_blocks.put(new Coordinates(5, 1), new DeathBlock());
        m_blocks.put(new Coordinates(1, 5), new DeathBlock());
    }

    private HashMap<Coordinates, BlockAbs> m_blocks;
    private HashMap<Coordinates, ArrayList<MonsterType>> m_monsters;
    private HashMap<String, Coordinates> m_players;
    private HashMap<Coordinates, CollectableType> m_collectables;
}
