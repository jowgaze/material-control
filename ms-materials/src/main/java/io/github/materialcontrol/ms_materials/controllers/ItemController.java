package io.github.materialcontrol.ms_materials.controllers;

import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.item.dtos.ItemMapper;
import io.github.materialcontrol.ms_materials.entities.item.dtos.ItemRequestDto;
import io.github.materialcontrol.ms_materials.entities.item.dtos.ItemResponseDto;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.services.ItemService;
import io.github.materialcontrol.ms_materials.services.MaterialService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final MaterialService materialService;
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<List<ItemResponseDto>> create(@RequestBody @Valid ItemRequestDto body) {
        Material material = materialService.findById(body.getMaterialId());
        List<ItemResponseDto> result = new ArrayList<>();

        for (int i = 0; i < body.getQuantity(); i++) {
            Item item = itemService.create(material);
            result.add(itemService.addHateoes(ItemMapper.toDto(item)));
        }

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping()
    public ResponseEntity<Page<ItemResponseDto>> findAll(@Parameter(hidden = true) Pageable pageable) {
        Page<ItemResponseDto> items = ItemMapper.toPage(itemService.findAll(pageable));

        return ResponseEntity.ok().body(items.map(itemService::addHateoes));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> findById(@PathVariable("id") Long id) {
        Item item = itemService.findById(id);

        return ResponseEntity.ok().body(itemService.addHateoes(ItemMapper.toDto(item)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        itemService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ItemResponseDto> updateStatus(@PathVariable("id") Long id) {
        Item item = itemService.updateStatus(id);
        ItemResponseDto dto = itemService.addHateoes(ItemMapper.toDto(item));

        return ResponseEntity.ok().body(dto);
    }
}
