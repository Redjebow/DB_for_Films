package com.example.validatingforminput.actor;

import com.example.validatingforminput.nationality.NationalityRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/actors")
public class ActorController{
    ActorRepository actorRepository;
    NationalityRepository nationalityRepository;
    ActorService actorService;
    ActorMapper actorMapper;
    public ActorController(ActorMapper actorMapper,  ActorRepository actorRepository, NationalityRepository nationalityRepository, ActorService actorService){
        this.actorRepository = actorRepository;
        this.nationalityRepository = nationalityRepository;
        this.actorService = actorService;
        this.actorMapper = actorMapper;

    }
    @GetMapping("/add")
    public String addActorsForm(Model model){
        model.addAttribute("actor", new ActorDTO());
        model.addAttribute("pageTitle","Add New Actors");
        model.addAttribute("nationality",nationalityRepository.findAll());
        return "actors_add";
    }
    @GetMapping("/all")
    public String getAllActors(Model model){
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("nationality",nationalityRepository.findAll());
        model.addAttribute("pageTitle","All Actors");
        return "actors_all";
    }
    @PostMapping("/submit")
    public ModelAndView submitActors(@Valid @ModelAttribute ActorDTO actorDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("nationality",nationalityRepository.findAll());
            return new ModelAndView("actors_add");
        }
        Actor actor = actorMapper.toEntity(actorDTO);
        actorRepository.save(actor);
        return new ModelAndView("result");
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return new ModelAndView("result");
    }
}
