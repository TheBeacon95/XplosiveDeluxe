package ui_Impl;

import level_Interfaces.LevelNames;
import level_Interfaces.StageManagementServiceIfc;
import common.ServiceManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ui_Interfaces.ScreenServiceIfc;
import ui_Interfaces.UiNames;

public class GamePanel extends JPanel {

    private ScreenServiceIfc m_screenService;
    private int x, y;
    private KeyHandler m_keyHandler;

    public GamePanel() {
        m_screenService = (ScreenService) ServiceManager.getService(UiNames.Services.ScreenService);
        setPreferredSize(new Dimension(m_screenService.getStageWidth(), m_screenService.getStageHeight()));
        setBackground(Color.black);
        setDoubleBuffered(true);
        m_keyHandler = new KeyHandler();
        addKeyListener(m_keyHandler);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
//        EntityManagementServiceIfc entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
//        entityManagementService.drawEntities(g2);
        StageManagementServiceIfc stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        if (stageManagementService != null) stageManagementService.draw(g2);
        g2.dispose();
    }
}
