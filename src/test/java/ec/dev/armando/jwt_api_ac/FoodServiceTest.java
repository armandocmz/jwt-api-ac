package ec.dev.armando.jwt_api_ac;


import ec.dev.armando.jwt_api_ac.entity.Food;
import ec.dev.armando.jwt_api_ac.entity.Ingredient;
import ec.dev.armando.jwt_api_ac.service.FoodService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest //constructor debe tener
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //en los test getters y setters se genera solo
@AutoConfigureTestDatabase //relacion de BD
@Transactional //se ejecuten todas las operaciones
@Slf4j  //logger simple logging patron de diseño SIMPLE LOGIN FACADA FOR JAVA
public class FoodServiceTest {

    private final FoodService foodService;
    private final Faker faker = new Faker(Locale.forLanguageTag("es-AR"));

    private Food buildFakeWithIngredients(){

        //en vez de setear test con datos
        //usar builder
        //builder es un armador clases con datos
        //dentro o vacias (lombok builder)
        //usar @Builder
        //Persona p = ner Persona()
        //p.setNombre("");
        //p.setId(1L);

        //Persona persona= Persona.builder()
        //.id(1L)
        //.nombre("Abc")
        //projectLombok.org

        //Builder faker.mumber()
        String dish = faker.food().dish();
        int calories = faker.number().numberBetween(100,800); //rango de faker


        //logging level slf4j
        log.info("dish ->"+dish);  //slf4j


        List<Ingredient> ingredients = new ArrayList<>();

        int ingredientCount = faker.number().numberBetween(2,5);

        //el seteo de builder en vez del clasico p = new persona() set
        Food food = Food.builder()
                .name(dish)
                .calories(calories)
                .ingredients(ingredients)
                .build();

        ingredients.addAll(
                java.util.stream.IntStream.range(0, ingredientCount)
                        .mapToObj(i->Ingredient.builder()
                                .name(faker.food().ingredient())
                                .food(food)
                                .build())
                        .toList()
        );


        return food;
    }

    //se ejecuta antes de los test
    @BeforeEach
    void setUp(){
        foodService.deleteAll(); //limpia previamenye
        IntStream.range(0,3).forEach(i->foodService.save(buildFakeWithIngredients()));
    }

    @Test
    void testGetAll(){
        List<Food> allFood = foodService.getAll();
        assertEquals(3,allFood.size());

        allFood.forEach(food->{
            assertNotNull(food.getName());
            assertTrue(food.getCalories() > 0);
            assertFalse(food.getIngredients().isEmpty());
        });

    }

    @Test
    void testSaveAndGetById(){

        Food newFood = buildFakeWithIngredients();
        Food savedFood = foodService.save(newFood);

        Food fetched = foodService.getById(savedFood.getId());

        assertEquals(savedFood.getId(), fetched.getId());
        assertEquals(savedFood.getName(), fetched.getName());
        assertEquals(savedFood.getCalories(), fetched.getCalories());
        assertEquals(savedFood.getIngredients().size(), fetched.getIngredients().size());
    }

    @Test
    void testUpdate() {
        Food originalFood = foodService.getAll().get(0);
        originalFood.setName("nameUpdated");
        originalFood.setCalories(123);

        Food updated = foodService.update(originalFood.getId(), originalFood);

        assertEquals("nameUpdated", updated.getName());
        assertEquals(123, updated.getCalories());
    }

    @Test
    void testDelete() {
        List<Food> foodList = foodService.getAll();
        Food food = foodList.get(0);
        int foodCount = foodList.size();

        foodService.deleteById(food.getId());

        assertEquals(foodCount-1, foodService.getAll().size());
    }

    @Test
    void testGetByIdNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> foodService.getById(9999L));
        assertTrue(exception.getMessage().contains("Food not found with id"));
    }

}
