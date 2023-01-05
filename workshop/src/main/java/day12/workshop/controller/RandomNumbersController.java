package day12.workshop.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day12.workshop.exception.RandomNumberException;
import day12.workshop.models.Generate;

@Controller
@RequestMapping(path = "/random")

public class RandomNumbersController {
    @GetMapping("/show")
    public String showRandForm(Model model) {
        // Generate the object
        // bind the text input to gen
        Generate gen = new Generate();
        model.addAttribute("generateObj", gen);

        return "generate";

    }

    @PostMapping("/generate")
    // This method compared to changing paths is more important
    public String postRandForm(@ModelAttribute Generate generate, Model model) {
        // @ModelAttribute ties back to the object if the class is the same
        int numRange = generate.getRange();

        model.addAttribute("numberRandomNum", numRange);
        model.addAttribute("randNumResult", randomiseNumbers(numRange));
        return "result";

    }

    private List<Integer> randomiseNumbers(int range) {
        int maxRange = 30;
        List<Integer> randNumList = new ArrayList<>();

        // Validate the number ranges and throw exception if < 0 or > 30
        if (range < 0 || range > 30) {
            throw new RandomNumberException();
        }

        // Generate the number images and store it in an array/list
        // OR Generate random numbers using Set<Integer> LinkedHashSet<Integer>()

        Random rnd = new SecureRandom();
        while (randNumList.size() < range) {

            int num = rnd.nextInt(0, maxRange);
            if (randNumList.contains(num)) {
                continue;
            } else {
                randNumList.add(num);
            }

        }
        Collections.sort(randNumList);
        return randNumList;

    }

}
