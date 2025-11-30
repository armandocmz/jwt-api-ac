package ec.dev.armando.jwt_api_ac.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity //Entidad para JPA
@Data     //viene de Lombok para no crear lineas de constructores ni getters ni setters
@AllArgsConstructor //crea el constructor con datos
@NoArgsConstructor  //crea el constructor vacio
public class Food {

    @Id  //Id de JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Identificador Integrador de ID (para que no se repitan)
    private Long id;

    private String name;

    private Integer calories;

    /*
    -cascade = CascadeType.ALL: Si guardas / eliminas un Food, afecta tambien a sus ingredients
    -orphanRemoval = true: Si eliminas un Ingredient lo borra tambien de la db, y no rompe la relación con Food.
    */
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;
}
