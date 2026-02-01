package entity_Impl.Players;

import common.*;
import ui_Interfaces.*;

/**
 * Creates instances of players
 * @author Yanick
 */
public class PlayerFactory {
    public Player createPlayer(PlayerId playerId, Coordinates position, String skinPath) {
        InputManagementServiceIfc inputManagementService = (InputManagementServiceIfc) ServiceManager.getService(UiNames.Services.InputManagementService);
        Player player = new Player(position, 5, skinPath, playerId); // TODO: make lifeCount chooseable.
//        player.setupAnimations(skinPath);
        return player;
    }
}