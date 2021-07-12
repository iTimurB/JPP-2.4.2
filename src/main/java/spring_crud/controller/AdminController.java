package spring_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/index")
    public String index(ModelMap modelMap, Principal principal){
        User userDetails = (userDetailsService.findUserName(principal.getName()));
        User user = new User(userDetails.getId(), userDetails.getName(), userDetails.getAge(), userDetails.getEmail(), userDetails.getRoles());

        modelMap.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/users")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "users";
    }

    @GetMapping("/{id}")
    public String showUserPage(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUser(id));
        return "admin";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("addUser") User user, ModelMap modelMap) {
        List<Role> roles = roleService.getListRole();
        User newUser = new User ();
        modelMap.addAttribute("newUser", newUser);
        modelMap.addAttribute("getRoles", roles);
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("addUser") @Valid User user, @RequestParam("role") String [] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role){
            Role currentRole = roleService.getRoleByName(roles);
            roleSet.add(currentRole);
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "/edit";
        }
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}