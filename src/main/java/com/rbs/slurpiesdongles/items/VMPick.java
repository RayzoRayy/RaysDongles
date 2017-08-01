package com.rbs.slurpiesdongles.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class VMPick extends ItemPickaxe {

    protected String name;

    public VMPick(String name, ToolMaterial material) {
        super(material);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4){
        list.add("This pick was made for use with the mod VeinMiner, so you can veinmine for a while before needing another pick. Hence the name ;)");
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;

    }
}
