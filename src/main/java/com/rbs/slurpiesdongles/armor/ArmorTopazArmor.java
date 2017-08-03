package com.rbs.slurpiesdongles.armor;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ArmorTopazArmor extends ItemArmor {

    protected String name;

    public ArmorTopazArmor(String unlocalizedName, ArmorMaterial material, String armorType, EntityEquipmentSlot equipmentSlotIn) {

        super(material, 0, equipmentSlotIn);
        this.setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = unlocalizedName;
        setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot equipmentSlotIn, String layer) {
        if (equipmentSlotIn != EntityEquipmentSlot.LEGS) {
            return "slurpiesdongles:textures/models/armor/topaz_layer_1.png";
        } else {
            return "slurpiesdongles:textures/models/armor/topaz_layer_2.png";
        }

    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == SDItems.topazHelmet
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == SDItems.topazChestplate
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == SDItems.topazLeggings
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == SDItems.topazBoots) {
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 1000, false, false));

            if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == SDItems.topazHelmet
                    && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == SDItems.topazChestplate
                    && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == SDItems.topazLeggings
                    && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == SDItems.topazBoots)
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 1, false, false));

            if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == SDItems.topazHelmet
                    && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == SDItems.topazChestplate
                    && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == SDItems.topazLeggings
                    && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == SDItems.topazBoots)
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 1, false, false));


            if (!player.capabilities.isCreativeMode) {
                if (!player.capabilities.allowFlying) {
                    player.capabilities.allowFlying = true;

                }

            }
        } else {
            if (!player.capabilities.isCreativeMode) {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;

            }

        }


    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;

    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Wearing full set grants Flight, Night Vison, Speed, and Fire Resistance!");
    }

}
