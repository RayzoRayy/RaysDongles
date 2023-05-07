package net.minecraft.world.level.block.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DecoratedPotBlockEntity extends BlockEntity {
   private static final String TAG_SHARDS = "shards";
   private static final int SHARDS_IN_POT = 4;
   private boolean isBroken = false;
   private final List<Item> shards = Util.make(new ArrayList<>(4), (p_273437_) -> {
      p_273437_.add(Items.BRICK);
      p_273437_.add(Items.BRICK);
      p_273437_.add(Items.BRICK);
      p_273437_.add(Items.BRICK);
   });

   public DecoratedPotBlockEntity(BlockPos p_273660_, BlockState p_272831_) {
      super(BlockEntityType.DECORATED_POT, p_273660_, p_272831_);
   }

   protected void saveAdditional(CompoundTag p_272957_) {
      super.saveAdditional(p_272957_);
      saveShards(this.shards, p_272957_);
   }

   public void load(CompoundTag p_272924_) {
      super.load(p_272924_);
      if (p_272924_.contains("shards", 9)) {
         ListTag listtag = p_272924_.getList("shards", 8);
         this.shards.clear();
         int i = Math.min(4, listtag.size());

         for(int j = 0; j < i; ++j) {
            Tag tag = listtag.get(j);
            if (tag instanceof StringTag) {
               StringTag stringtag = (StringTag)tag;
               this.shards.add(BuiltInRegistries.ITEM.get(new ResourceLocation(stringtag.getAsString())));
            } else {
               this.shards.add(Items.BRICK);
            }
         }

         int k = 4 - i;

         for(int l = 0; l < k; ++l) {
            this.shards.add(Items.BRICK);
         }
      }

   }

   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   public CompoundTag getUpdateTag() {
      return this.saveWithoutMetadata();
   }

   public static void saveShards(List<Item> p_273539_, CompoundTag p_272709_) {
      ListTag listtag = new ListTag();

      for(Item item : p_273539_) {
         listtag.add(StringTag.valueOf(BuiltInRegistries.ITEM.getKey(item).toString()));
      }

      p_272709_.put("shards", listtag);
   }

   public ItemStack getItem() {
      ItemStack itemstack = new ItemStack(Blocks.DECORATED_POT);
      CompoundTag compoundtag = new CompoundTag();
      saveShards(this.shards, compoundtag);
      BlockItem.setBlockEntityData(itemstack, BlockEntityType.DECORATED_POT, compoundtag);
      return itemstack;
   }

   public List<Item> getShards() {
      return this.shards;
   }

   public void playerDestroy(Level p_272599_, BlockPos p_272740_, ItemStack p_273113_, Player p_273574_) {
      if (p_273574_.isCreative()) {
         this.isBroken = true;
      } else {
         if (p_273113_.is(ItemTags.BREAKS_DECORATED_POTS) && !EnchantmentHelper.hasSilkTouch(p_273113_)) {
            List<Item> list = this.getShards();
            NonNullList<ItemStack> nonnulllist = NonNullList.createWithCapacity(list.size());
            nonnulllist.addAll(0, list.stream().map(Item::getDefaultInstance).toList());
            Containers.dropContents(p_272599_, p_272740_, nonnulllist);
            this.isBroken = true;
            p_272599_.playSound((Player)null, p_272740_, SoundEvents.DECORATED_POT_SHATTER, SoundSource.PLAYERS, 1.0F, 1.0F);
         }

      }
   }

   public boolean isBroken() {
      return this.isBroken;
   }

   public Direction getDirection() {
      return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
   }

   public void setFromItem(ItemStack p_273109_) {
      CompoundTag compoundtag = BlockItem.getBlockEntityData(p_273109_);
      if (compoundtag != null) {
         this.load(compoundtag);
      }

   }
}