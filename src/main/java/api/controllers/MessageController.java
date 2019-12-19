package api.controllers;

import api.entities.Message;
import api.enums.MessageImportance;
import api.enums.MessageStatus;
import api.exceptions.ValidationException;
import api.mappers.MessageMapper;
import api.models.MessageSendRequest;
import api.models.MessageStatusResponse;
import api.validators.ContentValidator;
import api.validators.PhoneValidator;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * Controller for sending messages to client
 *
 * @author obondar82@gmail.com
 */
@RestController
@RequestMapping(value = "message", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    /**
     * Message mapper for database requests handling
     */
    @Autowired
    MessageMapper messageMapper;

    /**
     * Method send message to client
     * All exceptions within this method are mapped to suitable REST response
     *
     * @return HTTP 201 if message successfully queued (id of message contains in HTTP location header)
     * @throws ValidationException When input data aren't valid
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> send(
            @RequestBody MessageSendRequest rq, HttpServletResponse srs) throws ValidationException {

        // Validate request data
        PhoneValidator.validate(rq.getPhone());
        ContentValidator.validate(rq.getContent());

        // Set default value of importance if not specified
        if (rq.getImportance() == null) {
            rq.setImportance(MessageImportance.INFO);
        }

        // Instantiate Message
        Message message = new Message(UUID.randomUUID());

        // Set message properties
        message.setPhone(rq.getPhone());
        message.setContent(rq.getContent());
        message.setCreated(LocalDate.now(ZoneOffset.UTC));
        message.setStatus(MessageStatus.QUEUED);
        message.setImportance(rq.getImportance());

        // Save message to database
        messageMapper.create(message);

        // TODO: Put message to MQ for processing

        // Create location (URL) of created message according to REST specification
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getId())
                .toUri();

        // Return response with HTTP code 201 CREATED
        return ResponseEntity.created(location).build();
    }

    /**
     * Method returns current status of message
     *
     * @param id Message uuid
     * @return Status as MessageStatusResponse model
     * @throws NotFoundException When message with specified id is not found
     */
    @GetMapping(value = "/{id}/status")
    public MessageStatusResponse status(@PathVariable UUID id) throws NotFoundException {

        // Select message from database by request id
        Message message = messageMapper.findById(id);

        if (message == null) {
            // No rows returned from database
            throw new NotFoundException("Message not found");
        }

        // Create and return response
        return new MessageStatusResponse(message.getStatus());
    }
}
