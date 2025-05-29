package br.com.farmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.farmed.dto.UserDTO;
import br.com.farmed.entity.User;
import br.com.farmed.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/")
public class FarmedController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("isLogged", auth.getCredentials() != "");

        return "index";
    }

    @GetMapping("auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
        }

        if (logout != null) {
            model.addAttribute("msg", "Logout realizado com sucesso.");
        }
        
        return "auth/login";
    }

    @GetMapping("auth/register")
    public String formRegister(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "auth/register";
    }

    @PostMapping("auth/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        userService.registerNewUser(userDTO);
        return "redirect:/auth/login?registered";
    }
    
}