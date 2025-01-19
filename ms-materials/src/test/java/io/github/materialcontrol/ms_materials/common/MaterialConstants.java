package io.github.materialcontrol.ms_materials.common;

import io.github.materialcontrol.ms_materials.entities.material.Material;

import java.util.HashSet;

public class MaterialConstants {
    public static final Material VALID_MATERIAL = new Material("Projetor");
    public static final Material VALID_MATERIAL_SAVED = new Material(1L, "Projetor", new HashSet<>());
}
