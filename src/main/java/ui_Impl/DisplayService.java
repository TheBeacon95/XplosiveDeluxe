package ui_Impl;

import common.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ui_Interfaces.*;

/**
 * This agent is responsible for creating and updating the game frame.
 *
 * @author Yanick
 */
public final class DisplayService implements DisplayServiceIfc {

//    private JPanel m_overlayPanel;

    @Override
    public synchronized void draw() {
        if (m_gamePanel != null) {
            m_gamePanel.repaint();
        }
    }

    @Override
    public String getId() {
        return UiNames.Services.DisplayService;
    }

    @Override
    public void initializeService() {
        m_window = new JFrame();
        m_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_window.setResizable(false);
        m_window.setTitle("Xplosive Deluxe");

        m_gamePanel = new GamePanelAbs() {};
//        m_overlayPanel = new JPanel();
//        m_overlayPanel.setForeground(Color.BLACK);
        ScreenServiceIfc screenService = (ScreenServiceIfc) ServiceManager.getService(UiNames.Services.ScreenService);
//        m_overlayPanel.setPreferredSize(new Dimension(screenService.getStageWidth(), screenService.getStageHeight()));

        m_window.add(m_gamePanel);
//        m_window.add(m_overlayPanel, 1);

        m_window.pack();

        m_window.setLocationRelativeTo(null);
        m_window.setVisible(true);

        m_syncDrawing = new Object();
    }

    @Override
     public void waitForDrawing() {
        try {
            synchronized (m_syncDrawing) {
                m_syncDrawing.wait();
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(DisplayService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notifyDrawingDone() {
        synchronized (m_syncDrawing) {
            m_syncDrawing.notify();
        }
    }

    @Override
    public void setPanel(GamePanelAbs gamePanel) {
        m_window.remove(m_gamePanel);
        m_gamePanel = gamePanel;
        m_window.add(m_gamePanel);
        m_gamePanel.requestFocusInWindow();
        m_window.pack();
    }

    @Override
    public void setOpacityLevel(float level) {
        m_gamePanel.setAlpha(level);
    }

    private GamePanelAbs m_gamePanel;
    private JFrame m_window;
    private Object m_syncDrawing;
}