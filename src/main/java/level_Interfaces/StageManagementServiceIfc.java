package level_Interfaces;

import common.Coordinates;
import common.ServiceIfc;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Yanick
 */
public interface StageManagementServiceIfc extends ServiceIfc {
    /**
     * Updates all blocks in the stage.
     */
    void updateStage();
    
    /**
     * Draws all the blocks in the stage and the background.
     * @param g2 Graphics2D instance needed to draw.
     */
    void draw(Graphics2D g2);
    
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
}