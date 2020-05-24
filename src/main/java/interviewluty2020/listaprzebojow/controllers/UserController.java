package interviewluty2020.listaprzebojow.controllers;

import interviewluty2020.listaprzebojow.dto.CreateUserDto;
import interviewluty2020.listaprzebojow.dto.ReadUserDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.enums.Genre;
import interviewluty2020.listaprzebojow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;


    @GetMapping("/create")
    public String createGet(Model model)
    {
        model.addAttribute("user", new CreateUserDto());
        model.addAttribute("votes", new HashSet<>());
        return "users/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute CreateUserDto createUserDto, Model model)
    {
        model.addAttribute("user", createUserDto);
        model.addAttribute("votes", new HashSet<>());
        userService.add(createUserDto);
        return "redirect:/users/all";
    }



    @GetMapping("/all")
    public String getAll(Model model)
    {
        model.addAttribute("users", userService.getAll());
        return "users/all";
    }

    @GetMapping("/one/{id}")
    public String getOne(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        model.addAttribute("user", userService.getOne(id));
        return "users/one";
    }



    @PostMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        model.addAttribute("user", userService.getOne(id));
        model.addAttribute("genre", Genre.values());
        model.addAttribute("votes", new HashSet<>());
        return "users/edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute ReadUserDto readUserDto)
    {
        if (readUserDto.getId() == null)
        {
            throw new AppException("ID IS NULL");
        }

        userService.update(readUserDto);
        return "redirect:/users/all";
    }



    @PostMapping("/delete")
    public String deletePost(@RequestParam Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        userService.delete(id);
        return "redirect:/users/all";
    }






}








