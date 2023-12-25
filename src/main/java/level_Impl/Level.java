package level_Impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import level_Impl.Blocks.*;
import common.Coordinates;
import entity_Interfaces.CollectableType;
import entity_Interfaces.MonsterType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

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

    public static Level readLevelConfig() {
        XmlMapper mapper = new XmlMapper();
        Level level = null;
        try {
            level = mapper.readValue(Level.class.getClassLoader().getResourceAsStream("Level/TestLevel.xml"), Level.class);
        }
        catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return level;
    }
    
    public void writeTestLevel() {
        XmlMapper mapper = new XmlMapper();
        try {
            //        XMLStreamWriter streamWriter = XMLOutputFactory.newFactory().createXMLStreamWriter(new FileOutputStream("Level/TestLevel.xml"));
            mapper.writeValue(new File("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\Levels\\TestLevel.xml"), this);
        }
        catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void setupTestLevel() {
        setupGrid();
        placeRandomBlocks();
//        writeTestLevel();
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

    private void setupGrid() {
        // Todo: get stage size from service
        for (int column = 0; column < 19; column++) {
            for (int row = 0; row < 15; row++) {
                if (column == 0 || column == 18 || row == 0 || row == 14) {
                    // Wall around the level
                    m_blocks.put(new Coordinates(column, row), BlockType.Wall);
                }
                else if ((column % 2 == 0) && (row % 2 == 0)) {
                    // Grid blocks
                    m_blocks.put(new Coordinates(column, row), BlockType.Wall);
                }
            }
        }
    }
    
    private void placeRandomBlocks() {
        m_blocks.put(new Coordinates(3, 1), BlockType.Brick);
        m_blocks.put(new Coordinates(1, 3), BlockType.Brick);
        m_blocks.put(new Coordinates(5, 1), BlockType.DeathBlock);
        m_blocks.put(new Coordinates(1, 5), BlockType.DeathBlock);
    }

    private HashMap<Coordinates, BlockType> m_blocks;
    private HashMap<Coordinates, ArrayList<MonsterType>> m_monsters;
    private HashMap<String, Coordinates> m_players;
    private HashMap<Coordinates, CollectableType> m_collectables;
}
