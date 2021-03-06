package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSword extends net.minecraft.item.ItemSword {

    protected String name;

    public ItemSword(String name, ToolMaterial material) {
        super(material);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    //This is to add a potion effect to a mob upon hitting them
    /*@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 1200, 1));

        return true;*/

    //This is to add an enchantment to a weapon by ID
    /*public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.addEnchantment(Enchantment.getEnchantmentByID(20), 3);

    }*/

    //This Method adds an enchant to a specified weapon
    /*@Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (stack.getItem() == SDItems.sapphireSword) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(20), 3);
        }
        super.onCreated(stack, worldIn, playerIn);
    }
*/

}
