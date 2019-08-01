package micf.taskr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micf.taskr.domain.user.UserDto;
import micf.taskr.payload.JWTLoginSucessReponse;
import micf.taskr.payload.LoginRequest;
import micf.taskr.security.jwt.JwtTokenProvider;
import micf.taskr.domain.user.User;

import micf.taskr.service.user.UserServiceImpl;
import micf.taskr.validation.MapValidationError;

import static micf.taskr.config.SecurityConstraints.TOKEN_PREFIX;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UserRestController {

    @Autowired
    MapValidationError mapValidationError;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        // Validate that passwords match

        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        final User newUser = userService.saveUser(userDto);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationError.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }
}