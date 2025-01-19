package io.github.materialcontrol.ms_materials.entities.item;

import io.github.materialcontrol.ms_materials.entities.item.enums.Status;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_item")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 60)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private Status status = Status.AVAILABLE;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    public void setCode(Long serial){
        code = String.format("%s-%s",material.getName(),serial);
    }
}
