package entity_Interfaces;

import common.*;

public interface EntityIfc {

    /**
     * Gets the position of the Entity.
     * @return position in pixels
     */
    Coordinates getGlobalPosition();
    
    /**
     * Gets the position of the Entity.
     * @return position in tiles
     */
    Coordinates getGridPosition();

    /**
     * Gets the direction the Entity is facing.
     *
     * @return
     */
    Direction getDirection();
    
    /**
     * Allows for the Entity to move or perform their behaviors.
     */
    void update();
    
    /**
     * Reacts to the Entity touching an explosion.
     * @param explosion 
     */
    void explode(ExplosionIfc explosion);
}