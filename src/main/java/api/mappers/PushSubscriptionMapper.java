package api.mappers;

import api.entities.PushSubscription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Push subscription entity mapper
 *
 * @author obondar82@gmail.com
 */
public interface PushSubscriptionMapper {

    /**
     * Select SQL
     *
     * @param phone subscriber phone number
     * @return PushSubscription entity instance
     */
    @Select("SELECT phone, created, updated, token, active " +
            "FROM push_subscription " +
            "WHERE phone = #{phone}")
    PushSubscription findByPhone(@Param("phone") String phone);

    /**
     * Exists SQL
     *
     * @param phone subscriber phone number
     * @return true if subscription exists, false otherwise
     */
    @Select("SELECT EXISTS(SELECT 1 FROM push_subscription WHERE phone = #{phone})")
    boolean existsByPhone(@Param("phone") String phone);

    /**
     * Insert SQL
     *
     * @param pushSubscription subscription to insert
     */
    @Insert("INSERT INTO push_subscription (phone, created, updated, token, active) " +
            "VALUES (#{phone}, #{created}, #{updated}, #{token}, #{active})")
    public void create(PushSubscription pushSubscription);

    /**
     * Update SQL
     *
     * @param pushSubscription subscription to insert
     */
    @Update("UPDATE push_subscription " +
            "SET token = #{token}, active = #{active}, updated = (now() at time zone 'utc') " +
            "WHERE phone = #{phone}")
    public void update(PushSubscription pushSubscription);

    /**
     * Activate subscription by token SQL
     *
     * @param token subscribe token
     */
    @Update("UPDATE push_subscription " +
            "SET #{active} = true, updated = (now() at time zone 'utc') " +
            "WHERE token = #{token} AND active <> true")
    public void activateByToken(@Param("token") String token);

    /**
     * Deactivate subscription by token SQL
     *
     * @param token subscribe token
     */
    @Update("UPDATE push_subscription " +
            "SET active = false, updated = (now() at time zone 'utc') " +
            "WHERE token = #{token} AND active <> false")
    public void deactivateByToken(@Param("token") String token);
}
