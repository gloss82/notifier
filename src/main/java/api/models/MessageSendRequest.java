package api.models;

import api.enums.MessageImportance;

/**
 * Request for message sending
 *
 * @author obondar82@gmail.com
 */
public class MessageSendRequest {

    /**
     * Recipient phone number
     */
    private String phone;

    /**
     * Message content
     */
    private String content;

    /**
     * Message importance (default = INFO)
     */
    private MessageImportance importance = MessageImportance.INFO;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageImportance getImportance() {
        return importance;
    }

    public void setImportance(MessageImportance importance) {
        this.importance = importance;
    }
}
