package ec.dev.armando.jwt_api_ac.service;

import ec.dev.armando.jwt_api_ac.entity.Food;
import ec.dev.armando.jwt_api_ac.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository; //equivale a hacer new constructor...

    public List<Food> getAll(){
        return foodRepository.findAll();
    }

    public Food getById(Long id){
        //if(id!=null)
        //si id no existe voy x la excepcion
        return foodRepository.findById(id).orElseThrow(()->new RuntimeException("Food not found with id: "+id));

    }

    public Food save(Food food){
        return foodRepository.save(food); //insert into grabacion total
    }

    public Food update(Long id, Food  updateFood){ //update

        Food existingFood= getById(id);
        existingFood.setName(updateFood.getName()); //x cada campo
        existingFood.setCalories(updateFood.getCalories()); //x cada campo

        return foodRepository.save(existingFood);//si existe la actualiza , sino viene null lo crea (inserta) save GenerationType=Identity
    }

    public void deleteById(Long id){
        foodRepository.deleteById(id); //delete
    }

    public void deleteAll(){
        foodRepository.deleteAll();
    }





}
