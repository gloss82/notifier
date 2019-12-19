package api.entities;

import java.time.LocalDate;

/**
 * Pus subscription entity
 *
 * @author obondar82@gmail.com
 */
public class PushSubscription {

    /**
     * Recipient phone number
     */
    private String phone;

    /**
     * Push subscription token
     */
    private String token;

    /**
     * Create date
     */
    private LocalDate created;

    /**
     * Update date
     */
    private LocalDate updated;

    /**
     * Subscription active flag
     */
    private boolean active;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
