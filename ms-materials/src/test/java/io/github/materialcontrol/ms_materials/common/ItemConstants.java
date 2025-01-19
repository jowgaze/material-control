package io.github.materialcontrol.ms_materials.common;

import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.item.enums.Status;

import static io.github.materialcontrol.ms_materials.common.MaterialConstants.*;

public class ItemConstants {
    public static final Item VALID_ITEM_SAVED = new Item(1L, null, Status.AVAILABLE, VALID_MATERIAL_SAVED);
    public static final Item VALID_ITEM_SAVED_WITH_CODE = new Item(1L, "Projeto-1", Status.AVAILABLE, VALID_MATERIAL_SAVED);
}
