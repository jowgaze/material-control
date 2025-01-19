package io.github.materialcontrol.ms_materials.services;

import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.repositories.MaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static io.github.materialcontrol.ms_materials.common.MaterialConstants.VALID_MATERIAL;
import static io.github.materialcontrol.ms_materials.common.MaterialConstants.VALID_MATERIAL_SAVED;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class MaterialServiceTest {

    @InjectMocks
    private MaterialService service;

    @Mock
    private MaterialRepository repository;

    @Test
    public void createMaterial_WithValidData_ReturnsUser() {
        when(repository.save(VALID_MATERIAL)).thenReturn(VALID_MATERIAL_SAVED);

        Material sut = service.create(VALID_MATERIAL);

        assertThat(sut.getId()).isNotNull();
        assertThat(sut.getName()).isEqualTo(VALID_MATERIAL_SAVED.getName());
    }

    @Test
    public void getMaterial_ByExistingId_ReturnsUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_MATERIAL_SAVED));

        Material sut = service.findById(1L);

        assertThat(sut.getId()).isEqualTo(VALID_MATERIAL_SAVED.getId());
        assertThat(sut.getName()).isEqualTo(VALID_MATERIAL_SAVED.getName());
    }

    @Test
    public void getAllMaterial_Void_ReturnsListUser() {
        List<Material> materials = new ArrayList<>(
                Arrays.asList(
                        new Material(1L, "Projetor", new HashSet<>()),
                        new Material(1L, "Pincel", new HashSet<>()),
                        new Material(1L, "Apagador", new HashSet<>())
                )
        );

        when(repository.findAll()).thenReturn(materials);

        List<Material> sut = service.findAll();

        assertThat(sut).isNotNull();
        assertThat(sut.size()).isEqualTo(3);
    }

    @Test
    public void deleteMaterial_ByExistingId_Void(){
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_MATERIAL_SAVED));
        assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
    }
}
