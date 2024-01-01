package level_Interfaces;

/**
 *
 * @author Yanick
 */
public interface BombListenerIfc {
    
    /**
     * Gets called when a bomb is placed.
     */
    void onBombPlaced();
    
    /**
     * Gets called when a bomb is placed.
     */
    void onBombExploded();
    
    /**
     * Gets called when a bomb is destroyed.
     */
    void onBombDestroyed();
}