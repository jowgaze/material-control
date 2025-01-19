package io.github.materialcontrol.ms_materials.entities.item.dtos;

import io.github.materialcontrol.ms_materials.entities.item.Item;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ItemMapper {
    public static ItemResponseDto toDto(Item item){
        return new ModelMapper().map(item, ItemResponseDto.class);
    }

    public static Page<ItemResponseDto> toPage(Page<Item> page){
        return page.map(ItemMapper::toDto);
    }
}
