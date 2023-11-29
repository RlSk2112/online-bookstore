package bg.softuni.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/online-bookstore/user")
public class UserController {

    @GetMapping("/login")
    public ModelAndView modelAndView(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
