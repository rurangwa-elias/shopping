package edu.miu.groupx.security.securityservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping({"/", "/home"})
    String home() {
        return "home";
    }

    @GetMapping("/hello")
    String hello() {
        return "hello";
    }
}
