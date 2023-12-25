package level_Interfaces;

import common.Coordinates;
import common.Direction;

/**
 * Allows all moving entities to get the direction, they can go in.
 * @author Yanick
 */
public interface MovementServiceIfc {
    
    /**
     * Returns the next direction a hostile entity should take next.
     * @param position position of the entity
     * @param direction direction the entity is currently facing
     * @return the direction, the entity will go in next
     */
    public Direction getNextHostileDirection(Coordinates position, Direction direction);
    
    /**
     * Returns the next direction a randomly moving entity should take next.
     * @param position position of the entity
     * @param direction direction the entity is currently facing
     * @return the direction, the entity will go in next
     */
    public Direction getNextRandomDirection(Coordinates position, Direction direction);
    
    /**
     * Converts the input direction into another direction, so corners can be rounded.
     * @param position position of the entity
     * @param direction the desired direction of the entity
     * @return the actual direction the entity will move in.
     */
    public Direction convertDesiredDirection(Coordinates position, Direction direction);
}