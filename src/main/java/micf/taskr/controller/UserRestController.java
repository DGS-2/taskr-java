package micf.taskr.controller;

import micf.taskr.domain.user.*;
import micf.taskr.service.user.UserServiceImpl;

// import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import micf.taskr.security.user.*;
import micf.taskr.service.user.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    ActiveUserStore activeUserStore;

    @Autowired
    UserServiceImpl userService;


    @GetMapping(value = "/users")
    public String getLoggedUsers(final Locale locale, final Model model) {
        model.addAttribute("users", activeUserStore.getUsers());
        return "users";
    }

    @GetMapping(value = "/loggedUsersFromSessionRegistry")
    public String getLoggedUsersFromSessionRegistry(final Locale locale, final Model model) {
        model.addAttribute("users", userService.getUsersFromSessionRegistry());
        return "users";
    }
}