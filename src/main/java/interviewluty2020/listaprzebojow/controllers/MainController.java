package interviewluty2020.listaprzebojow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/home")
public class MainController
{
    @GetMapping({"/index", "/"})
    public String mainPage()
    {
        return "home/mainPage";
    }

    @GetMapping("/error1")
    public String error1()
    {
        return "mainPage";
    }

    @GetMapping("/notFound")
    public String notFound()
    {
        return "errors/notFoundPage";
    }

    // ==============================================================

    public String user()
    {
        return "users/userMainPage";
    }

    public String admin()
    {
        // temporarily
        return "home/mainPage";
    }


}




