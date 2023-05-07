package net.minecraft.client.telemetry;

import java.time.Duration;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.telemetry.events.PerformanceMetricsEvent;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.client.telemetry.events.WorldLoadTimesEvent;
import net.minecraft.client.telemetry.events.WorldUnloadEvent;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorldSessionTelemetryManager {
   private final UUID worldSessionId = UUID.randomUUID();
   private final TelemetryEventSender eventSender;
   private final WorldLoadEvent worldLoadEvent;
   private final WorldUnloadEvent worldUnloadEvent = new WorldUnloadEvent();
   private final PerformanceMetricsEvent performanceMetricsEvent;
   private final WorldLoadTimesEvent worldLoadTimesEvent;

   public WorldSessionTelemetryManager(TelemetryEventSender p_261695_, boolean p_261623_, @Nullable Duration p_261937_) {
      this.worldLoadEvent = new WorldLoadEvent();
      this.performanceMetricsEvent = new PerformanceMetricsEvent();
      this.worldLoadTimesEvent = new WorldLoadTimesEvent(p_261623_, p_261937_);
      this.eventSender = p_261695_.decorate((p_261981_) -> {
         this.worldLoadEvent.addProperties(p_261981_);
         p_261981_.put(TelemetryProperty.WORLD_SESSION_ID, this.worldSessionId);
      });
   }

   public void tick() {
      this.performanceMetricsEvent.tick(this.eventSender);
   }

   public void onPlayerInfoReceived(GameType p_261768_, boolean p_261669_) {
      this.worldLoadEvent.setGameMode(p_261768_, p_261669_);
      this.worldUnloadEvent.onPlayerInfoReceived();
      this.worldSessionStart();
   }

   public void onServerBrandReceived(String p_261520_) {
      this.worldLoadEvent.setServerBrand(p_261520_);
      this.worldSessionStart();
   }

   public void setTime(long p_261878_) {
      this.worldUnloadEvent.setTime(p_261878_);
   }

   public void worldSessionStart() {
      if (this.worldLoadEvent.send(this.eventSender)) {
         this.worldLoadTimesEvent.send(this.eventSender);
         this.performanceMetricsEvent.start();
      }

   }

   public void onDisconnect() {
      this.worldLoadEvent.send(this.eventSender);
      this.performanceMetricsEvent.stop();
      this.worldUnloadEvent.send(this.eventSender);
   }
}