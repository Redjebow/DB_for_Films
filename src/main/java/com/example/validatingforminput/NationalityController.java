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
@RequestMapping("/nationality")
public class NationalityController{

    NationalityRepository nationalityRepository;
    public NationalityController(NationalityRepository nationalityRepository){
        this.nationalityRepository = nationalityRepository;
    }
    @GetMapping("/add")
    public String addNationalityForm(Model model){
        model.addAttribute("nationality", new Nationality());
        return "nationality_add";
    }
    @PostMapping("/submit")
    public ModelAndView submitNationality(@Valid @ModelAttribute Nationality nationality, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("nationality_add") ;
        }
        nationalityRepository.save(nationality);
        return new ModelAndView("result");

    }
}