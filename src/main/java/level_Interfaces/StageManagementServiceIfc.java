package level_Interfaces;

import common.ServiceIfc;
import java.awt.Graphics2D;

/**
 *
 * @author Yanick
 */
public interface StageManagementServiceIfc extends ServiceIfc {
    
    /**
     * Prepares the stage to be played.
     * @param level
     */
    void setStage(Level level);
    
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
     * Sets the Background sprite
     * @param style the style where the Background is under.
     */
    public void setBackground(String style);
    
    /**
     * Todo: describe.
     * @return 
     */
    boolean isReady();
}