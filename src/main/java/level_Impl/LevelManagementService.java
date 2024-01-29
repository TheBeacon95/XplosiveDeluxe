package level_Impl;

import common.*;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class LevelManagementService implements LevelManagementServiceIfc {

    @Override
    public void loadLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDoneLoadingLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getLevelRowsCount() {
        return ROWS_COUNT;
    }

    @Override
    public int getLevelColumnsCount() {
        return COLUMNS_COUNT;
    }
    
    @Override
    public boolean isGridBlockPosition(Coordinates position) {
        boolean isOuterWall = position.x == 0 || position.x == 18 || position.y == 0 || position.y == 14;
        boolean isInnerGrid = position.x % 2 == 0 && position.y % 2 == 0;
        return isOuterWall || isInnerGrid;
    }

    @Override
    public void initializeService() {
        // Nothing to do here.
    }

    @Override
    public String getId() {
        return LevelNames.Services.LevelManagementService;
    }
    
    private final static int BLOCK_SEGMENTS = 8; // Shows how many steps can be taken between two blocks.
    private final static int ROWS_COUNT = 15;
    private final static int COLUMNS_COUNT = 19;
}