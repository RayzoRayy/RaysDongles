package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModArmor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TopazArmor extends ArmorItem {
    public TopazArmor(IArmorMaterial materialIn, EquipmentSlotType slots, Item.Properties builder, String name) {
        super(materialIn, slots, builder);

        this.setRegistryName(Reference.MODID, name);


    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EquipmentSlotType.LEGS) {
            return "slurpiesdongles:textures/models/armor/topaz_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/topaz_layer_2.png";
        }
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.RARE : Rarity.RARE;

    }
}