package ui_Interfaces;

import common.ServiceIfc;

/**
 *
 * @author Yanick
 */
public interface DisplayServiceIfc extends ServiceIfc {

    /**
     * Draws the currently active panel.
     */
    void draw();

    void waitForDrawing();

    void notifyDrawingDone();

    /**
     * Gives the DisplayService a new panel that will be drawn.
     * @param GamePanel
     */
    void setPanel(GamePanelAbs GamePanel);

    /**
     * Sets how well the GamePanel can be seen.
     * @param level is the clarity. 0 is fully black, 100 is fully visible.
     */
    public void setOpacityLevel(float level);
}