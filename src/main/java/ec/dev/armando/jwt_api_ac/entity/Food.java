package ec.dev.armando.jwt_api_ac.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
