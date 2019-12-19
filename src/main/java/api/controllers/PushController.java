package api.controllers;

import api.entities.PushSubscription;
import api.exceptions.ValidationException;
import api.mappers.PushSubscriptionMapper;
import api.models.PushSubscribeRequest;
import api.validators.PhoneValidator;
import api.validators.PushTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Push messaging controller
 *
 * @author obondar82@gmail.com
 */
@RestController
@RequestMapping(value = "push", produces = MediaType.APPLICATION_JSON_VALUE)
public class PushController {

    /**
     * Message mapper for database requests handling
     */
    @Autowired
    PushSubscriptionMapper pushSubscriptionMapper;

    /**
     * Method subscribe recipient for push messages
     * All exceptions within this method are mapped to suitable REST response
     *
     * @return HTTP 201 (created) upon successful creation or 200 (ok) upon successful update
     * @throws ValidationException When input data aren't valid
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> subscribe(@RequestBody PushSubscribeRequest rq) throws ValidationException {

        // Validate request data
        PhoneValidator.validate(rq.getPhone());
        PushTokenValidator.validate(rq.getToken());

        // Instantiate Subscription
        PushSubscription subscription = new PushSubscription();

        subscription.setPhone(rq.getPhone());
        subscription.setCreated(LocalDate.now(ZoneOffset.UTC));
        subscription.setToken(rq.getToken());
        subscription.setActive(true);

        boolean subscriptionExists = pushSubscriptionMapper.existsByPhone(subscription.getPhone());

        ResponseEntity.BodyBuilder response;

        if (subscriptionExists) {
            pushSubscriptionMapper.update(subscription);

            // Return response with HTTP code 200 OK
            response = ResponseEntity.ok();

        } else {
            pushSubscriptionMapper.create(subscription);

            // Create location (URL) of created message according to REST specification
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{token}")
                    .buildAndExpand(subscription.getToken())
                    .toUri();

            // Return response with HTTP code 201 CREATED
            response = ResponseEntity.created(location);
        }

        // Return response
        return response.build();
    }

    /**
     * Method deactivate subscription
     *
     * @param token Subscription token
     * @return No content
     */
    @DeleteMapping(path = "/{token}")
    public ResponseEntity<Object> delete(@PathVariable String token) {

        // Deactivate subscription
        pushSubscriptionMapper.deactivateByToken(token);

        // Create and return response
        return ResponseEntity.noContent().build();
    }
}
