package com.rbs.slurpiesdongles.common.blocks;

import com.rbs.slurpiesdongles.core.init.ModFood;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class BlenderBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BlenderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH));
    }

    protected static final VoxelShape SHAPE_N = Stream.of(
            Block.box(4, 5, 4, 12, 15, 12),
            Block.box(5, 15, 5, 11, 16, 11),
            Block.box(5, 0, 5, 11, 5, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING,context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
        return true;
    }
    @Override
    public InteractionResult use(BlockState p60503, Level world, BlockPos p60505, Player player, InteractionHand hand, BlockHitResult p60508) {
        if (!world.isClientSide) {

            if (player.getItemInHand(hand).getItem() == Items.APPLE) {

                ItemStack APPLE_JUICE = new ItemStack(ModFood.APPLE_JUICE.get());

                player.getMainHandItem();
                player.getItemInHand(hand).shrink(1);
                if (!player.getInventory().add(APPLE_JUICE)) {
                    player.drop(APPLE_JUICE, false);
                }
            }
            if (player.getItemInHand(hand).getItem() == ModFood.SUGARCOATED_APPLE.get()) {

                ItemStack APPLE_SLUSHIE = new ItemStack(ModFood.APPLE_SLUSHIE.get());

                player.getMainHandItem();
                player.getItemInHand(hand).shrink(1);
                if (!player.getInventory().add(APPLE_SLUSHIE)) {
                    player.drop(APPLE_SLUSHIE, false);
                }
                if (player.getItemInHand(hand).getItem() == Items.CARROT) {

                    ItemStack CARROT_JUICE = new ItemStack(ModFood.CARROT_JUICE.get());

                    player.getMainHandItem();
                    player.getItemInHand(hand).shrink(1);
                    if (!player.getInventory().add(CARROT_JUICE)) {
                        player.drop(CARROT_JUICE, false);
                    }
                    if (player.getItemInHand(hand).getItem() == ModFood.SUGARCOATED_LEMON.get()) {

                        ItemStack LEMONADE = new ItemStack(ModFood.LEMONADE.get());

                        player.getMainHandItem();
                        player.getItemInHand(hand).shrink(1);
                        if (!player.getInventory().add(LEMONADE)) {
                            player.drop(LEMONADE, false);
                        }
                        if (player.getItemInHand(hand).getItem() == Items.MELON_SLICE) {

                            ItemStack MELON_JUICE = new ItemStack(ModFood.MELON_JUICE.get());

                            player.getMainHandItem();
                            player.getItemInHand(hand).shrink(1);
                            if (!player.getInventory().add(MELON_JUICE)) {
                                player.drop(MELON_JUICE, false);
                            }
                            if (player.getItemInHand(hand).getItem() == ModFood.SUGARCOATED_MELON.get()) {

                                ItemStack MELON_SLUSHIE = new ItemStack(ModFood.MELON_SLUSHIE.get());

                                player.getMainHandItem();
                                player.getItemInHand(hand).shrink(1);
                                if (!player.getInventory().add(MELON_SLUSHIE)) {
                                    player.drop(MELON_SLUSHIE, false);
                                }
                                if (player.getItemInHand(hand).getItem() == ModFood.ORANGE.get()) {

                                    ItemStack ORANGE_JUICE = new ItemStack(ModFood.ORANGE_JUICE.get());

                                    player.getMainHandItem();
                                    player.getItemInHand(hand).shrink(1);
                                    //player.addItem(APPLE_JUICE);
                                    if (!player.getInventory().add(ORANGE_JUICE)) {
                                        player.drop(ORANGE_JUICE, false);
                                    }
                                    if (player.getItemInHand(hand).getItem() == ModFood.SUGARCOATED_ORANGE.get()) {

                                        ItemStack ORANGE_SLUSHIE = new ItemStack(ModFood.ORANGE_SLUSHIE.get());

                                        player.getMainHandItem();
                                        player.getItemInHand(hand).shrink(1);
                                        if (!player.getInventory().add(ORANGE_SLUSHIE)) {
                                            player.drop(ORANGE_SLUSHIE, false);
                                        }
                                        if (player.getItemInHand(hand).getItem() == ModFood.STRAWBERRY.get()) {

                                            ItemStack STRAWBERRY_JUICE = new ItemStack(ModFood.STRAWBERRY_JUICE.get());

                                            player.getMainHandItem();
                                            player.getItemInHand(hand).shrink(1);
                                            if (!player.getInventory().add(STRAWBERRY_JUICE)) {
                                                player.drop(STRAWBERRY_JUICE, false);
                                            }
                                            if (player.getItemInHand(hand).getItem() == ModFood.SUGARCOATED_STRAWBERRY.get()) {

                                                ItemStack STRAWBERRY_SLUSHIE = new ItemStack(ModFood.STRAWBERRY_SLUSHIE.get());

                                                player.getMainHandItem();
                                                player.getItemInHand(hand).shrink(1);
                                                if (!player.getInventory().add(STRAWBERRY_SLUSHIE)) {
                                                    player.drop(STRAWBERRY_SLUSHIE, false);
                                                }
                                                if (player.getItemInHand(hand).getItem() == ModFood.TOMATO.get()) {

                                                    ItemStack TOMATO_JUICE = new ItemStack(ModFood.TOMATO_JUICE.get());

                                                    player.getMainHandItem();
                                                    player.getItemInHand(hand).shrink(1);
                                                    if (!player.getInventory().add(TOMATO_JUICE)) {
                                                        player.drop(TOMATO_JUICE, false);
                                                    }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
        return InteractionResult.FAIL;
    }
}
