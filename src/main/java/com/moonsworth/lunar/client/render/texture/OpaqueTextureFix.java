package com.moonsworth.lunar.client.render.texture;

import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.util.Set;
import lombok.Generated;

public final class OpaqueTextureFix {
   private static final Set<String> field1 = ImmutableSet.of(
      "stone",
      "stone_andesite",
      "stone_andesite_smooth",
      "stone_diorite",
      "stone_diorite_smooth",
      "stone_granite",
      new String[]{
         "stone_granite_smooth",
         "stone_slab_side",
         "stone_slab_top",
         "stonebrick",
         "stonebrick_carved",
         "stonebrick_cracked",
         "stonebrick_mossy",
         "cobblestone",
         "cobblestone_mossy",
         "bedrock",
         "dirt",
         "dirt_podzol_top",
         "dirt_podzol_side",
         "coarse_dirt",
         "grass_side",
         "grass_top",
         "grass_side_snowed",
         "sand",
         "soul_sand",
         "red_sand",
         "gravel",
         "sandstone_smooth",
         "sandstone_normal",
         "sandstone_top",
         "sandstone_bottom",
         "sandstone_carved",
         "red_sandstone_smooth",
         "red_sandstone_top",
         "red_sandstone_normal",
         "red_sandstone_bottom",
         "red_sandstone_carved",
         "end_stone",
         "netherrack",
         "nether_brick",
         "mycelium_side",
         "mycelium_top",
         "hardened_clay",
         "hardened_clay_stained_black",
         "hardened_clay_stained_blue",
         "hardened_clay_stained_brown",
         "hardened_clay_stained_cyan",
         "hardened_clay_stained_gray",
         "hardened_clay_stained_green",
         "hardened_clay_stained_light_blue",
         "hardened_clay_stained_lime",
         "hardened_clay_stained_magenta",
         "hardened_clay_stained_orange",
         "hardened_clay_stained_pink",
         "hardened_clay_stained_purple",
         "hardened_clay_stained_red",
         "hardened_clay_stained_silver",
         "hardened_clay_stained_white",
         "hardened_clay_stained_yellow",
         "log_acacia",
         "log_acacia_top",
         "log_big_oak",
         "log_big_oak_top",
         "log_birch",
         "log_birch_top",
         "log_jungle",
         "log_jungle_top",
         "log_oak",
         "log_oak_top",
         "log_spruce",
         "log_spruce_top",
         "planks_acacia",
         "planks_big_oak",
         "planks_birch",
         "planks_jungle",
         "planks_oak",
         "planks_spruce",
         "quartz_block_bottom",
         "quartz_block_chiseled",
         "quartz_block_chiseled_top",
         "quartz_block_lines",
         "quartz_block_lines_top",
         "quartz_block_side",
         "quartz_block_top",
         "obsidian"
      }
   );

   public static boolean method1(BufferedImage bufferedimage0) {
      int number1 = bufferedimage0.getWidth();
      int number2 = bufferedimage0.getHeight();
      int[] items3 = bufferedimage0.getRGB(0, 0, number1, number2, null, 0, number1);

      for (int index4 = items3.length - 1; index4 >= 0; index4--) {
         int number5 = items3[index4] >> 24 & 0xFF;
         if (number5 < 200) {
            return true;
         }
      }

      return false;
   }

   public static void method2(BufferedImage bufferedimage0) {
      int number1 = bufferedimage0.getWidth();
      int number2 = bufferedimage0.getHeight();
      int[] items3 = bufferedimage0.getRGB(0, 0, number1, number2, null, 0, number1);

      for (int index4 = items3.length - 1; index4 >= 0; index4--) {
         items3[index4] |= -16777216;
      }

      bufferedimage0.setRGB(0, 0, number1, number2, items3, 0, number1);
   }

   public static boolean method3(String text, BufferedImage bufferedimage1) {
      if (field1.contains(text) && method1(bufferedimage1)) {
         method2(bufferedimage1);
         return true;
      } else {
         return false;
      }
   }

   @Generated
   private OpaqueTextureFix() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
