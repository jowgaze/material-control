package io.github.materialcontrol.ms_materials.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.github.materialcontrol.ms_materials.controllers.ItemController;
import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.item.dtos.ItemResponseDto;
import io.github.materialcontrol.ms_materials.entities.item.enums.Status;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.exceptions.NotFoundException;
import io.github.materialcontrol.ms_materials.exceptions.UniqueAttributeViolationException;
import io.github.materialcontrol.ms_materials.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Item create(Material material) {
        try {
            Item item = itemRepository.save(new Item(material));
            Long serial = itemRepository.countItemByMaterial(material);
            item.setCode(serial);
            return item;
        } catch (DataIntegrityViolationException e) {
            throw new UniqueAttributeViolationException("Item code already exists");
        }
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Item with id = %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }

    @Transactional
    public Item updateStatus(Long id) {
        Item item = findById(id);

        if (item.getStatus().equals(Status.AVAILABLE))
            item.setStatus(Status.UNAVAILABLE);
        else
            item.setStatus(Status.AVAILABLE);

        return item;
    }

    public ItemResponseDto addHateoes(ItemResponseDto item) {
        return item
                .add(linkTo(methodOn(ItemController.class).findById(item.getId())).withSelfRel().withType("GET"))
                .add(linkTo(methodOn(ItemController.class).updateStatus(item.getId())).withSelfRel().withType("PATCH"))
                .add(linkTo(methodOn(ItemController.class).delete(item.getId())).withSelfRel().withType("DELETE"));
    }

}
