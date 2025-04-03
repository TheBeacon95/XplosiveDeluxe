package common.Time;

import common.*;
import java.util.List;

/**
 *
 * @author Yanick
 */
public class Timer {

    public static Timer getNewPausableTimer(Duration duration) {
        return new Timer(true, duration);
    }

    public static Timer getNewUnpausableTimer(Duration duration) {
        return new Timer(false, duration);
    }

    public void addTimerDoneListener(TimerDoneListenerIfc listener) {
        m_listeners.add(listener);
    }

    public void start() {
        s_timerManager.startTimer(this);
    }

    public void onTimerDone() {
        for (TimerDoneListenerIfc listener : m_listeners) {
            listener.onTimerDone(this);
        }
    }

    /**
     *
     */
    private Timer(boolean isPausable, Duration duration) {
        if (s_timerManager == null) {
            s_timerManager = new TimerManager();
        }

        if (isPausable) {
            s_timerManager.registerPausable(this, duration);
        }
        else {
            s_timerManager.registerUnpausable(this, duration);
        }
    }

    private List<TimerDoneListenerIfc> m_listeners;
    private static TimerManager s_timerManager = null;
}