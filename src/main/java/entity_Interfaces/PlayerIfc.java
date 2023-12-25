package entity_Interfaces;

public interface PlayerIfc extends EntityIfc {
    /**
     * Shows the status of the player.
     * @return player status
     */
    PlayerStatusIfc getStatus();
    
    /**
     * Shows the controls the player uses.
     * @return player controls
     */
    PlayerControls getControls();
    
    /**
     * Increases the number of lives by one, not exceeding the max.
     */
    void IncreaseLives();

    /**
     * Increases the speed by one, not exceeding the max.
     */
    void IncreaseSpeed();

    /**
     * Increases the number of bombs by one, not exceeding the max.
     */
    void IncreaseBombCount();

    /**
     * Increases the strength by one, not exceeding the max.
     */
    void IncreaseStrength();

    /**
     * Gives the player an effect for a specific amount of time.
     * @param effect effect
     * @param effectTime the duration of the effect in seconds.
     */
    void Effect(PlayerEffect effect, int effectTime);
}
