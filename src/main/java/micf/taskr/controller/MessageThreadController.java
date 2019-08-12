package micf.taskr.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micf.taskr.domain.task.TaskThreadMessage;
import micf.taskr.service.task.TaskThreadMessageService;
import micf.taskr.validation.MapValidationError;

@RestController
@RequestMapping("/api/message-thread")
@CrossOrigin
public class MessageThreadController {
    
    @Autowired
    private TaskThreadMessageService threadMessageService;

    @Autowired
    private MapValidationError mapValidationError; 

    @PostMapping("/{taskIdentifier}")
    public ResponseEntity<?> addMessageToThread(@Valid @RequestBody TaskThreadMessage message, BindingResult result, @PathVariable String taskIdentifier, Principal principal) {
        
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskThreadMessage newMessage = threadMessageService.addMessageToThread(taskIdentifier, message, principal.getName());

        return new ResponseEntity<TaskThreadMessage>(newMessage, HttpStatus.OK);
    }

    @GetMapping("/{taskIdentifier}")
    public List<TaskThreadMessage> getTaskMessageThread(@PathVariable String taskIdentifier, Principal principal) {
        return threadMessageService.findThreadById(taskIdentifier, principal.getName());
    }

    @GetMapping("/{taskIdentifier}/{message_sequence}")
    public ResponseEntity<?> getMessage(@PathVariable String taskIdentifier, @PathVariable String message_sequence, Principal principal) {
        TaskThreadMessage message = threadMessageService.findMessageBySequence(taskIdentifier, message_sequence, principal.getName());

        return new ResponseEntity<TaskThreadMessage>(message, HttpStatus.OK);
    }

    // Change to PutMapping?
    @PatchMapping("/{taskIdentifier}/{message_sequence}")
    public ResponseEntity<?> updateMessage(@Valid @RequestBody TaskThreadMessage message, BindingResult result, @PathVariable String taskIdentifier, @PathVariable String message_sequence, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        TaskThreadMessage updatedMessage = threadMessageService.updateMessageBySequence(message, taskIdentifier, message_sequence, principal.getName());

        return new ResponseEntity<TaskThreadMessage>(updatedMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{taskIdentifier}/{message_sequence}")
    public ResponseEntity<?> deleteMessage(@PathVariable String taskIdentifier, @PathVariable String message_sequence, Principal principal){
        threadMessageService.deleteMessageBySequence(taskIdentifier, message_sequence, principal.getName());

        return new ResponseEntity<String>("Message was deleted",HttpStatus.OK);
    }
}