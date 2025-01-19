package io.github.materialcontrol.ms_materials.entities.item.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemRequestDto {
    private Long materialId;
    private Integer quantity;
}
