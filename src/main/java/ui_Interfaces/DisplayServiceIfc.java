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
}