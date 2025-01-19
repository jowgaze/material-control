package io.github.materialcontrol.ms_materials.entities.material.dtos;

import io.github.materialcontrol.ms_materials.entities.material.Material;
import org.modelmapper.ModelMapper;

import java.util.List;

public class MaterialMapper {
    public static Material toEntity(MaterialRequestDto dto){
        return new ModelMapper().map(dto, Material.class);
    }

    public static MaterialResponseDto toDto(Material material){
        return  new ModelMapper().map(material, MaterialResponseDto.class);
    }

    public static List<MaterialResponseDto> toList(List<Material> materials){
        return materials.stream().map(MaterialMapper::toDto).toList();
    }
}
