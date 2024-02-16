package com.example.validatingforminput;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/films")
public class FilmController   implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    ActorRepository actorRepository;
    @GetMapping("/add")
    public String addFilmForm(Model model){
        model.addAttribute("films", new Film());
        model.addAttribute("actors", actorRepository.findAll());
        return "films_add";
    }
    @PostMapping("/submit")
    public ModelAndView submitFilm(@Valid @ModelAttribute Film film, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("films", film);
            model.addAttribute("actors", actorRepository.findAll());
            return new ModelAndView("films_add");
        }
        filmRepository.save(film);
        return new ModelAndView("result");
    }
}
