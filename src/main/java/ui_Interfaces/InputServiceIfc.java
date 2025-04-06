package ui_Interfaces;

import common.ServiceIfc;
import java.util.List;

/**
 * Allows for InputKeys to be attached.
 * Allows for anyone to check if a key was pressed.
 * @author Yanick
 */
public interface InputServiceIfc extends ServiceIfc {

    /**
     * Makes the Service load the key inputs from the config again.
     */
    void loadKeyInputs();

    /**
     * returns the key input for any player
     * @param playerId the ID of the player.
     * @return
     */
    KeyHandlerIfc getInput(String playerId);

    /**
     * Gets all the KeyListeners for the players.
     * @return
     */
    public List<KeyHandlerIfc> getAllPlayerInputs();
}
