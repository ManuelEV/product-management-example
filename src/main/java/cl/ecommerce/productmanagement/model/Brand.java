package cl.ecommerce.productmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    private List<Price> prices;

}
