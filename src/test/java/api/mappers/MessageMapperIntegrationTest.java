package api.mappers;


import api.entities.Message;
import api.enums.MessageImportance;
import api.enums.MessageStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author obondar82@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageMapperIntegrationTest {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void givenValidMessage_saveLoadOK() {
        // Instantiate new Message
        Message message = new Message();

        // Set message properties
        message.setId(UUID.randomUUID());
        message.setPhone("79630429597");
        message.setContent("This is test message!");
        message.setCreated(LocalDate.now(ZoneOffset.UTC));
        message.setStatus(MessageStatus.DEAD);
        message.setImportance(MessageImportance.INFO);

        // Save message to database
        messageMapper.create(message);
        Message message2 = messageMapper.findById(message.getId());

        assertThat(message.getId()).isEqualTo(message2.getId());
        assertThat(message.getPhone()).isEqualTo(message2.getPhone());
        assertThat(message.getContent()).isEqualTo(message2.getContent());
        assertThat(message.getStatus()).isEqualTo(message2.getStatus());
        assertThat(message.getImportance()).isEqualTo(message2.getImportance());
    }
}