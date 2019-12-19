package api.mappers;

import api.entities.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

/**
 * Message entity mapper
 *
 * @author obondar82@gmail.com
 */
public interface MessageMapper {

    /**
     * Select SQL
     *
     * @param id message identity
     * @return Message instance
     */
    @Select("SELECT m.id as idStr, m.phone, m.content, m.created, m.updated, " +
            "s.name as status, i.name as importance " +
            "FROM message m " +
            "LEFT JOIN message_status s " +
            "  ON s.id = m.status " +
            "LEFT JOIN message_importance i " +
            "  ON i.id = m.importance " +
            "WHERE m.id = #{id}")
    Message findById(@Param("id") UUID id);

    /**
     * Insert SQL
     *
     * @param message message to insert
     */
    @Insert("INSERT INTO message (id, phone, created, content, updated, status, importance) " +
            "SELECT #{idStr}::uuid, #{phone}, #{created}, #{content}, #{updated}, s.id, i.id " +
            "FROM message_status s, message_importance i " +
            "WHERE i.name = #{importance} AND s.name = #{status}")
    public void create(Message message);
}
