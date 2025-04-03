package common.Time;

import common.*;
import java.util.HashMap;

/**
 *
 * @author Yanick
 */
public class TimerManager {

    TimerManager() {
        m_pausableTimers = new HashMap<>();
        m_unpausableTimers = new HashMap<>();
    }

    void startTimer(int m_id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    int registerPausable(Timer timer, Duration duration) {
        m_pausableTimers.put(m_pausableTimersCount++, duration);
    }

    int registerUnpausable(Timer aThis, Duration duration) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private final HashMap<Integer, Duration> m_pausableTimers;
    private final HashMap<Integer, Duration> m_unpausableTimers;

    private int m_pausableTimersCount;
    private int m_unpausableTimersCount;
}