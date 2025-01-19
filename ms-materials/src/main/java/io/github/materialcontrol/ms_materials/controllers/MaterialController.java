package io.github.materialcontrol.ms_materials.controllers;

import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.entities.material.dtos.MaterialMapper;
import io.github.materialcontrol.ms_materials.entities.material.dtos.MaterialRequestDto;
import io.github.materialcontrol.ms_materials.entities.material.dtos.MaterialResponseDto;
import io.github.materialcontrol.ms_materials.services.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid MaterialRequestDto body) {
        Material material = materialService.create(MaterialMapper.toEntity(body));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(material.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<List<MaterialResponseDto>> findAll() {
        List<MaterialResponseDto> materials = MaterialMapper.toList(materialService.findAll());

        return ResponseEntity.ok().body(materials.stream().map(materialService::addHateoes).toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDto> findById(@PathVariable("id") Long id) {
        Material material = materialService.findById(id);

        return ResponseEntity.ok().body(materialService.addHateoes(MaterialMapper.toDto(material)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        materialService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
