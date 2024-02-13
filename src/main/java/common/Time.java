package common;

/**
 *
 * @author Yanick
 */
public final class Time {

    /**
     * Creates a time value for the current time.
     * @return
     */
    public static Time now() {
        return new Time(System.nanoTime());
    }


    public static Time fromDuration(Duration duration) {
        return fromNanoseconds(duration.getNanoseconds());
    }

    /**
     * Creates a time value for the time in a specified amount of seconds.
     * @param seconds
     * @return
     */
    public static Time fromSeconds(long seconds) {
        return new Time(System.nanoTime() + seconds * TimeUtil.SECONDS_TO_NANOSECONDS);
    }

    /**
     * Creates a time value for the time in a specified amount of nanoseconds.
     * @param nanoseconds
     * @return
     */
    public static Time fromNanoseconds(long nanoseconds) {
        return new Time(System.nanoTime() + nanoseconds);
    }

    /**
     * Shows if a the time value is in the past.
     * @return true if the time is in the past.
     */
    public boolean isOver() {
        return System.nanoTime() > m_timeVal;
    }

    private Time(long timeVal) {
        m_timeVal = timeVal;
    }

    private final long m_timeVal;
}