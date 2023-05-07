package net.minecraft.network.protocol.game;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ClientboundRespawnPacket implements Packet<ClientGamePacketListener> {
   public static final byte KEEP_ATTRIBUTES = 1;
   public static final byte KEEP_ENTITY_DATA = 2;
   public static final byte KEEP_ALL_DATA = 3;
   private final ResourceKey<DimensionType> dimensionType;
   private final ResourceKey<Level> dimension;
   private final long seed;
   private final GameType playerGameType;
   @Nullable
   private final GameType previousPlayerGameType;
   private final boolean isDebug;
   private final boolean isFlat;
   private final byte dataToKeep;
   private final Optional<GlobalPos> lastDeathLocation;

   public ClientboundRespawnPacket(ResourceKey<DimensionType> p_263565_, ResourceKey<Level> p_263578_, long p_263572_, GameType p_263577_, @Nullable GameType p_263566_, boolean p_263576_, boolean p_263569_, byte p_263570_, Optional<GlobalPos> p_263580_) {
      this.dimensionType = p_263565_;
      this.dimension = p_263578_;
      this.seed = p_263572_;
      this.playerGameType = p_263577_;
      this.previousPlayerGameType = p_263566_;
      this.isDebug = p_263576_;
      this.isFlat = p_263569_;
      this.dataToKeep = p_263570_;
      this.lastDeathLocation = p_263580_;
   }

   public ClientboundRespawnPacket(FriendlyByteBuf p_179191_) {
      this.dimensionType = p_179191_.readResourceKey(Registries.DIMENSION_TYPE);
      this.dimension = p_179191_.readResourceKey(Registries.DIMENSION);
      this.seed = p_179191_.readLong();
      this.playerGameType = GameType.byId(p_179191_.readUnsignedByte());
      this.previousPlayerGameType = GameType.byNullableId(p_179191_.readByte());
      this.isDebug = p_179191_.readBoolean();
      this.isFlat = p_179191_.readBoolean();
      this.dataToKeep = p_179191_.readByte();
      this.lastDeathLocation = p_179191_.readOptional(FriendlyByteBuf::readGlobalPos);
   }

   public void write(FriendlyByteBuf p_132954_) {
      p_132954_.writeResourceKey(this.dimensionType);
      p_132954_.writeResourceKey(this.dimension);
      p_132954_.writeLong(this.seed);
      p_132954_.writeByte(this.playerGameType.getId());
      p_132954_.writeByte(GameType.getNullableId(this.previousPlayerGameType));
      p_132954_.writeBoolean(this.isDebug);
      p_132954_.writeBoolean(this.isFlat);
      p_132954_.writeByte(this.dataToKeep);
      p_132954_.writeOptional(this.lastDeathLocation, FriendlyByteBuf::writeGlobalPos);
   }

   public void handle(ClientGamePacketListener p_132951_) {
      p_132951_.handleRespawn(this);
   }

   public ResourceKey<DimensionType> getDimensionType() {
      return this.dimensionType;
   }

   public ResourceKey<Level> getDimension() {
      return this.dimension;
   }

   public long getSeed() {
      return this.seed;
   }

   public GameType getPlayerGameType() {
      return this.playerGameType;
   }

   @Nullable
   public GameType getPreviousPlayerGameType() {
      return this.previousPlayerGameType;
   }

   public boolean isDebug() {
      return this.isDebug;
   }

   public boolean isFlat() {
      return this.isFlat;
   }

   public boolean shouldKeep(byte p_263573_) {
      return (this.dataToKeep & p_263573_) != 0;
   }

   public Optional<GlobalPos> getLastDeathLocation() {
      return this.lastDeathLocation;
   }
}