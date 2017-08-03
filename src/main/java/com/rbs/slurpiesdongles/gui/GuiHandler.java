package com.rbs.slurpiesdongles.gui;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by patrick on 29/07/2017.
 */
public class GuiHandler implements IGuiHandler {

    public static final int WAND = 0;


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == WAND)
            return new GuiTimeWand();
        return null;
    }
}
