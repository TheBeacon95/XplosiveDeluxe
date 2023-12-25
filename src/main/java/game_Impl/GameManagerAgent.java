package game_Impl;

import common.AgentAbs;
import common.ServiceManager;

/**
 *
 * @author Yanick
 */
public class GameManagerAgent extends AgentAbs implements Runnable {

    @Override
    protected void initializeAgents() {
        // Do nothing
    }

    @Override
    protected void initializeServices(ServiceManager serviceManager) {
        // Do nothing
    }

    @Override
    protected void start() {
      m_stateMachine = new GameStateMachine();
      startGameThread();
      m_stateMachine.start();
    }

    public void startGameThread() {
        m_gameThread = new Thread(this);
        m_gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000 * 1000 * 1000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (m_gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
//                m_gamePanel.repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000 * 1000 * 1000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        m_stateMachine.run();
    }
    
    private Thread m_gameThread;
    private GameStateMachine m_stateMachine;
    
    private static final double FPS = 240;
}