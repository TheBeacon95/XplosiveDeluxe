package level_Interfaces;

import common.Coordinates;
import entity_Interfaces.CollectableType;
import entity_Interfaces.MonsterType;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Represents the serializable template for a stage.
 * @author Yanick
 */
public class Level implements Serializable {

    public Level() {
        m_blocks = new HashMap<>();
        m_monsters = new HashMap<>();
        m_players = new HashMap<>();
        m_collectables = new HashMap<>();
    }
    
    public static Level readLevelConfig() {
        return readLevelConfig("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\Levels\\TestLevel.txt");
    }

    public static Level readLevelConfig(String filePath) {
        Level readLevel = null;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                readLevel = (Level) objectInputStream.readObject();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return readLevel;
    }
    
    public void writeTestLevel() {
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\Levels\\TestLevel.txt");
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(this);
                objectOutputStream.flush();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public final void setupTestLevel() {
//        setupGrid();
        placeRandomBlocks();
        placeRandomMonsters();
        placePlayers();
        writeTestLevel();
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

    public HashMap<Coordinates, BlockType> getBlocks() {
        return (HashMap<Coordinates, BlockType>) m_blocks.clone();
    }

    private boolean isLevelValid() {
        // Todo: improve by checking the player strings.
        boolean areAllPlayersRegistered = m_players.size() == 4;
        boolean isAtLeastOneMonsterRegistered = m_monsters.size() >= 1;

        return areAllPlayersRegistered && isAtLeastOneMonsterRegistered;
    }
    
    private void placeRandomBlocks() {
        m_blocks.put(new Coordinates(1, 2), BlockType.Brick);
        m_blocks.put(new Coordinates(2, 1), BlockType.Brick);
        m_blocks.put(new Coordinates(3, 1), BlockType.Brick);
        m_blocks.put(new Coordinates(2, 2), BlockType.Brick);
        m_blocks.put(new Coordinates(1, 3), BlockType.Brick);
        m_blocks.put(new Coordinates(5, 1), BlockType.DeathBlock);
        m_blocks.put(new Coordinates(1, 5), BlockType.DeathBlock);
        m_blocks.put(new Coordinates(8, 7), BlockType.Bomb);
        m_blocks.put(new Coordinates(7, 6), BlockType.Bomb);
        m_blocks.put(new Coordinates(3, 8), BlockType.Brick);
        m_blocks.put(new Coordinates(3, 10), BlockType.Brick);
        m_blocks.put(new Coordinates(2, 9), BlockType.Brick);
        m_blocks.put(new Coordinates(4, 9), BlockType.Brick);
    }
    
    private void placeRandomMonsters() {
        ArrayList<MonsterType> list = new ArrayList<>();
        list.add(MonsterType.Muncher);
        m_monsters.put(new Coordinates(3, 9), list);
    }
    
    private void placePlayers() {
        m_players.put("Player_1", new Coordinates(3, 3));
        m_players.put("Player_2", new Coordinates(15, 3));
        m_players.put("Player_3", new Coordinates(3, 13));
        m_players.put("Player_4", new Coordinates(15, 13));
    }

    private final HashMap<Coordinates, BlockType> m_blocks;
    private final HashMap<Coordinates, ArrayList<MonsterType>> m_monsters;
    private final HashMap<String, Coordinates> m_players;
    private final HashMap<Coordinates, CollectableType> m_collectables;
}