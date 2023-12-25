package game_Interfaces;

import common.ServiceIfc;

/**
 * Allows the game itself to be interacted with
 * @author Yanick
 */
public interface GameManagementServiceIfc extends ServiceIfc {
    
    /**
     * Pauses the game.
     */
    void pause();
    
    /**
     * Unpauses the game.
     */
    void unpause();
    
    /**
     * Shows whether the game is currently running.
     * @return true if the game is active otherwise false.
     */
    boolean isGameActive();
    
    /**
     * Shows whether the game is currently paused.
     * @return true if paused otherwise false.
     */
    boolean isPaused();
}