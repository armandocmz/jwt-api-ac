package ec.dev.armando.jwt_api_ac.controller;

import ec.dev.armando.jwt_api_ac.entity.Food;
import ec.dev.armando.jwt_api_ac.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/all")
    public List<Food> getAll() {
        return foodService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Food getById(@PathVariable Long id) {
        return foodService.getById(id);
    }

    @PostMapping("/create")
    public Food create(@RequestBody Food food) {
        return foodService.save(food);
    }

    @PutMapping("/update/{id}")
    public Food update(@PathVariable Long id, @RequestBody Food food) {
        return foodService.update(id, food);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        foodService.deleteById(id);
    }
}
