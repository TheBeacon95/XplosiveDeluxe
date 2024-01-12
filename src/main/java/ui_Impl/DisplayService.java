package ui_Impl;

import javax.swing.JFrame;
import ui_Interfaces.DisplayServiceIfc;
import ui_Interfaces.KeyHandlerIfc;
import ui_Interfaces.UiNames;

/**
 * This agent is responsible for creating and updating the game frame.
 *
 * @author Yanick
 */
public final class DisplayService implements DisplayServiceIfc {

    @Override
    public synchronized void draw() {
        m_gamePanel.repaint();
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

        m_gamePanel = new GamePanel();
        m_window.add(m_gamePanel);

        m_window.pack();

        m_window.setLocationRelativeTo(null);
        m_window.setVisible(true);
    }
    
    @Override
    public void attachKeyHandler(KeyHandlerIfc keyHandler) {
        if (m_gamePanel != null) {
            m_gamePanel.addKeyListener(keyHandler);
        }
    }
    
    private GamePanel m_gamePanel;
    private JFrame m_window;
}