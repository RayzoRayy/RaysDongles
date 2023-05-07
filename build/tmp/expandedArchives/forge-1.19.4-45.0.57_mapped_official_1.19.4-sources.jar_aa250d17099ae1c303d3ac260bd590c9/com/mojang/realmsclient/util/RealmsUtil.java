package com.mojang.realmsclient.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RealmsUtil {
   static final MinecraftSessionService SESSION_SERVICE = Minecraft.getInstance().getMinecraftSessionService();
   private static final LoadingCache<String, GameProfile> GAME_PROFILE_CACHE = CacheBuilder.newBuilder().expireAfterWrite(60L, TimeUnit.MINUTES).build(new CacheLoader<String, GameProfile>() {
      public GameProfile load(String p_90229_) {
         return RealmsUtil.SESSION_SERVICE.fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(p_90229_), (String)null), false);
      }
   });
   private static final int MINUTES = 60;
   private static final int HOURS = 3600;
   private static final int DAYS = 86400;

   public static String uuidToName(String p_90222_) {
      return GAME_PROFILE_CACHE.getUnchecked(p_90222_).getName();
   }

   public static GameProfile getGameProfile(String p_270932_) {
      return GAME_PROFILE_CACHE.getUnchecked(p_270932_);
   }

   public static String convertToAgePresentation(long p_90220_) {
      if (p_90220_ < 0L) {
         return "right now";
      } else {
         long i = p_90220_ / 1000L;
         if (i < 60L) {
            return (i == 1L ? "1 second" : i + " seconds") + " ago";
         } else if (i < 3600L) {
            long l = i / 60L;
            return (l == 1L ? "1 minute" : l + " minutes") + " ago";
         } else if (i < 86400L) {
            long k = i / 3600L;
            return (k == 1L ? "1 hour" : k + " hours") + " ago";
         } else {
            long j = i / 86400L;
            return (j == 1L ? "1 day" : j + " days") + " ago";
         }
      }
   }

   public static String convertToAgePresentationFromInstant(Date p_90224_) {
      return convertToAgePresentation(System.currentTimeMillis() - p_90224_.getTime());
   }

   public static void renderPlayerFace(PoseStack p_270077_, int p_270622_, int p_270847_, int p_270584_, String p_270810_) {
      GameProfile gameprofile = getGameProfile(p_270810_);
      ResourceLocation resourcelocation = Minecraft.getInstance().getSkinManager().getInsecureSkinLocation(gameprofile);
      RenderSystem.setShaderTexture(0, resourcelocation);
      PlayerFaceRenderer.draw(p_270077_, p_270622_, p_270847_, p_270584_);
   }
}