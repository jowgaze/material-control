package io.github.materialcontrol.ms_materials.repositories;

import io.github.materialcontrol.ms_materials.entities.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}