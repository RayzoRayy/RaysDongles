package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModArmor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;

import net.minecraft.entity.player.PlayerEntity;


import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WitheredArmor extends ArmorItem {
    public WitheredArmor(IArmorMaterial materialIn, EquipmentSlotType slots, Item.Properties builder, String name) {
        super(materialIn, slots, builder);

        this.setRegistryName(Reference.MODID, name);


    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EquipmentSlotType.LEGS) {
            return "slurpiesdongles:textures/models/armor/withered_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/withered_layer_2.png";
        }
    }


    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.getItemStackFromSlot(EquipmentSlotType.HEAD) != null && player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmor.withered_helmet
                && player.getItemStackFromSlot(EquipmentSlotType.CHEST) != null && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModArmor.withered_chestplate
                && player.getItemStackFromSlot(EquipmentSlotType.LEGS) != null && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModArmor.withered_leggings
                && player.getItemStackFromSlot(EquipmentSlotType.FEET) != null && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModArmor.withered_boots) {

            if (!player.abilities.isCreativeMode) {
                if (!player.abilities.allowFlying) {
                    player.abilities.allowFlying = true;
                }

            }
        } else {
            if (!player.abilities.isCreativeMode) {
                player.abilities.isFlying = false;
                player.abilities.allowFlying = false;
            }

        }
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.EPIC : Rarity.EPIC;

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Wearing this armor as a set grants Flight"));
    }
}
