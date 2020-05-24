package interviewluty2020.listaprzebojow.controllers;

import interviewluty2020.listaprzebojow.dto.RegisterUserDto;
import interviewluty2020.listaprzebojow.model.enums.Role;
import interviewluty2020.listaprzebojow.service.SecurityService;
import interviewluty2020.listaprzebojow.service.UserService;
import interviewluty2020.listaprzebojow.validation.spring.RegisterUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController
{
    private final SecurityService securityService;
    private final RegisterUserValidator registerUserValidator;
    private final UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder)
    {
        //webDataBinder.setValidator(registerUserValidator);
        // ___________________________________________________________________
    }

    // ---------------------------- REGISTER ---------------------------------
    @GetMapping("/register")
    public String registerGet(Model model)
    {
        model.addAttribute("user", new RegisterUserDto());
        model.addAttribute("roles", Role.values());
        model.addAttribute("errors", new HashMap<>());
        return "security/register";
    }

    @PostMapping("/register")
    public String registerPost(
            @Valid @ModelAttribute RegisterUserDto registerUserDto,
            BindingResult bindingResult,
            Model model)
    {
        if (bindingResult.hasErrors())
        {
            var errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getCode));

            model.addAttribute("user", registerUserDto);
            model.addAttribute("roles", Role.values());
            model.addAttribute("errors", errors);
            return "security/register";
        }
        securityService.register(registerUserDto);
        return "redirect:/home/mainPage";
    }


    // ------------------------------ LOG IN --------------------------------
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("error", "");
        return "security/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model)
    {
        model.addAttribute("error", "Login error");
        return "users/userMainPage";
    }

    // ---------------------------- ACCESS DENIED -------------------------------
    @GetMapping("/accessDenied")
    public String accessDenied(Model model)
    {
        model.addAttribute("message", "ACCESS DENIED");
        return "security/accessDenied";
    }

    // ----------------------------- REGISTER ACTVATION ---------------------------
    @GetMapping("confirm-registration")
    public String activateUserGet(@RequestParam String token)
    {
        //securityService.ac
        return "redirect:/login";
    }











}












