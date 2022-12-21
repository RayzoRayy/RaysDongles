package com.ray.raysdongles.core.events;

import com.ray.raysdongles.RaysDongles;
import com.ray.raysdongles.core.config.ConfigGeneral;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RaysDongles.MOD_ID, value = Dist.CLIENT)
public class UpdateChecker {
    private static final String rdURL = "https://www.curseforge.com/minecraft/mc-mods/slurpies-dongles/files";

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void join(ClientPlayerNetworkEvent.LoggingIn event) {
        if (ConfigGeneral.COMMON.receiveUpdateMessages.get()) {
            VersionChecker.CheckResult result = VersionChecker.getResult(RaysDongles.MOD_CONTAINER.get().getModInfo());
            if (result.status() == VersionChecker.Status.BETA_OUTDATED || result.status() == VersionChecker.Status.OUTDATED) {
                MutableComponent txt = Component.literal("Ray's Dongles has a new update available, version " + result.target().getCanonical() + ". ").append(Component.literal("Click here to update!").withStyle(ChatFormatting.AQUA));
                txt.withStyle(txt.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, rdURL)));
                event.getPlayer().sendSystemMessage(txt);
            }
        }
    }
}

