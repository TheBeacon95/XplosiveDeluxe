package level_Impl;

import common.*;
import java.util.HashMap;
import java.util.Map;
import level_Impl.Blocks.*;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Stage {

    public Stage(Level level) {
        m_blockFactory = new BlockFactory();
        m_levelManagementService = (LevelManagementServiceIfc) ServiceManager.getService(LevelNames.Services.LevelManagementService);
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
        if (!m_levelManagementService.isGridBlockPosition(position)) {
            newBlock = isRealBomb ? m_blockFactory.createBomb(position) : m_blockFactory.createBlock(blockType, position);
            removeBlock(position); // If there's a block here, replace it.
            m_blocks.put(position, newBlock);
        }
        return newBlock;
    }

    private void setupGrid() {
        // Todo: get stage size from service
        for (int column = 0; column < 19; column++) {
            for (int row = 0; row < 15; row++) {
                Coordinates position = new Coordinates(column, row);
                if (m_levelManagementService.isGridBlockPosition(position)) {
                    if (m_blocks.containsKey(position)) {
                        m_blocks.remove(position);
                    }
                    m_blocks.put(position, new Wall());
                }
            }
        }
    }

    private void setupBlocks(Level level) {
        Map<Coordinates, BlockType> blocks = level.getBlocks();
        for (Map.Entry<Coordinates, BlockType> block: blocks.entrySet()) {
            m_blocks.put(block.getKey(), m_blockFactory.createBlock(block.getValue(), block.getKey()));
        }
    }

    private BlockFactory m_blockFactory;
    private final LevelManagementServiceIfc m_levelManagementService;
    private final HashMap<Coordinates, BlockAbs> m_blocks;
}