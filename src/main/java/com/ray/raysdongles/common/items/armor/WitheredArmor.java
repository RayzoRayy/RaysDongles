package com.ray.raysdongles.common.items.armor;

import com.ray.raysdongles.core.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class WitheredArmor extends ArmorItem {

    public WitheredArmor(ArmorMaterial materialIn, EquipmentSlot slots, Item.Properties builder) {
        super(materialIn, slots, builder);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD) != null && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.WITHERED_HELMET.get()
                && player.getItemBySlot(EquipmentSlot.CHEST) != null && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.WITHERED_CHEST.get()
                && player.getItemBySlot(EquipmentSlot.LEGS) != null && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.WITHERED_LEGS.get()
                && player.getItemBySlot(EquipmentSlot.FEET) != null && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.WITHERED_BOOTS.get()) {

            if (!player.isCreative()) {
                if (!player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = true;
                }
            }
        } else {
            if (!player.isCreative()) {
                player.getAbilities().flying = false;
                player.getAbilities().mayfly = false;
            }
        }
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.EPIC : Rarity.EPIC;

    }

    public boolean isRepairable(ItemStack repair) {
        return repair.getItem() == Items.COAL;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> toolTip, TooltipFlag tooltipFlag) {
        toolTip.add(Component.translatable("Wearing this armor as a set grants flight"));
    }
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return true;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return true;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level p_41448_, net.minecraft.world.entity.player.Player p_41449_) {
        stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
    }
}
