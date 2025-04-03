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

    void startTimer(Timer timer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void registerPausable(Timer timer, Duration duration) {
        m_pausableTimers.put(timer, duration);
    }

    void registerUnpausable(Timer timer, Duration duration) {
        m_unpausableTimers.put(timer, duration);
    }

    private final HashMap<Timer, Duration> m_pausableTimers;
    private final HashMap<Timer, Duration> m_unpausableTimers;
}