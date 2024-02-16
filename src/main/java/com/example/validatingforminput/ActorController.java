package com.example.validatingforminput;

import jakarta.validation.Valid;
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
@RequestMapping("/actor")
public class ActorController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }
    ActorRepository actorRepository;
    NationalityRepository nationalityRepository;
    public ActorController(ActorRepository actorRepository, NationalityRepository nationalityRepository){
        this.actorRepository = actorRepository;
        this.nationalityRepository = nationalityRepository;
    }
    @GetMapping("/add")
    public String addActorsForm(Model model){
        model.addAttribute("actors", new Actor());
        model.addAttribute("nationality",nationalityRepository.findAll());
        return "actors_add";
    }
    @PostMapping("/submit")
    public ModelAndView submitActors(@Valid @ModelAttribute Actor actor, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("actors", actor);
            model.addAttribute("nationality",nationalityRepository.findAll());
            return new ModelAndView("actors_add");
        }
        actorRepository.save(actor);
        return new ModelAndView("result");
    }
}
