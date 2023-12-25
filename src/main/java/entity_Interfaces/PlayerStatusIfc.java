package entity_Interfaces;

/**
 * Represents the entire status of the Player.
 * All of these attributes can be shown on the HUD.
 * @author Yanick
 */
public interface PlayerStatusIfc {
    /**
     * Shows the amount of lives the player has left.
     * @return life count
     */
    int getLifeCount();

    /**
     * Shows the current speed of the player.
     * @return speed
     */
    int getSpeed();

    /**
     * Shows the number of total bombs the player can place at a time.
     * @return bombCount
     */
    int getBombCount();

    /**
     * Shows the range of the bomb a player can place and the range of their fire attack.
     * @return strength
     */
    int getStrength();

    /**
     * Shows the effect the player currently has.
     * @return player effect
     */
    PlayerEffect getEffect();
}