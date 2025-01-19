package io.github.materialcontrol.ms_materials.entities.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.materialcontrol.ms_materials.entities.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_material")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true, length = 50)
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "material")
    @Setter(AccessLevel.NONE)
    private Set<Item> items = new HashSet<>();
}
