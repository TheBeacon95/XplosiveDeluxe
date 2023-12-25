package level_Interfaces;

import common.ServiceIfc;

/**
 *
 * @author Yanick
 */
public interface LevelManagementServiceIfc extends ServiceIfc {
    
    /**
     * Loads a level.
     */
    public void loadLevel();
    
    /**
     * Shows whether the process of loading a level is done.
     * @return True if loading hasn't started or is done. False if a Level is currently being loaded.
     */
    public boolean isDoneLoadingLevel();
}
