package ec.dev.armando.jwt_api_ac.repository;

import ec.dev.armando.jwt_api_ac.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository  extends JpaRepository<Food, Long> {



}
