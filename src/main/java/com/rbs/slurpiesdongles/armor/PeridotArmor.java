package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModArmor;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PeridotArmor extends ArmorItem {
    public PeridotArmor(IArmorMaterial materialIn, EquipmentSlotType slots, Item.Properties builder, String name) {
        super(materialIn, slots, builder);

        this.setRegistryName(Reference.MODID, name);


    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EquipmentSlotType.LEGS) {
            return "slurpiesdongles:textures/models/armor/peridot_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/peridot_layer_2.png";
        }
    }
}
