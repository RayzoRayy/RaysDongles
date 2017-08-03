package com.rbs.slurpiesdongles.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class VMPick extends net.minecraft.item.ItemPickaxe {

    protected String name;

    public VMPick(String name, ToolMaterial material) {
        super(material);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("This pick was made for use with the mod VeinMiner, so you can veinmine for a while before needing another pick.");
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;

    }

}
