package level_Impl;

import level_Interfaces.BlockType;
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
        m_blockFactory = new BlockFactory();
        m_blocks = new HashMap<>();
        setupBlocks(level);
        setupGrid();
    }
    
    public HashMap<Coordinates, BlockAbs> getBlocks() {
        try {
            return new HashMap<>(m_blocks);
        }
        catch (java.util.ConcurrentModificationException e) {
            return null;
        }
    }
    
    public void removeBlock(Coordinates position) {
        if (m_blocks.containsKey(position)) {
            m_blocks.remove(position);
        }
    }
    
    public BlockAbs createBlock(BlockType blockType, Coordinates position) {
        return createBlock(blockType, position, false);
    }
    
    public BlockAbs createBomb(Coordinates position) {
        return createBlock(BlockType.Bomb, position, true);
    }
    
    private BlockAbs createBlock(BlockType blockType, Coordinates position, boolean isRealBomb) {
        BlockAbs newBlock = null;
        if (!isGridBlockPosition(position)) {
            newBlock = isRealBomb ? m_blockFactory.createBomb(position) : m_blockFactory.createBlock(blockType);
            removeBlock(position); // If there's a block here, replace it.
            m_blocks.put(position, newBlock);
        }
        return newBlock;
    }
    
    
    
    private boolean isGridBlockPosition(Coordinates position) {
        boolean isOuterWall = position.x == 0 || position.x == 18 || position.y == 0 || position.y == 14;
        boolean isInnerGrid = position.x % 2 == 0 && position.y % 2 == 0;
        return isOuterWall || isInnerGrid;
    }

    private void setupGrid() {
        // Todo: get stage size from service
        for (int column = 0; column < 19; column++) {
            for (int row = 0; row < 15; row++) {
                Coordinates position = new Coordinates(column, row);
                if (isGridBlockPosition(position)) {
                    if (m_blocks.containsKey(position)) {
                        m_blocks.remove(position);
                    }
                    m_blocks.put(position, new Wall());
                }
//                if (column == 0 || column == 18 || row == 0 || row == 14) {
//                    // Wall around the level
//                    Coordinates coordinates = new Coordinates(column, row);
//                    if (m_blocks.containsKey(coordinates)) {
//                        m_blocks.remove(coordinates);
//                    }
//                    m_blocks.put(coordinates, new Wall());
//                }
//                else if ((column % 2 == 0) && (row % 2 == 0)) {
//                    // Grid blocks
//                    Coordinates coordinates = new Coordinates(column, row);
//                    if (m_blocks.containsKey(coordinates)) {
//                        m_blocks.remove(coordinates);
//                    }
//                    m_blocks.put(coordinates, new Wall());
//                }
            }
        }
    }
    
    private void setupBlocks(Level level) {
        Map<Coordinates, BlockType> blocks = level.getBlocks();
        for (Map.Entry<Coordinates, BlockType> block: blocks.entrySet()) {
            m_blocks.put(block.getKey(), m_blockFactory.createBlock(block.getValue()));
        }
    }
    
    private BlockFactory m_blockFactory;
    private final HashMap<Coordinates, BlockAbs> m_blocks;
}