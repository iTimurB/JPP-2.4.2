package spring_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_crud.dao.UserDao;
import spring_crud.model.User;
import spring_crud.security.SecurityConfig;
import spring_crud.service.UserDetailsServiceImpl;
import spring_crud.service.UserService;
import spring_crud.service.UserServiceImpl;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("")
    public String show(ModelMap modelMap, Principal principal) {
        User userDetails = (userDetailsService.findUserName(principal.getName()));
        User user = new User(userDetails.getId(), userDetails.getName(), userDetails.getAge(), userDetails.getEmail(), userDetails.getRoles());

        modelMap.addAttribute("user", user);
        return "show_user";
    }
}