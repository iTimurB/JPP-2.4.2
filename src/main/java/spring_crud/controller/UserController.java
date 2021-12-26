package spring_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_crud.model.User;
import spring_crud.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show(ModelMap modelMap, Principal principal) {
        User userDetails = (userService.getUserByNameWithRoles(principal.getName()));
        modelMap.addAttribute("user", userDetails);
        return "show_user";
    }
}