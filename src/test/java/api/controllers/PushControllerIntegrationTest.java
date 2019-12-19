package api.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author obondar82@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PushControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validSubscriptionCreateOrUpdateOK() throws Exception {
        this.mockMvc.perform(post("/push/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phone\": \"79630429597\", \"token\": \"ievie5AeS3MeChomerohgahph8joo1Igh7aefooweeRai5ohCaehoo\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void invalidSubscriptionCreateOrUpdateFail() throws Exception {
        this.mockMvc.perform(post("/push/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phone\": \"79630429597\", \"token\": \"\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteSubscriptionAlwaysOK() throws Exception {
        this.mockMvc.perform(delete("/push/ievie5AeS3MeChomerohgahph8joo1Igh7aefooweeRai5ohCaehoo"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}