package common;

/**
 * This interface is only used for grouping purposes.
 */
public interface ServiceIfc extends IdentifiableIfc {
    /**
     * Gets called after every service has been created.
     */
    void initializeService();
}
