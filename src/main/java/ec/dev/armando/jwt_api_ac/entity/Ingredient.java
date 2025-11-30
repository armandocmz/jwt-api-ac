package ec.dev.armando.jwt_api_ac.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;

    @ManyToOne()
    @JoinColumn(name="food_id")
    //TODO Agregar jsonignore
    private Food food;
}
