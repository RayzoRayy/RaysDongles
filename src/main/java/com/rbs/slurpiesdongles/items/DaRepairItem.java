package com.rbs.slurpiesdongles.items;

/**
 * Created by Consular on 7/21/2017.
 */

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.events.ItemHelper;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import com.rbs.slurpiesdongles.events.Changer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import scala.collection.generic.Growable;

import javax.annotation.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.List;

/**
 * Created by RedBu on 3/11/2017.
 */
@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class DaRepairItem extends ItemBase { //implements IBauble {

    private static final String TAG_REPAIR = "repair";
    private static final String TAG_REPAIR_TIMER = "repair.timer";


    public DaRepairItem(String name) {
        super(name);
        this.maxStackSize = 1;
        setCreativeTab(SlurpiesDongles.creativeTab);
    }


    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {

        if (!world.isRemote) {
            EntityPlayer player = (EntityPlayer) entity;

            //145 = 7 1/2 seconds for repair time. 20 Ticks per second, 145 = 7 1/2 seconds per repair
            if (this.getNBTTagInt(stack, TAG_REPAIR_TIMER) >= 145) {
                repairAllItems(player);
                this.updateNBTDataInt(stack, TAG_REPAIR_TIMER, 1);
            } else {

                this.updateNBTDataInt(stack, TAG_REPAIR_TIMER, this.getNBTTagInt(stack, TAG_REPAIR_TIMER) + 1);
            }


        }



    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        createNBTData(itemStack);
    }
    private static void createNBTData(ItemStack itemStack) {
        NBTTagCompound data = new NBTTagCompound();
        data.setInteger(TAG_REPAIR_TIMER, 0);
        itemStack.setTagInfo(TAG_REPAIR, data);
    }
    private static void updateNBTDataInt(ItemStack itemStack, String tag, int value) {

        if (itemStack.getTagCompound()!=null) {
            NBTTagCompound data = itemStack.getTagCompound().getCompoundTag(TAG_REPAIR);
            data.setInteger(tag, value);
            itemStack.setTagInfo(TAG_REPAIR, data);
        }
        else {
            createNBTData(itemStack);
        }
    }

    private static int getNBTTagInt(ItemStack itemStack, String tag) {
        if (itemStack.getTagCompound()!=null){
            NBTTagCompound data = itemStack.getTagCompound().getCompoundTag(TAG_REPAIR);
            return data.getInteger(tag);
        }
        else{
            return 0;
        }
    }


    private void repairAllItems(EntityPlayer player) {


        IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        for (int i = 0; i < inv.getSlots(); i++) {
            ItemStack invStack = inv.getStackInSlot(i);

            if (invStack == null || invStack.getItem() instanceof Changer || !invStack.getItem().isRepairable()) {
                continue;
            }

            if (Loader.isModLoaded("chisel")) {
                //if (chiselCheck(invStack)) continue;
            }

            if (invStack == player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) && player.isSwingInProgress) {
                //Don't repair item that is currently used by the player.
                continue;
            }

            if (ItemHelper.isDamageable(invStack) && invStack.getItemDamage() > 0) {
                invStack.setItemDamage(invStack.getItemDamage() - 1);
            }

        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Have this item in your inventory and it will repair any item that has durability every 7 1/2 seconds!");
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;

    }
}
