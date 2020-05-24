package interviewluty2020.listaprzebojow.controllers;

import interviewluty2020.listaprzebojow.dto.CreateSongDto;
import interviewluty2020.listaprzebojow.dto.CreateUserDto;
import interviewluty2020.listaprzebojow.dto.CreateVoteDto;
import interviewluty2020.listaprzebojow.dto.ReadVoteDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.Song;
import interviewluty2020.listaprzebojow.model.enums.Genre;
import interviewluty2020.listaprzebojow.service.SongService;
import interviewluty2020.listaprzebojow.service.UserService;
import interviewluty2020.listaprzebojow.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController
{
    private final VoteService voteService;
    private final SongService songService;
    private final UserService userService;


    @GetMapping("/create")
    public String createGet(Model model/*, Long userId*/)
    {
/*        if (userId == null)
        {
            throw new AppException("ID IS NULL");
        }*/

        model.addAttribute("vote", new CreateVoteDto());
/*        model.addAttribute("songs", List.of(
                new CreateSongDto().builder().author("SHAKIRA").title("AFRICA").genre(Genre.POP).build(),
                new CreateSongDto().builder().author("BEYONCE").title("USA").genre(Genre.POP).build(),
                new CreateSongDto().builder().author("KATEPERRY").title("FRANCE").genre(Genre.POP).build(),
                new CreateSongDto().builder().author("KAYAH").title("SUPERMANKA").genre(Genre.POP).build(),
                new CreateSongDto().builder().author("BRITNEY").title("OHIO").genre(Genre.POP).build()
        ));*/
        model.addAttribute("songs", songService.getAll());
        //model.addAttribute("user", userService.getOne(userId));
        model.addAttribute("voteDate", LocalDateTime.now());
        return "votes/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute CreateVoteDto createVoteDto, Model model)
    {
        if (createVoteDto == null)
        {
            throw new AppException("CREATE VOTE DTO IS NULL");
        }

        model.addAttribute("vote", createVoteDto);
        model.addAttribute("voteDate", LocalDateTime.now());
        voteService.add(createVoteDto);
        return "redirect:/votes/all";
    }

    @GetMapping("/all")
    public String getAll(Model model)
    {
        model.addAttribute("votes", voteService.getAll());
        return "votes/all";
    }

    @GetMapping("/one/{id}")
    public String getOne(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        model.addAttribute("vote", voteService.getOne(id));
        return "votes/one";
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        model.addAttribute("vote", voteService.getOne(id));
        model.addAttribute("voteDate", LocalDateTime.now());
        return "votes/edit";
    }

    @PostMapping("edit")
    public String editPost(@ModelAttribute ReadVoteDto readVoteDto)
    {
        voteService.update(readVoteDto);
        return "redirect:/votes/all";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        voteService.delete(id);
        return "redirect:/votes/all";
    }


}




