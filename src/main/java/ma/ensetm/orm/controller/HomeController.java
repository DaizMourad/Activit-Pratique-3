package ma.ensetm.orm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String accueil() {
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String pageLogin() {
        return "login";
    }

}
