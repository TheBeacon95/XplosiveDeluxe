package ui_Interfaces;

import common.ServiceIfc;

/**
 *
 * @author Yanick
 */
public interface DisplayServiceIfc extends ServiceIfc {
    void draw();

    void attachKeyHandler(KeyHandlerIfc keyHandler);

    /**
     * Shows if the Game is currently busy drawing.
     * @return true: busy - false: waiting.
     */
//    boolean isDrawing();

    void waitForDrawing();

    void notifyDrawingDone();
}