package ui_Interfaces;

import common.Direction;
import common.IdentifiableIfc;
import common.ServiceIfc;
import java.util.NoSuchElementException;
import javax.management.openmbean.KeyAlreadyExistsException;

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
}
//    /**
//     * Allows for a user to attach their input buttons.
//     * @param user The instance that uses the input buttons
//     * @param upKeyCode The key code for up
//     * @param rightKeyCode The key code for right
//     * @param downKeyCode The key code for down
//     * @param leftKeyCode The key code for left
//     * @param fireKeyCode The key code for fire
//     * @throws KeyAlreadyExistsException the instance was already attached.
//     */
//    void attachInput(IdentifiableIfc user, int upKeyCode, int rightKeyCode, int downKeyCode, int leftKeyCode, int fireKeyCode) throws KeyAlreadyExistsException;
//    
//    /**
//     * Removes the input buttons of user.
//     * @param user the instance that needs their input removed.
//     * @throws NoSuchElementException is thrown if the instance is not attached.
//     */
//    void removeInput(IdentifiableIfc user) throws NoSuchElementException;
//    
//    /**
//     * Gets the direction that is currently pressed by the user. If the user is pressing two directions, the newer one is used.
//     * @param user the user that is checking their pressed direction
//     * @return the direction the user is currently pressing.
//     */
//    Direction getDirectionPressed(IdentifiableIfc user);
//    
//    /**
//     * Gets wether the user is pressing fire or not.
//     * @param user the user that is checking their fire button
//     * @return true if fire is pressed false if not
//     */
//    boolean isFirePressed(IdentifiableIfc user);
//    
//    /**
//     * Pause toggle is requested if the pause button was pressed.
//     * The next toggle is only requested if the button was released and pressed again.
//     * @return true if pause or resume is requested.
//     */
//    boolean isPauseToggleRequested();
