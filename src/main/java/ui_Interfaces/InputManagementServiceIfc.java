package ui_Interfaces;

import common.PlayerId;
import common.ServiceIfc;
import javax.swing.JPanel;

/**
 *
 * @author Yanick
 */
public interface InputManagementServiceIfc extends ServiceIfc {

    /**
     * Reads all the inputs for the players from persistency.
     */
    void loadPlayerInputs();

    /**
     * Saves all the player inputs to a file.
     */
    void savePlayerInputs();

    /**
     * Sets the player inputs to active.
     * @param panel the panel that is responsible for the KeyBindings
     */
    void activatePlayerInputs(JPanel panel);

    /**
     * Sets all the player inputs to inactive.
     * @param panel the panel that was responsible for the KeyBindings
     */
    void deactivatePlayerInputs(JPanel panel);

    /**
     * Sets the menu inputs to active.
     * @param panel the panel that is responsible for the KeyBindings
     */
    void activateMenuInputs(JPanel panel);

    /**
     * Sets all the menu inputs to inactive.
     * @param panel the panel that was responsible for the KeyBindings
     */
    void deactivateMenuInputs(JPanel panel);

    /**
     * Gets the playerInput object that the player can reference during the game.
     * @param playerId
     * @return
     */
    PlayerInput getPlayerInput(PlayerId playerId);

    /**
     * Gets a list of all currently pressed buttons for the menu.
     * @return
     */
    MenuInput getPressedMenuButtons();
}