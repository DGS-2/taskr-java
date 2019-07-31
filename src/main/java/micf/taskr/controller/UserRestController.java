package micf.taskr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micf.taskr.domain.user.UserDto;
import micf.taskr.domain.user.User;

import micf.taskr.service.user.UserServiceImpl;
import micf.taskr.validation.MapValidationError;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UserRestController {

    @Autowired
    MapValidationError mapValidationError;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        // Validate that passwords match

        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        final User newUser = userService.saveUser(userDto);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}