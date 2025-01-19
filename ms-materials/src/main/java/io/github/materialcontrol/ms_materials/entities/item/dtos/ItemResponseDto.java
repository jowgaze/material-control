package io.github.materialcontrol.ms_materials.entities.item.dtos;

import io.github.materialcontrol.ms_materials.entities.material.Material;
import org.springframework.hateoas.RepresentationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemResponseDto extends RepresentationModel<ItemResponseDto>{
    private Long id;
    private String code;
    private String status;
    private Material material;
}
