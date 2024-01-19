package game_Impl.gameStates;

import common.stateMachine.*;

/**
 * This class will probably be deleted after testing the win conditions.
 * @author Yanick
 */
public class PlayerWonState extends StateAbs {
    
    public PlayerWonState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_startTime = System.nanoTime();
        m_isDoneWaiting = false;
        m_lastCount = 10;
    }

    @Override
    public void run() {
        long elapsedTime = System.nanoTime() - m_startTime;
        if (elapsedTime > 5l * 1000 * 1000 * 1000) {
            System.out.println("0!");
            m_isDoneWaiting = true;
        }
        else if (m_lastCount > 1 && elapsedTime > 4l * 1000 * 1000 * 1000) {
            System.out.println("1");
            m_lastCount = 1;
        }
        else if (m_lastCount > 2 && elapsedTime > 3l * 1000 * 1000 * 1000) {
            System.out.println("2");
            m_lastCount = 2;
        }
        else if (m_lastCount > 3 && elapsedTime > 2l * 1000 * 1000 * 1000) {
            System.out.println("3");
            m_lastCount = 3;
        }
        else if (m_lastCount > 4 && elapsedTime > 1l * 1000 * 1000 * 1000) {
            System.out.println("4");
            m_lastCount = 4;
        }
    }

    public boolean isDoneWaiting() {
        return m_isDoneWaiting;
    }

    private int m_lastCount;
    private long m_startTime;
    private boolean m_isDoneWaiting;
    
    public static final String STATE_NAME = "PlayerWonState";
}