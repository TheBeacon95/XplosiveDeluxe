package ui_Impl;

import javax.swing.JFrame;
import ui_Interfaces.DisplayServiceIfc;
import ui_Interfaces.UiNames;

/**
 * This agent is responsible for creating and updating the game frame.
 *
 * @author Yanick
 */
public final class DisplayService implements DisplayServiceIfc {

    @Override
    public void draw() {
        m_gamePanel.repaint();
    }

    @Override
    public String getId() {
        return UiNames.Services.DisplayService;
    }

    @Override
    public void initializeService() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Xplosive Deluxe");

        m_gamePanel = new GamePanel();
        window.add(m_gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private GamePanel m_gamePanel;
}