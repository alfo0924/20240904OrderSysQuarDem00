package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.User;
import com.example._20240904ordersysquardem00.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginn() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (userService.isUsernameTaken(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "使用者名稱已被使用，請更換");
        }

        if (userService.isEmailTaken(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "此電子信箱已被註冊過了");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerNewUser(user);
        redirectAttributes.addFlashAttribute("message", "註冊成功！5秒後將自動跳轉到登入頁面。");
        return "redirect:/register-success";
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "register-success";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "login-success";
    }
}