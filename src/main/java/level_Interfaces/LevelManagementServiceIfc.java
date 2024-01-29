package level_Interfaces;

import common.*;

/**
 *
 * @author Yanick
 */
public interface LevelManagementServiceIfc extends ServiceIfc {
    
    /**
     * Loads a level.
     */
    void loadLevel();
    
    /**
     * Shows whether the process of loading a level is done.
     * @return True if loading hasn't started or is done. False if a Level is currently being loaded.
     */
    boolean isDoneLoadingLevel();

    /**
     * Shows the index of the lowest row of blocks.
     * @return
     */
    int getLevelRowsCount();

    /**
     * Shows the index of the right-most column of blocks.
     * @return
     */
    int getLevelColumnsCount();
    
    /**
     * Shows if a cell is supposed to be occupied by a grid block.
     * @param position
     * @return true if it's a grid position. false otherwise.
     */
    boolean isGridBlockPosition(Coordinates position);
}
