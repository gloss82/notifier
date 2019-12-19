package api.entities;

import api.enums.MessageImportance;
import api.enums.MessageStatus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Message entity
 *
 * @author obondar82@gmail.com
 */
public class Message {

    /**
     * Message unique identifier
     */
    private UUID id;

    /**
     * Recipient phone number
     */
    private String phone;

    /**
     * Message content
     */
    private String content;

    /**
     * Create date
     */
    private LocalDate created;

    /**
     * Update date
     */
    private LocalDate updated;

    /**
     * Current status
     */
    private MessageStatus status;

    /**
     * Message importance
     */
    private MessageImportance importance;

    public UUID getId() {
        return id;
    }

    public String getIdStr() {
        return id == null ? null : id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdStr(String id) {
        this.id = UUID.fromString(id);
    }

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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public MessageImportance getImportance() {
        return importance;
    }

    public void setImportance(MessageImportance importance) {
        this.importance = importance;
    }

    public Message() {
    }

    public Message(UUID id) {
        this.id = id;
    }
}
