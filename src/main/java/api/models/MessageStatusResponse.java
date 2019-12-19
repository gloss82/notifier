package api.models;

import api.enums.MessageStatus;

/**
 * Message status response
 *
 * @author obondar82@gmail.com
 */
public class MessageStatusResponse {

    /**
     * Actual message status
     */
    private MessageStatus status;

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public MessageStatusResponse(MessageStatus status) {
        this.status = status;
    }
}
