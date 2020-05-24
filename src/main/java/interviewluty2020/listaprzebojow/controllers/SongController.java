package interviewluty2020.listaprzebojow.controllers;

import interviewluty2020.listaprzebojow.dto.CreateSongDto;
import interviewluty2020.listaprzebojow.dto.ReadSongDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.enums.Genre;
import interviewluty2020.listaprzebojow.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController
{
    private final SongService songService;

    // CREATE ------------------------------
    @GetMapping("/create")
    public String createGet(Model model)
    {
        model.addAttribute("song", new CreateSongDto());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("votes", new HashSet<>());
        return "songs/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute CreateSongDto createSongDto, Model model)
    {
        if (createSongDto == null)
        {
            throw new AppException("CREATE SONG DTO OBJECT IS NULL");
        }

        model.addAttribute("song", createSongDto);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("votes", new HashSet<>());
        songService.add(createSongDto);
        return "redirect:/songs/all";
    }


    // GET ALL -----------------------------
    @GetMapping("/all")
    public String getAll(Model model)
    {
        model.addAttribute("songs", songService.getAll());
        return "songs/all";
    }

    // GET ONE -----------------------------
    @GetMapping("/one/{id}")
    public String getOne(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        model.addAttribute("song", songService.getOne(id));
        return "songs/one";
    }

    // EDIT --------------------------------
    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        model.addAttribute("song", songService.getOne(id));
        model.addAttribute("genre", Genre.values());
        model.addAttribute("votes", new HashSet<>());
        return "songs/edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute ReadSongDto readSongDto)
    {
        if (readSongDto == null)
        {
            throw new AppException("READ SONG DTO OBJECT IS NULL");
        }

        songService.update(readSongDto);
        return "redirect:/songs/all";
    }

    // DELETE ------------------------------
    @PostMapping("/delete")
    public String deletePost(@RequestParam Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        songService.delete(id);
        return "redirect:/songs/all";
    }


}
