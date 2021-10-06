package com.shama.webapp.controllers;

import com.shama.webapp.models.Distance;
import com.shama.webapp.models.Info;
import com.shama.webapp.models.Role;
import com.shama.webapp.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Controller
public class WebController  {

    @Autowired
    RestTemplate template;

    @Autowired
    private Environment environment;

    @GetMapping(value = {"/", "/index", "/home",})
    public String index(Model model) {
        model.addAttribute("assignment", "web-assignment");
        Info info = new Info();
        info.setRoles(new ArrayList<>());
        setInfo(model, info);
        return "index";
    }

    private void setInfo(Model model, Info info) {

        model.addAttribute("allRoles", Role.values());
        model.addAttribute("info", info);
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute @Valid Info info, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setInfo(model, info);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
            return "index";
        }
        model.addAttribute("info", info);
        return "display-info";
    }

    @GetMapping("/distance/coversion")
    public String distanceConversion(Model model) {
        model.addAttribute("distance", new Distance());
        model.addAttribute("allUnits", Unit.values());
        return "distance-conversion";
    }

    @PostMapping("/convert")
    public String convert(@ModelAttribute Distance distance, Model model) {
        if (distance.getFromUnit() == distance.getToUnit()) {
            model.addAttribute("error", "From Unit and To Unit must be different");
        } else {
            StringBuilder url = new StringBuilder(environment.getProperty("address")).append("/distance/convert/");
            url.append(distance.getFromUnit().name()).append("/").append(distance.getQuantity()).append("/").append(distance.getToUnit().name());
            ResponseEntity<Double> result = template.getForEntity(url.toString(), Double.class);
            if (result.hasBody()) {
                model.addAttribute("conversion", result.getBody());
            }
            //@TODO show error
        }
        model.addAttribute("distance", distance);
        model.addAttribute("allUnits", Unit.values());
        return "distance-conversion";
    }
}
