package day12.workshop.revision.workshoprevision.controller;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import day12.workshop.revision.workshoprevision.exception.RandomNumberException;
import day12.workshop.revision.workshoprevision.models.Number;

@Controller
@RequestMapping(path="/random")

public class RandomNumberController {

    // Get the value from the text input from the html
    @GetMapping("/show")
    public String showForm(Model model) {

        // Bind the text input to the number object
        Number number = new Number();
        model.addAttribute("numObj",number);

        return "generate";
    }

    // This is for PostMapping Method which is more useful
    @PostMapping("/generate")
    public String postResult(@ModelAttribute Number number, Model model) {
        int range = number.getNum();

        model.addAttribute("numVal", range);
        model.addAttribute("listOfRanVal", generateRanVal(range));

        return "result";

    }


    // // THIS IS FOR A QUERY STRING ?range=6
    // @GetMapping("/generate")
    // public String postResult(@RequestParam String range, Model model) {
    //     int rangeInt = Integer.parseInt(range);

    //     model.addAttribute("numVal", range);
    //     model.addAttribute("listOfRanVal", generateRanVal(rangeInt));

    //     return "result";

    // }

    public Set<Integer> generateRanVal(int range) {

        // Validate the number ranges and throw exception if < 0 or > 30
        if (range < 0 || range > 30) {
            throw new RandomNumberException();
        }


        // Use a set to create unique numbers
        Set<Integer> randValSet = new LinkedHashSet<>();
        int maxVal = 30;
        Random rand = new SecureRandom();
        while(randValSet.size() < range) {
            int nextRand = rand.nextInt(0, maxVal);
            randValSet.add(nextRand);

        }

        return randValSet;

    }
    
}
