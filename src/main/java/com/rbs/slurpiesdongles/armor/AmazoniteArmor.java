package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModArmor;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class AmazoniteArmor extends ArmorItem {
    public AmazoniteArmor(IArmorMaterial materialIn, EquipmentSlotType slots, Item.Properties builder, String name) {
        super(materialIn, slots, builder);

        this.setRegistryName(Reference.MODID, name);

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EquipmentSlotType.LEGS) {
            return "slurpiesdongles:textures/models/armor/amazonite_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/amazonite_layer_2.png";
        }


    }
}
