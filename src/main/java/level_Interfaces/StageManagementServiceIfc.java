package level_Interfaces;

import common.*;
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
    
    /**
     * Places a bomb at a specific position.
     * @param bombType the type of the bomb
     * @param position where the bomb will be placed
     * @param strength how far the explosion will reach
     */
    void placeBomb(BombType bombType, Coordinates position, int strength);
    
    /**
     * Rounds the position up or down to grid position.
     * @param position position to be rounded
     * @return 
     */
    Coordinates roundToGridPosition(Coordinates position);

    /**
     * Explodes the block if there is one.
     * @param gridPosition
     */
    void explode(Coordinates gridPosition);
    
    /**
     * Shows if a space is occupied by a block that stops explosions.
     * @param gridPosition
     * @return true if there's an explosion stopper. false otherwise.
     */
    boolean isExplosionStopper(Coordinates gridPosition);
}