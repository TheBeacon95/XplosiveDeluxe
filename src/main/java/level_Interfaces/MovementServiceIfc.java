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
     * Converts the input direction into another direction, so corners can be rounded.
     * @param position position of the entity
     * @param direction the desired direction of the entity
     * @param isGhost true if the entity is able to phase through blocks.
     * @return the actual direction the entity will move in.
     */
    public Direction convertDesiredDirection(Coordinates position, Direction direction, boolean isGhost);
    
    /**
     * Shows whether a position is between blocks either horizontally or vertically.
     * @param globalPosition
     * @return 
     */
    boolean isBetweenCells(Coordinates globalPosition);
    
    /**
     * Gets a list of all neighboring cells that are walkable.
     * @param gridPosition
     * @return 
     */
    List<Coordinates> getAllFreeNeighboringCells(Coordinates gridPosition);
    
    /**
     * Gets a list of all neighboring cells that are eatable.
     * @param gridPosition
     * @return 
     */
    public List<Coordinates> getAllEatableNeighboringCells(Coordinates gridPosition);
    
    /**
     * Shows how many steps can be taken between two blocks.
     * @return 
     */
    int getBlockSegments();
}