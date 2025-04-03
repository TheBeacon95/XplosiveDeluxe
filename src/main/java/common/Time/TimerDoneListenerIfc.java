package common.Time;

/**
 *
 * @author Yanick
 */
interface TimerDoneListenerIfc {

    /**
     * Gets called whenever the timer is up.
     * @param thisTimer
     */
    public void onTimerDone(Timer thisTimer);

}
