package io.github.materialcontrol.ms_materials.services;

import io.github.materialcontrol.ms_materials.common.MaterialConstants;
import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.item.enums.Status;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import static io.github.materialcontrol.ms_materials.common.ItemConstants.*;
import static io.github.materialcontrol.ms_materials.common.MaterialConstants.VALID_MATERIAL_SAVED;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService service;

    @Mock
    private ItemRepository repository;

    @Test
    public void createItem_WithValidData_ReturnsItem() {
        when(repository.save(any(Item.class))).thenReturn(VALID_ITEM_SAVED);
        when(repository.countItemByMaterial(VALID_MATERIAL_SAVED)).thenReturn(1L);

        Item sut = service.create(VALID_MATERIAL_SAVED);

        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(1L);
        assertThat(sut.getCode()).isEqualTo("Projetor-1");
    }

    @Test
    public void getItem_ByExistingId_ReturnsItem(){
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_ITEM_SAVED_WITH_CODE));

        Item sut = service.findById(1L);

        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(1L);
        assertThat(sut.getCode()).isEqualTo(VALID_ITEM_SAVED_WITH_CODE.getCode());
        assertThat(sut.getStatus()).isEqualTo(VALID_ITEM_SAVED_WITH_CODE.getStatus());
    }

    @Test
    public void getAllUsers_Void_ReturnsPageUsers() {
        List<Item> itemList = List.of(
                new Item(1L, "Projetor-1", Status.AVAILABLE, VALID_MATERIAL_SAVED),
                new Item(2L, "Projetor-2", Status.AVAILABLE, VALID_MATERIAL_SAVED),
                new Item(3L, "Projetor-3", Status.AVAILABLE, VALID_MATERIAL_SAVED)
        );
        Page<Item> userPage = new PageImpl<>(itemList);

        Pageable pageable = PageRequest.of(0, 20);

        when(repository.findAll(pageable)).thenReturn(userPage);

        Page<Item> sut = service.findAll(pageable);

        assertThat(sut).isNotNull();
        assertThat(sut.getSize()).isEqualTo(3);
    }

    @Test
    public void updateItem_ByExistingId_ReturnsItem(){
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_ITEM_SAVED_WITH_CODE));

        Item sut = service.updateStatus(1L);

        assertThat(sut.getStatus()).isEqualTo(Status.UNAVAILABLE);
    }

    @Test
    public void deleteMaterial_ByExistingId_Void(){
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_ITEM_SAVED_WITH_CODE));
        assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
    }
}
