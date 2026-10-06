package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge2_21;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Bridge2Iterator implements Bridge2_21 {
   public Bridge6_4 method1() {
      return (Bridge6_4)Items.carrot_on_a_stick;
   }

   public Bridge6_4 method2() {
      return (Bridge6_4)Items.fishing_rod;
   }

   public Bridge6_4 method3() {
      return (Bridge6_4)Items.name_tag;
   }

   public Bridge6_4 method4() {
      return (Bridge6_4)Items.lead;
   }

   public Bridge6_4 method5() {
      return (Bridge6_4)Items.skull;
   }

   public Bridge6_4 method6() {
      return (Bridge6_4)Items.skull;
   }

   public Bridge6_4 method7() {
      return (Bridge6_4)Items.blaze_rod;
   }

   public Bridge6_4 method8() {
      return (Bridge6_4)Items.bow;
   }

   public Bridge6_4 method9() {
      return (Bridge6_4)Items.potionitem;
   }

   public Bridge6_4 method10() {
      return (Bridge6_4)Items.stick;
   }

   public Bridge6_4 method11() {
      return (Bridge6_4)Items.arrow;
   }

   public Bridge6_4 method12() {
      return (Bridge6_4)Items.diamond_helmet;
   }

   public Bridge6_4 method13() {
      return (Bridge6_4)Items.diamond_chestplate;
   }

   public Bridge6_4 method14() {
      return (Bridge6_4)Items.diamond_leggings;
   }

   public Bridge6_4 method15() {
      return (Bridge6_4)Items.diamond_boots;
   }

   public Bridge6_4 method16() {
      return (Bridge6_4)Items.diamond_sword;
   }

   public Bridge6_4 method17() {
      return (Bridge6_4)Items.dye;
   }

   public Bridge6_4 method21(int var1) {
      return (Bridge6_4)Item.getItemById(var1);
   }

   public Bridge6_4 method22(String var1) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge6_4)Item.getByNameOrId(var1) : (Bridge6_4)Item.itemRegistry$v1_7.getObject(var1);
   }

   public int method23(Bridge6_4 var1) {
      return Item.getIdFromItem((Item)var1);
   }

   public Bridge6_4 method18() {
      return (Bridge6_4)Items.golden_sword;
   }

   public Bridge6_4 method19() {
      return (Bridge6_4)Items.ender_pearl;
   }

   public Bridge6_4 method20() {
      return (Bridge6_4)Items.clock;
   }

   public Bridge6_4 method24() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Bridge6_4)Items.ENCHANTED_BOOK$v1_12 : (Bridge6_4)Items.enchanted_book;
   }

   public Bridge6_4 method25() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Bridge6_4)Items.AIR$v1_12 : null;
   }

   public Bridge6_4 method26() {
      return (Bridge6_4)Items.iron_ingot;
   }

   public Bridge6_4 method27() {
      return (Bridge6_4)Items.gold_ingot;
   }

   public Bridge6_4 method28() {
      return (Bridge6_4)Items.diamond;
   }

   public Bridge6_4 method29() {
      return (Bridge6_4)Items.emerald;
   }

   public Bridge6_4 method30() {
      return (Bridge6_4)Items.filled_map;
   }

   public Bridge6_4 method31() {
      return (Bridge6_4)Items.flint_and_steel;
   }

   public Bridge6_4 method32() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Bridge6_4)Items.SHIELD$v1_12 : null;
   }

   public Bridge6_4 method33() {
      return (Bridge6_4)Items.bed;
   }

   public Bridge6_4 method34() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.tnt);
   }

   public Bridge6_4 method35() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.glowstone);
   }

   public Bridge6_4 method36() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.bookshelf);
   }

   public Bridge6_4 method37() {
      return (Bridge6_4)Items.wooden_pickaxe;
   }

   public Bridge6_4 method38() {
      return (Bridge6_4)Items.stone_pickaxe;
   }

   public Bridge6_4 method39() {
      return (Bridge6_4)Items.golden_pickaxe;
   }

   public Bridge6_4 method40() {
      return (Bridge6_4)Items.diamond_pickaxe;
   }

   public Bridge6_4 method41() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method42() {
      return ThreadModuleDump63.MC_VERSION == 1 ? (Bridge6_4)Item.getItemFromBlock(Blocks.barrier) : null;
   }

   public Bridge6_4 method43() {
      return (Bridge6_4)Items.string;
   }

   public Bridge6_4 method44() {
      return (Bridge6_4)Items.wheat;
   }

   public Bridge6_4 method45() {
      return (Bridge6_4)Items.experience_bottle;
   }

   public Bridge6_4 method46() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.dirt);
   }

   public Bridge6_4 method57() {
      return (Bridge6_4)Items.brick;
   }

   public Bridge6_4 method47() {
      return (Bridge6_4)Items.golden_hoe;
   }

   public Bridge6_4 method48() {
      return (Bridge6_4)Items.diamond_hoe;
   }

   public Bridge6_4 method49() {
      return (Bridge6_4)Items.stone_sword;
   }

   public Bridge6_4 method50() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.sapling);
   }

   public Bridge6_4 method51() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.enchanting_table);
   }

   public Bridge6_4 method52() {
      return (Bridge6_4)Items.brewing_stand;
   }

   public Bridge6_4 method53() {
      return (Bridge6_4)Items.spawn_egg;
   }

   public Bridge6_4 method54() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.waterlily);
   }

   public Bridge6_4 method55() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.crafting_table);
   }

   public Bridge6_4 method56() {
      return (Bridge6_4)Items.magma_cream;
   }

   public Bridge6_4 method58() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method59() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method60() {
      return (Bridge6_4)Items.bone;
   }

   public Bridge6_4 method61() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.command_block);
   }

   public Bridge6_4 method62() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.lever);
   }

   public Bridge6_4 method63() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.cobblestone_wall);
   }

   public Bridge6_4 method64() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.redstone_lamp);
   }

   public Bridge6_4 method65() {
      return (Bridge6_4)Items.apple;
   }

   public Bridge6_4 method66() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method67() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method68() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method69() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method70() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method71() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method72() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (Bridge6_4)Items.BEETROOT_SOUP$v1_12 : null;
   }

   public Bridge6_4 method73() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method74() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (Bridge6_4)Items.BEETROOT_SEEDS$v1_12 : null;
   }

   public Bridge6_4 method75() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method76() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method77() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method78() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (Bridge6_4)Items.DRAGON_BREATH$v1_12 : null;
   }

   public Bridge6_4 method79() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge6_4 method80() {
      return (Bridge6_4)Items.carrot;
   }

   public Bridge6_4 method81() {
      return (Bridge6_4)Items.potato;
   }

   public Bridge6_4 method82() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.pumpkin);
   }

   public Bridge6_4 method83() {
      return (Bridge6_4)Items.reeds;
   }

   public Bridge6_4 method84() {
      return (Bridge6_4)Items.melon;
   }

   public Bridge6_4 method85() {
      return (Bridge6_4)Item.getItemFromBlock((Block)(ThreadModuleDump63.MC_VERSION >= 1 ? Blocks.cactus : Blocks.cactus$v1_7));
   }

   public Bridge6_4 method86() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.cocoa);
   }

   public Bridge6_4 method87() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.red_mushroom);
   }

   public Bridge6_4 method88() {
      return (Bridge6_4)Items.nether_wart;
   }

   public Bridge6_4 method89() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.double_plant);
   }

   public Bridge6_4 method90() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.double_plant);
   }

   public Bridge6_4 method91() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.stained_hardened_clay);
   }

   public Bridge6_4 method92() {
      return (Bridge6_4)Item.getItemFromBlock(Blocks.stained_hardened_clay);
   }

   public List<Bridge6_4> method93() {
      return ImmutableList.of((Bridge6_4)Item.getItemFromBlock(Blocks.stained_glass_pane));
   }

   public List<Bridge6_4> method94() {
      return ImmutableList.of((Bridge6_4)Item.getItemFromBlock(Blocks.stained_glass));
   }

   public List<Bridge6_4> method96() {
      return ImmutableList.of((Bridge6_4)Item.getItemFromBlock(Blocks.stained_hardened_clay));
   }

   public List<Bridge6_4> method97() {
      return ImmutableList.of((Bridge6_4)Item.getItemFromBlock(Blocks.wool));
   }

   public List<Bridge6_4> method95() {
      return ImmutableList.of((Bridge6_4)Items.dye);
   }

   public Bridge6_4 method98() {
      return (Bridge6_4)Items.compass;
   }

   public List<Bridge6_4> method99() {
      if (ThreadModuleDump63.MC_VERSION < 5) {
         return ThreadModuleDump63.MC_VERSION >= 1
            ? Item.itemRegistry.registryObjects.values().stream().map(var0 -> (Bridge6_4)var0).toList()
            : Item.itemRegistry$v1_7.registryObjects.values().stream().map(var0 -> (Bridge6_4)var0).toList();
      }

      Builder var1 = ImmutableList.builder();

      for (Item var3 : Item.itemRegistry) {
         var1.add((Bridge6_4)var3);
      }

      return var1.build();
   }
}
