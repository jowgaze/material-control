package io.github.materialcontrol.ms_materials.entities.material.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialRequestDto {
    @Size(min = 2, max = 50)
    private String name;
}
