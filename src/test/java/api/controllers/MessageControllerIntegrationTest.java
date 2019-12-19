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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author obondar82@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void messageStatusForValidIdOK() throws Exception {
        this.mockMvc.perform(get("/message/eca7b5d8-32a6-4e8c-a36e-b0ebdb96ddab/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("QUEUED")));
    }

    @Test
    public void messageStatusForInvalidIdHTTP404() throws Exception {
        this.mockMvc.perform(get("/message/0ca7b5d8-32a6-4e8c-a36e-b0ebdb96ddab/status"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createValidMessageCreateOK() throws Exception {
        this.mockMvc.perform(post("/message/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phone\": \"79630429597\", \"content\": \"Test message1\", \"importance\": \"INFO\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void createInvalidMessageCreateFail() throws Exception {
        this.mockMvc.perform(post("/message/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phone\": \"79630429597\", \"content\": \"\", \"importance\": \"INFO\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badJsonCheckFail() throws Exception {
        this.mockMvc.perform(post("/message/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phone': \"79630429597\", \"content\": \"\", \"importance\": \"INFO\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}