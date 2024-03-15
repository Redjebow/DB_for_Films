
package com.example.validatingforminput.nationality;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/nationality")
public class NationalityController{

    NationalityRepository nationalityRepository;
    NationalityService nationalityService;
    public NationalityController(NationalityRepository nationalityRepository, NationalityService nationalityService){
        this.nationalityRepository = nationalityRepository;
        this.nationalityService = nationalityService;
    }
    @GetMapping("/add")
    public String addNationalityForm(Model model){
        model.addAttribute("pageTitle","Add New Nationality");
        model.addAttribute("nationality", new Nationality());
        return "nationality_add";
    }
    @GetMapping("/all")
    public String getAllNationalitys(Model model){
        model.addAttribute("nationality", nationalityRepository.findAll());
        model.addAttribute("pageTitle","All Nationalitys");
        return "nationality_all";
    }
    @PostMapping("/submit")
    public ModelAndView submitNationality(@Valid @ModelAttribute Nationality nationality, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("nationality_add") ;
        }
        nationalityRepository.save(nationality);
        return new ModelAndView("result");
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteNationality(@PathVariable Long id) {
        nationalityService.deleteNationality(id);
        return new ModelAndView("result");
    }
}