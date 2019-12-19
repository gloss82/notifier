package api.enums;

/**
 * Outcome message statuses
 *
 * @author obondar82@gmail.com
 */
public enum MessageStatus {
    UNKNOWN, // Unknown for any reason
    QUEUED, // In queue
    SENT, // Sent to provider
    DELIVERED, // Delivered to recipient
    DEAD // Delivery tries exceeded, fail
}
