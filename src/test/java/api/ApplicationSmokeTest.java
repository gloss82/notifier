package api;

import api.controllers.PingController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author obondar82@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationSmokeTest {

    @Autowired
    private PingController controller;

    @Test
    public void applicationContextShouldBeLoaded() {
        assertThat(controller).isNotNull();
    }
}
