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
import level_Impl.BlockType;

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
    }
    
    private void placeRandomMonsters() {
        ArrayList<MonsterType> list = new ArrayList<>();
        list.add(MonsterType.Ghost);
        m_monsters.put(new Coordinates(3, 9), list);
    }

    private final HashMap<Coordinates, BlockType> m_blocks;
    private final HashMap<Coordinates, ArrayList<MonsterType>> m_monsters;
    private final HashMap<String, Coordinates> m_players;
    private final HashMap<Coordinates, CollectableType> m_collectables;
}