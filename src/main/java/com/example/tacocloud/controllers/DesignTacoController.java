package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.Type;
import com.example.tacocloud.repositories.IngredientRepository;
import com.example.tacocloud.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.example.tacocloud.domain.Ingredient.filterByType;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }


    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> ingredientList.add(ingredient));
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @ModelAttribute
    Taco taco(Taco taco) {
        return new Taco();
    }

    @ModelAttribute
    Order order(Order order) {
        return new Order();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco savedTaco = tacoRepository.save(design);
        order.addDesign(savedTaco);
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
