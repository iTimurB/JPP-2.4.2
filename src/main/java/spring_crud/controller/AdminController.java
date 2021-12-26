package spring_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_crud.model.User;
import spring_crud.service.RoleService;
import spring_crud.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController( UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin_page")
    public String adminPage(ModelMap modelMap, Principal principal) {
        User userDetails = userService.getUserByNameWithRoles(principal.getName());
        modelMap.addAttribute("user", userDetails);
        return "admin_page";
    }

    @GetMapping("/users")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") long userId, ModelMap modelMap) {
        modelMap.addAttribute("editUser", userService.showUserById(userId));
        return "show_user_page";
    }

    @GetMapping("/user/new")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, @RequestParam("rolesFromCreateUser") List<Long> roleIds) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        user.setRoles(roleService.getRolesByIds(roleIds));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") long userId, ModelMap modelMap) {
        modelMap.addAttribute("editUser", userService.showUserById(userId));
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@ModelAttribute("editUser") @Valid User user, BindingResult bindingResult, @RequestParam(value = "rolesFromEditUser") List<Long> roleIds) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        user.setRoles(roleService.getRolesByIds(roleIds));
        userService.update(user.getId(), user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long userId) {
        userService.delete(userId);
        return "redirect:/admin/users";
    }
}