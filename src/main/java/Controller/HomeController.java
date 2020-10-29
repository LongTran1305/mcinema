package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String Home(Model model ){
        return "index";
    }
    @GetMapping("/index2")
    public String Home2(Model model ){
        return "main/index2";
    }
}
