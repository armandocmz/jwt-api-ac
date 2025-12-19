package ec.dev.armando.jwt_api_ac.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity //Entidad para JPA
@Data     //viene de Lombok para no crear lineas de constructores ni getters ni setters
@AllArgsConstructor //crea el constructor con datos
@NoArgsConstructor  //crea el constructor vacio
@Builder //para los test de FoodService
public class Food {

    @Id  //Id de JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Identificador Integrador de ID (para que no se repitan)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotNull
    @Min(value = 0, message = "Las calorias no pueden ser negativas")
    private Integer calories;

    /*
    -cascade = CascadeType.ALL: Si guardas / eliminas un Food, afecta tambien a sus ingredients
    -orphanRemoval = true: Si eliminas un Ingredient lo borra tambien de la db, y no rompe la relación con Food.
    */
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;
}
