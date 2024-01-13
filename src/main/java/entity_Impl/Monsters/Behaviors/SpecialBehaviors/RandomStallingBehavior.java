package entity_Impl.Monsters.Behaviors.SpecialBehaviors;

import entity_Impl.Monsters.MonsterAbs;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Yanick
 */
public class RandomStallingBehavior implements SpecialBehaviorIfc {

    @Override
    public void start() {
        m_startTime = System.nanoTime();
        m_isStalled = false;
        m_random = new Random();
    }
    
    @Override
    public void perform(MonsterAbs monster) {
        long currentTime = System.nanoTime();
        if (currentTime - m_startTime >= m_currentActionTime) {
            if (m_isStalled) {
                // Randomly select active time
                m_currentActionTime = s_activeTimes.get(m_random.nextInt(s_activeTimes.size()));
            }
            else {
                // Randomly select stall time
                m_currentActionTime = s_stallTimes.get(m_random.nextInt(s_stallTimes.size()));
                
                // Stall monster
                monster.stall(m_currentActionTime);
            }
            m_isStalled = !m_isStalled;
            m_startTime = currentTime;
        }
    }
    
    private static List<Long> s_activeTimes = Arrays.asList(
        25l * 100 * 1000 * 1000,
        30l * 100 * 1000 * 1000,
        35l * 100 * 1000 * 1000,
        40l * 100 * 1000 * 1000,
        45l * 100 * 1000 * 1000,
        50l * 100 * 1000 * 1000,
        55l * 100 * 1000 * 1000,
        60l * 100 * 1000 * 1000
    );
    
    private static List<Long> s_stallTimes = Arrays.asList(
        5l * 100 * 1000 * 1000,
        10l * 100 * 1000 * 1000,
        15l * 100 * 1000 * 1000,
        20l * 100 * 1000 * 1000,
        25l * 100 * 1000 * 1000,
        30l * 100 * 1000 * 1000,
        35l * 100 * 1000 * 1000,
        40l * 100 * 1000 * 1000
    );
    
    private boolean m_isStalled;
    private long m_startTime;
    private long m_currentActionTime;
    private Random m_random;
}
