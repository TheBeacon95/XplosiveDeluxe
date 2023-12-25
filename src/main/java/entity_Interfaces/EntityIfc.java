package entity_Interfaces;

import common.Animation;
import common.Coordinates;
import common.Direction;

public interface EntityIfc {

    /**
     * Gets the position of the Entity
     *
     * @return position in tiles
     */
    Coordinates getPosition();

    /**
     * Gets the direction the Entity is facing.
     *
     * @return
     */
    Direction getDirection();

//    /**
//     * Gets the Animation, that should currently be played.
//     *
//     * @return
//     */
//    Animation getAnimation();
    
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
