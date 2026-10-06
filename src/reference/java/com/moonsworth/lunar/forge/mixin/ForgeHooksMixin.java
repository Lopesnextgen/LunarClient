package com.moonsworth.lunar.forge.mixin;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ForgeHooks.class)
public abstract class ForgeHooksMixin {
   @Unique
   private static final Block[] ORE_BLOCKS = new Block[]{
      Blocks.emerald_ore,
      Blocks.emerald_block,
      Blocks.diamond_ore,
      Blocks.diamond_block,
      Blocks.gold_ore,
      Blocks.gold_block,
      Blocks.redstone_ore,
      Blocks.lit_redstone_ore
   };
   @Shadow
   public static boolean toolInit;

   public ForgeHooksMixin() {
   }

   @Overwrite
   public static void initTools() {
      if (!toolInit) {
         toolInit = true;

         for (Block block1 : ItemPickaxe.EFFECTIVE_ON) {
            ichor$setHarvestLevel(block1, "pickaxe", 0);
         }

         for (Block block7 : ItemSpade.EFFECTIVE_ON) {
            ichor$setHarvestLevel(block7, "shovel", 0);
         }

         for (Block block8 : ItemAxe.EFFECTIVE_ON) {
            ichor$setHarvestLevel(block8, "axe", 0);
         }

         ichor$setHarvestLevel(Blocks.obsidian, "pickaxe", 3);
         ichor$setHarvestLevel(Blocks.enchanting_table, "pickaxe", 0);

         for (Block block3 : ORE_BLOCKS) {
            ichor$setHarvestLevel(block3, "pickaxe", 2);
         }

         ichor$setHarvestLevel(Blocks.iron_ore, "pickaxe", 1);
         ichor$setHarvestLevel(Blocks.iron_block, "pickaxe", 1);
         ichor$setHarvestLevel(Blocks.lapis_ore, "pickaxe", 1);
         ichor$setHarvestLevel(Blocks.lapis_block, "pickaxe", 1);
         ichor$setHarvestLevel(Blocks.quartz_ore, "pickaxe", 0);
      }
   }

   private static void ichor$setHarvestLevel(Block block0, String text1, int number2) {
      try {
         block0.getClass().getMethod("setHarvestLevel", String.class, int.class).invoke(block0, text1, number2);
      } catch (Exception exception4) {
         throw new RuntimeException(exception4);
      }
   }
}
