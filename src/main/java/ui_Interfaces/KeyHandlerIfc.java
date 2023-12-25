package ui_Interfaces;

import common.Direction;
import java.awt.event.KeyListener;

/**
 * Listens to key inputs and relays them in a more usable way.
 * @author Yanick
 */
public interface KeyHandlerIfc extends KeyListener {
    /**
     * Returns the direction the player presses.
     * If multiple directions are pressed, the newest direction will be used.
     * @return direction the player will try to go in.
     */
    Direction getPressedDirection();
    
    /**
     * Shows if the fire button is currently pressed.
     * @return true if fire is being pressed. false otherwise.
     */
    boolean isFirePressed();
    
    /**
     * Shows which button corresponds with the up key.
     * @return key code of the up key.
     */
    int upKey();
    
    /**
     * Shows which button corresponds with the right key.
     * @return key code of the right key.
     */
    int rightKey();
    
    /**
     * Shows which button corresponds with the down key.
     * @return key code of the down key.
     */
    int downKey();
    
    /**
     * Shows which button corresponds with the left key.
     * @return key code of the left key.
     */
    int leftKey();
    
    /**
     * Shows which button corresponds with the fire key.
     * @return key code of the fire key.
     */
    int fireKey();
}