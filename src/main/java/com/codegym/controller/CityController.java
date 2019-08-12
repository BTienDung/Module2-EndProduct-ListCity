package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @ModelAttribute("country")
    public Iterable<Country> findAllCountry(){
        return countryService.findAll();
    }
    @GetMapping("/list-city")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView createCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("message", "Create Failed");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        cityService.save(city);
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("message", "Create Success");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/edit-city")
    public ModelAndView editCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("message","Edit failed");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        cityService.save(city);
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("message", "Edit success");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView formDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        City city = cityService.findById(id);
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/delete-city")
    public ModelAndView deleteCity(@ModelAttribute City city){
        ModelAndView modelAndView = new ModelAndView("/delete");
        cityService.delete(city.getId());
        modelAndView.addObject("message", "Delete success");
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/detail");
        City city = cityService.findById(id);
        modelAndView.addObject("city", city);
        return modelAndView;
    }
}
