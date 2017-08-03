package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ArmorRubyArmor extends ItemArmor {

    public ArmorRubyArmor(String name, ArmorMaterial material, String armorType, EntityEquipmentSlot equipmentSlotIn) {

        super(material, 0, equipmentSlotIn);
        setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EntityEquipmentSlot.LEGS) {
            return "slurpiesdongles:textures/models/armor/ruby_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/ruby_layer_2.png";
        }

    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == SDItems.rubyHelmet
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == SDItems.rubyChestplate
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == SDItems.rubyLeggings
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == SDItems.rubyBoots)
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 1000, false, false));

        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == SDItems.rubyHelmet
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == SDItems.rubyChestplate
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == SDItems.rubyLeggings
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == SDItems.rubyBoots)
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 1, false, false));
    }

    public void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {

        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1000) {
            player.addPotionEffect(new PotionEffect(potion, 1000, amplifier, true, true));
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Wearing full set grants Night Vison, and Speed!");
    }
}