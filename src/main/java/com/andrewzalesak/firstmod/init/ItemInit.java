package com.andrewzalesak.firstmod.init;

import com.andrewzalesak.firstmod.FirstModMain;
import com.andrewzalesak.firstmod.items.FuelItem;
import com.andrewzalesak.firstmod.items.TeleportWristwatch;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstModMain.MODID);
    public static final RegistryObject<Item> KANJI = ITEMS.register("kanji",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> SALAD = ITEMS.register("salad",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance).food(
                    new FoodProperties.Builder().nutrition(5).saturationMod(1)
                            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 200), 0.6F)
                            .alwaysEat()
                            .build()
            )));

    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties().tab(ModCreativeTab.instance), 3200));

    public static final RegistryObject<Item> TELEPORT_WRISTWATCH = ITEMS.register("teleport_wristwatch",
            () -> new TeleportWristwatch(new Item.Properties().tab(ModCreativeTab.instance).durability(50)));
    public static class ModCreativeTab extends CreativeModeTab {
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "firstmod");
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(KANJI.get());
        }
    }
}

