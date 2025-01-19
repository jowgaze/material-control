package io.github.materialcontrol.ms_materials.services;

import io.github.materialcontrol.ms_materials.controllers.ItemController;
import io.github.materialcontrol.ms_materials.controllers.MaterialController;
import io.github.materialcontrol.ms_materials.entities.item.dtos.ItemResponseDto;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.entities.material.dtos.MaterialResponseDto;
import io.github.materialcontrol.ms_materials.exceptions.NotFoundException;
import io.github.materialcontrol.ms_materials.exceptions.UniqueAttributeViolationException;
import io.github.materialcontrol.ms_materials.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    @Transactional
    public Material create(Material material) {
        try {
            return materialRepository.save(material);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueAttributeViolationException(String.format("Name = %s already exists", material.getName()));
        }
    }

    @Transactional(readOnly = true)
    public Material findById(Long id) {
        return materialRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Material with id = %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Material material = findById(id);
        materialRepository.delete(material);
    }

    public MaterialResponseDto addHateoes(MaterialResponseDto material) {
        return material
                .add(linkTo(methodOn(MaterialController.class).findById(material.getId())).withSelfRel().withType("GET"))
                .add(linkTo(methodOn(MaterialController.class).delete(material.getId())).withSelfRel().withType("DELETE"));
    }

}
