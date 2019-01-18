package com.cellar.wine.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//    // from SFG
//    @RequestMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("loginCommand", new LoginCommand());
//        return "security/login";
//    }
//
//    @RequestMapping("logout-success")
//    public String logout() {
//        return "security/logout";
//    }
//
//    @PostMapping("/login")
//    public String doLogin(@Valid LoginCommand loginCommand, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "loginform";
//        }
//        return "redirect:index";
//    }
//
//    @Data
//    static class LoginCommand {
//
//        @NotNull
//        private String username;
//
//        @NotNull
//        private String password;
//    }

    // from Chad
//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("loginCommand", new LoginCommand());
//        return "security/login";
//    }

    @RequestMapping("/login")
    public String loginForm() {
        return "security/login";
    }

}
