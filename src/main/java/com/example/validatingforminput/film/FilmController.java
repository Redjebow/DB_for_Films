package com.example.validatingforminput.film;

import com.example.validatingforminput.actor.ActorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/films")
public class FilmController{

    FilmRepository filmRepository;

    ActorRepository actorRepository;

    FilmService filmService;
    FilmMapper filmMapper;

    public FilmController(FilmMapper filmMapper, FilmRepository filmRepository, ActorRepository actorRepository, FilmService filmService){
        this.filmRepository = filmRepository;
        this. actorRepository = actorRepository;
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }
    @GetMapping("/add")
    public String addFilmForm(Model model){
        model.addAttribute("film", new FilmDTO());
        model.addAttribute("pageTitle","Add New Films");
        model.addAttribute("actors", actorRepository.findAll());
        return "films_add";
    }
    @GetMapping("/all")
    public String getAllFilms(Model model){
        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("pageTitle","All Films");
        return "films_all";
    }
    @PostMapping("/submit")
    public ModelAndView submitFilm(@Valid FilmDTO filmDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("film", filmDTO);
            model.addAttribute("actors", actorRepository.findAll());
            return new ModelAndView("films_add");
        }
        if(!filmDTO.getTitle().equals(filmDTO.getReTitle())){
            model.addAttribute("TitleDoNotMatch", "TitleDoNotMatch");
            model.addAttribute("film", filmDTO);
            model.addAttribute("actors", actorRepository.findAll());
            return new ModelAndView("films_add");
        }
        Film film = filmMapper.toEntity(filmDTO);
        filmRepository.save(film);
        return new ModelAndView("result");
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return new ModelAndView("result");
    }
}
