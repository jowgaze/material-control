package io.github.materialcontrol.ms_materials.repositories;

import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
  long countItemByMaterial(@NonNull Material material);
}