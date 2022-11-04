package com.andrewzalesak.firstmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BoomBlock extends Block {
    public BoomBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);

        if (!world.isClientSide() && held.getItem() == Items.COOKED_CHICKEN) {
            world.explode(player, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.BlockInteraction.DESTROY);
            held.shrink(1);
            return InteractionResult.CONSUME;
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    // Goes boom due to other boom
    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        // another boom
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Explosion.BlockInteraction.DESTROY);

        super.wasExploded(world, pos, explosion);
    }

}
