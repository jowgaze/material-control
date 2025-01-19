package io.github.materialcontrol.ms_materials.entities.material.dtos;

import org.springframework.hateoas.RepresentationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialResponseDto extends RepresentationModel<MaterialResponseDto>{
    private Long id;
    private String name;
}
