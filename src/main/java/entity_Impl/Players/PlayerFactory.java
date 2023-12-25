package entity_Impl.Players;

import common.Coordinates;

/**
 * Creates instances of players
 * @author Yanick
 */
public class PlayerFactory {
    public Player createPlayer(String playerId, Coordinates position, String skinPath) {
        Player player = new Player(playerId, position, 5, skinPath); // TODO: make lifeCount chooseable.
//        player.setupAnimations(skinPath);
        return player;
    }
}