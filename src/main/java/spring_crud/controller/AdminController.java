package spring_crud.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_crud.model.Role;
import spring_crud.model.User;
import spring_crud.service.RoleService;
import spring_crud.service.UserDetailsServiceImpl;
import spring_crud.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin_page")
    public String adminPage(ModelMap modelMap, Principal principal) {
        User userDetails = userService.getUserByName(principal.getName());
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
    public String getUserById(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUserById(id));
        return "show_user_page";
    }

    @GetMapping("/user/new")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("newUser", new User());
        modelMap.addAttribute("getRoles", roleService.getListRole());
        return "new";
    }

    @PostMapping("/user/new")//исправить
    public String createUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, @RequestParam("role") String[] role) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        user.setRoles(editUserRoles(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUserById(id));
        modelMap.addAttribute("roles", roleService.getListRole());
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam(value = "role") String[] role) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        user.setRoles(editUserRoles(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user.getId(), user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    public Set<Role> editUserRoles(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            Role currentRole = roleService.getRoleByName(roles);
            roleSet.add(currentRole);
        }
        return roleSet;
    }

}