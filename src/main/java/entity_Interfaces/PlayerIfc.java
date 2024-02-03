package entity_Interfaces;

import level_Interfaces.*;

public interface PlayerIfc extends EntityIfc, BombListenerIfc {

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
    void increaseLives();

    /**
     * Increases the speed by one, not exceeding the max.
     */
    void increaseSpeed();

    /**
     * Increases the number of bombs by one, not exceeding the max.
     */
    void increaseBombCount();

    /**
     * Increases the strength by one, not exceeding the max.
     */
    void increaseStrength();

    /**
     * Gives the player an effect for a specific amount of time.
     * @param effect effect
     * @param effectTime the duration of the effect in nanoseconds.
     */
    void effect(PlayerEffect effect, long effectTime);

    /**
     * Tries to give the player an effect for a specific amount of time.
     * If the player is shielded, it won't work.
     * @param effect effect
     * @param effectTime the duration of the effect in nanoseconds.
     */
    void tryEffect(PlayerEffect effect, long effectTime);
}
