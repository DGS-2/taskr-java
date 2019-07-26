package micf.taskr.controller;

import micf.taskr.domain.user.*;
import micf.taskr.service.user.UserServiceImpl;

// import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // Get all tasks
    @GetMapping("/users")
    public Collection<User> findAll() {
        return userService.findAll();
    }

    // Get task by id
    @GetMapping("/user/{id}")
    public User getTask(@PathVariable String id) {
        User theTask = userService.findById(id);
        
        if(theTask == null) {
            throw new RuntimeException("Task id not found - " + id);
        }

        return theTask;
    }
}