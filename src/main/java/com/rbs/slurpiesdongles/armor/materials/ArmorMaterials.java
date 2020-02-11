package com.rbs.slurpiesdongles.armor.materials;

import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;

import java.util.function.Supplier;

public enum ArmorMaterials implements IArmorMaterial{

    AMETHYST ("amethyst_armor", 34, new int[]{3, 6, 7, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, ModItems.amethyst),

    RUBY ("ruby_armor", 16, new int[]{2, 6, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F, ModItems.ruby),

    TOPAZ ("topaz_armor", 35, new int[]{4, 6, 8, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, ModItems.topaz),

    WITHERED ("withered_armor", 0, new int[]{4, 6, 7, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, Items.WITHER_SKELETON_SKULL),;




    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    /**
     * Holds the maximum damage factor (each piece multiply this by it's own value) of the material, this is the item
     * damage (how much can absorb before breaks)
     */
    private final int maxDamageFactor;
    /**
     * Holds the damage reduction (each 1 points is half a shield on gui) of each piece of armor (helmet, plate, legs and
     * boots)
     */
    private final int[] damageReductionAmountArray;
    /** Return the enchantability factor of the material */
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private Item repairItem;

    ArmorMaterials(String nameIn, int p_i48533_4_, int[] p_i48533_5_, int p_i48533_6_, SoundEvent p_i48533_7_, float p_i48533_8_, Item repairItem) {
        this.name = nameIn;
        this.maxDamageFactor = p_i48533_4_;
        this.damageReductionAmountArray = p_i48533_5_;
        this.enchantability = p_i48533_6_;
        this.soundEvent = p_i48533_7_;
        this.toughness = p_i48533_8_;
        this.repairItem = repairItem;
    }
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }
    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }
    @Override
    public int getEnchantability() {
        return this.enchantability;
    }
    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairItem);
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }
}

