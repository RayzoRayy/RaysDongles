package com.rbs.slurpiesdongles.armor.materials;

import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;

import java.util.function.Supplier;

public enum ArmorMaterials implements IArmorMaterial{

    AMAZONITE ("amazonite_armor", 28, new int[]{3, 6, 7, 4}, 37, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
        return Ingredient.fromItems(ModItems.amazonite);
    }),
    AMETHYST ("amethyst_armor", 23, new int[]{3, 6, 7, 4}, 31, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
        return Ingredient.fromItems(ModItems.amethyst);
    }),

    PERIDOT ("peridot_armor", 20, new int[]{2, 5, 5, 2}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F, () -> {
        return Ingredient.fromItems(ModItems.peridot);
    }),
    RUBY ("ruby_armor", 26, new int[]{2, 6, 6, 2}, 24, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, () -> {
        return Ingredient.fromItems(ModItems.ruby);
    }),
    SAPPHIRE ("sapphire_armor", 20, new int[]{2, 5, 6, 2}, 26, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, () -> {
        return Ingredient.fromItems(ModItems.sapphire);
    }),
    TOPAZ ("topaz_armor", 0, new int[]{4, 7, 6, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
        return Ingredient.fromItems(ModItems.topaz);
    }),

    WITHERED ("withered_armor", 0, new int[]{5, 8, 7, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, () -> {
        return Ingredient.fromItems(Items.NETHER_STAR, Items.WITHER_SKELETON_SKULL);
    });



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
    private final Lazy<Ingredient> repairMaterial;

    ArmorMaterials(String nameIn, int p_i48533_4_, int[] p_i48533_5_, int p_i48533_6_, SoundEvent p_i48533_7_, float p_i48533_8_, Supplier<Ingredient> p_i48533_9_) {
        this.name = nameIn;
        this.maxDamageFactor = p_i48533_4_;
        this.damageReductionAmountArray = p_i48533_5_;
        this.enchantability = p_i48533_6_;
        this.soundEvent = p_i48533_7_;
        this.toughness = p_i48533_8_;
        this.repairMaterial = () -> (Ingredient) p_i48533_9_;
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public static ArmorMaterial create(String name, String nameIn, int p_i48533_4_, int[] p_i48533_5_, int p_i48533_6_, SoundEvent p_i48533_7_, float p_i48533_8_, Supplier<Ingredient> p_i48533_9_) {
        throw new IllegalStateException("Enum not extended");
    }
}

