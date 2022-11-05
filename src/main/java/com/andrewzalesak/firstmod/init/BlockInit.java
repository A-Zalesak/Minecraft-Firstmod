package com.andrewzalesak.firstmod.init;

import com.andrewzalesak.firstmod.FirstModMain;
import com.andrewzalesak.firstmod.blocks.BoomBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstModMain.MODID);

    public static final RegistryObject<Block> ANDREW_BLOCK = BLOCKS.register("andrew_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.CACTUS).strength(4f, 500f).requiresCorrectToolForDrops().lightLevel((state) -> 13)));

    public static final RegistryObject<Block> BOOM_BLOCK = BLOCKS.register("boom_block",
            () -> new BoomBlock(Block.Properties.copy(Blocks.DIRT)));

    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event .getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            BLOCKS.getEntries().forEach( (blockRegistryObject -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties = new Item.Properties().tab(ItemInit.ModCreativeTab.instance);
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            }));
        }
    }
}
