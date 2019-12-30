package com.rbs.slurpiesdongles.update;

import com.rbs.slurpiesdongles.Reference;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModInfo;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class UpdateChecker {
    private static boolean isNewerVersion(String v1, String v2) {
        if (v1 == null || v2 == null) {
            System.err.println("Can't compare versions: local: "+v1+", remote: "+v2);
            return false;
        }
        String[] v1s = v1.split("\\.");
        String[] v2s = v2.split("\\.");
        if (v2s.length > v1s.length)
            return true;
        for (int i = 0; i < v2s.length; i++) {
            if (v2s[i].length() > v1s[i].length()) {
                return true;
            }
            if (v2s[i].compareTo(v1s[i]) > 0) {
                return true;
            }
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onJoinGame(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
        if (!Configuration.COMMON.showNewUpdateNotifications.get())
            return;
        System.out.println("Checking for update on join...");
        IModInfo info = ModList.get().getModContainerById(Reference.MODID).get().getModInfo();
        net.minecraftforge.fml.VersionChecker.CheckResult result = net.minecraftforge.fml.VersionChecker.getResult(info);
        if (result.target == null)
            return;
        System.out.println("Comparing versions "+info.getVersion().toString()+" and "+result.target.toString());
        if (!isNewerVersion(info.getVersion().toString(), result.target.toString())) {//result.target.compareTo(Loader.instance().activeModContainer().getVersion()) <= 0) {
            return;
        }
        System.out.println("Update available for Ray's Dongles");
        event.getPlayer().sendMessage(new TranslationTextComponent("text.new_update_notification", TextFormatting.GOLD + "Ray's Dongles! " + TextFormatting.DARK_AQUA + "Version " +result.target.toString()));
    }

}



