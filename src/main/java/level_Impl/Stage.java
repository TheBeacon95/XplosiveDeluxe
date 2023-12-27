package level_Impl;

import level_Interfaces.Level;
import common.Coordinates;
import java.util.HashMap;
import java.util.Map;
import level_Impl.Blocks.*;

/**
 *
 * @author Yanick
 */
public class Stage {
    
    public Stage(Level level) {
        m_blocks = new HashMap<>();
        setupBlocks(level);
        setupGrid();
    }
    
    public HashMap<Coordinates, BlockAbs> getBlocks() {
        return m_blocks;
    }

    private void setupGrid() {
        // Todo: get stage size from service
        for (int column = 0; column < 19; column++) {
            for (int row = 0; row < 15; row++) {
                if (column == 0 || column == 18 || row == 0 || row == 14) {
                    // Wall around the level
                    Coordinates coordinates = new Coordinates(column, row);
                    if (m_blocks.containsKey(coordinates)) {
                        m_blocks.remove(coordinates);
                    }
                    m_blocks.put(coordinates, new Wall());
                }
                else if ((column % 2 == 0) && (row % 2 == 0)) {
                    // Grid blocks
                    Coordinates coordinates = new Coordinates(column, row);
                    if (m_blocks.containsKey(coordinates)) {
                        m_blocks.remove(coordinates);
                    }
                    m_blocks.put(coordinates, new Wall());
                }
            }
        }
    }
    
    private void setupBlocks(Level level) {
        m_blockFactory = new BlockFactory();
        Map<Coordinates, BlockType> blocks = level.getBlocks();
        for (Map.Entry<Coordinates, BlockType> block: blocks.entrySet()) {
            m_blocks.put(block.getKey(), m_blockFactory.createBlock(block.getValue()));
        }
    }
    
    private BlockFactory m_blockFactory;
    private final HashMap<Coordinates, BlockAbs> m_blocks;
}