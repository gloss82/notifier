package api.models;

/**
 * Push subscribe request class
 *
 * @author obondar82@gmail.com
 */
public class PushSubscribeRequest {

    /**
     * Recipient phone number
     */
    private String phone;

    /**
     * Token for sending push messages (generated at client application)
     */
    private String token;

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
}
