package common;

/**
 *
 * @author Yanick
 */
public final class Duration {


    public static Duration fromSeconds(long seconds) {
        return new Duration(seconds * TimeUtil.SECONDS_TO_NANOSECONDS);
    }


    public static Duration fromNanoseconds(long nanoseconds) {
        return new Duration(nanoseconds);
    }

    public long getSeconds() {
        return m_duration / TimeUtil.SECONDS_TO_NANOSECONDS;
    }

    public long getNanoseconds() {
        return m_duration;
    }

    private Duration(long nanoseconds) {
        m_duration = nanoseconds;
    }

    private final long m_duration;
}
