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

    public DisplayService() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Xplosive Deluxe");

        m_gamePanel = new GamePanel();
        window.add(m_gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
//        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
//        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
//        startGameThread();
    }

//    public void startGameThread() {
//        m_gameThread = new Thread(this);
//        m_gameThread.start();
//    }

//    @Override
//    public void run() {
//        double drawInterval = 1000 * 1000 * 1000 / FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        long timer = 0;
//        int drawCount = 0;
//
//        while (m_gameThread != null) {
//
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                m_gamePanel.repaint();
//                delta--;
//                drawCount++;
//            }
//
//            if (timer >= 1000 * 1000 * 1000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
//    }

//    private void update() {
//        m_entityManagementService.updateEntities();
//    }
    
//    private Thread m_gameThread;
    private GamePanel m_gamePanel;
//    private EntityManagementServiceIfc m_entityManagementService;
//    private StageManagementServiceIfc m_stageManagementService;

//    private final double FPS = 60;

    @Override
    public void draw() {
        m_gamePanel.repaint();
    }

    @Override
    public String getId() {
        return UiNames.Services.DisplayService;
    }
}