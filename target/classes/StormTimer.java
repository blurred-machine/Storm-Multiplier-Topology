
import java.util.concurrent.TimeUnit;
import org.apache.storm.utils.Time;


public class StormTimer {
    private final long delay;
    private final long period;
    private final TimeUnit timeUnit;
    private final long periodNanos;
    private long start;

    /**
     * Creates a class that mimics a single threaded timer that expires periodically. If a call to {@link
     * #isExpiredResetOnTrue()} occurs later than {@code period} since the timer was initiated or reset, this method returns
     * true. Each time the method returns true the counter is reset. The timer starts with the specified time delay.
     *
     * @param delay    the initial delay before the timer starts
     * @param period   the period between calls {@link #isExpiredResetOnTrue()}
     * @param timeUnit the time unit of delay and period
     */


    public StormTimer(long delay, long period, TimeUnit timeUnit) {
        this.delay = delay;
        this.period = period;
        this.timeUnit = timeUnit;

        periodNanos = timeUnit.toNanos(period);
        start = Time.nanoTime() + timeUnit.toNanos(delay);
    }

    public long period() {
        return period;
    }

    public long delay() {
        return delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Checks if a call to this method occurs later than {@code period} since the timer was initiated or reset. If that is the
     * case the method returns true, otherwise it returns false. Each time this method returns true, the counter is reset
     * (re-initiated) and a new cycle will start.
     *
     * @return true if the time elapsed since the last call returning true is greater than {@code period}. Returns false
     *     otherwise.
     */
    public boolean isExpiredResetOnTrue() {
        final boolean expired = Time.nanoTime() - start >= periodNanos;
        if (expired) {
            start = Time.nanoTime();
        }
        return expired;
    }
}
