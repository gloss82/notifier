package api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping controller
 *
 * @author obondar82@gmail.com
 */
@RestController
public class PingController {

    /**
     * Method just returns "OK" string on every call
     * using "text/plain" content-type
     *
     * @return "OK" string
     */
    @RequestMapping(value = "/ping", produces = "text/plain")
    public String ping() {
        return "OK";
    }
}
