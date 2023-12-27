package level_Interfaces;

import common.Coordinates;
import common.Direction;
import common.ServiceIfc;
import java.util.List;

/**
 * Allows all moving entities to get the direction, they can go in.
 * @author Yanick
 */
public interface MovementServiceIfc extends ServiceIfc {
    
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
    
    /**
     * Shows whether a position is between blocks either horizontally or vertically.
     * @param position
     * @return 
     */
    boolean isBetweenCells(Coordinates position);
    
    /**
     * Gets a list of all neighboring cells that are walkable.
     * @param position
     * @return 
     */
    List<Coordinates> getAllFreeNeighboringCells(Coordinates position);
    
    /**
     * Shows how many steps can be taken between two blocks.
     * @return 
     */
    int getBlockSegments();
}