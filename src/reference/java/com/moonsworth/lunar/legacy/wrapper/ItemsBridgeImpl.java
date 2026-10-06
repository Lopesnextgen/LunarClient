package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ItemsBridgeImpl implements ItemsBridge {
   public ItemsBridgeImpl() {
   }

   public ItemBridge method1() {
      return (ItemBridge)Items.carrot_on_a_stick;
   }

   public ItemBridge method2() {
      return (ItemBridge)Items.fishing_rod;
   }

   public ItemBridge method3() {
      return (ItemBridge)Items.name_tag;
   }

   public ItemBridge method4() {
      return (ItemBridge)Items.lead;
   }

   public ItemBridge method5() {
      return (ItemBridge)Items.skull;
   }

   public ItemBridge method6() {
      return (ItemBridge)Items.skull;
   }

   public ItemBridge method7() {
      return (ItemBridge)Items.blaze_rod;
   }

   public ItemBridge method8() {
      return (ItemBridge)Items.bow;
   }

   public ItemBridge method9() {
      return (ItemBridge)Items.potionitem;
   }

   public ItemBridge method10() {
      return (ItemBridge)Items.stick;
   }

   public ItemBridge method11() {
      return (ItemBridge)Items.arrow;
   }

   public ItemBridge method12() {
      return (ItemBridge)Items.diamond_helmet;
   }

   public ItemBridge method13() {
      return (ItemBridge)Items.diamond_chestplate;
   }

   public ItemBridge method14() {
      return (ItemBridge)Items.diamond_leggings;
   }

   public ItemBridge method15() {
      return (ItemBridge)Items.diamond_boots;
   }

   public ItemBridge method16() {
      return (ItemBridge)Items.diamond_sword;
   }

   public ItemBridge method17() {
      return (ItemBridge)Items.dye;
   }

   public ItemBridge method21(int number1) {
      return (ItemBridge)Item.getItemById(number1);
   }

   public ItemBridge method22(String text1) {
      return Ref.MC_VERSION >= 1 ? (ItemBridge)Item.getByNameOrId(text1) : (ItemBridge)Item.itemRegistry$v1_7.getObject(text1);
   }

   public int method23(ItemBridge bridge6_41) {
      return Item.getIdFromItem((Item)bridge6_41);
   }

   public ItemBridge method18() {
      return (ItemBridge)Items.golden_sword;
   }

   public ItemBridge method19() {
      return (ItemBridge)Items.ender_pearl;
   }

   public ItemBridge method20() {
      return (ItemBridge)Items.clock;
   }

   public ItemBridge method24() {
      return Ref.MC_VERSION == 5 ? (ItemBridge)Items.ENCHANTED_BOOK$v1_12 : (ItemBridge)Items.enchanted_book;
   }

   public ItemBridge method25() {
      return Ref.MC_VERSION == 5 ? (ItemBridge)Items.AIR$v1_12 : null;
   }

   public ItemBridge method26() {
      return (ItemBridge)Items.iron_ingot;
   }

   public ItemBridge method27() {
      return (ItemBridge)Items.gold_ingot;
   }

   public ItemBridge method28() {
      return (ItemBridge)Items.diamond;
   }

   public ItemBridge method29() {
      return (ItemBridge)Items.emerald;
   }

   public ItemBridge method30() {
      return (ItemBridge)Items.filled_map;
   }

   public ItemBridge method31() {
      return (ItemBridge)Items.flint_and_steel;
   }

   public ItemBridge method32() {
      return Ref.MC_VERSION == 5 ? (ItemBridge)Items.SHIELD$v1_12 : null;
   }

   public ItemBridge method33() {
      return (ItemBridge)Items.bed;
   }

   public ItemBridge method34() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.tnt);
   }

   public ItemBridge method35() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.glowstone);
   }

   public ItemBridge method36() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.bookshelf);
   }

   public ItemBridge method37() {
      return (ItemBridge)Items.wooden_pickaxe;
   }

   public ItemBridge method38() {
      return (ItemBridge)Items.stone_pickaxe;
   }

   public ItemBridge method39() {
      return (ItemBridge)Items.golden_pickaxe;
   }

   public ItemBridge method40() {
      return (ItemBridge)Items.diamond_pickaxe;
   }

   public ItemBridge method41() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method42() {
      return Ref.MC_VERSION == 1 ? (ItemBridge)Item.getItemFromBlock(Blocks.barrier) : null;
   }

   public ItemBridge method43() {
      return (ItemBridge)Items.string;
   }

   public ItemBridge method44() {
      return (ItemBridge)Items.wheat;
   }

   public ItemBridge method45() {
      return (ItemBridge)Items.experience_bottle;
   }

   public ItemBridge method46() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.dirt);
   }

   public ItemBridge method57() {
      return (ItemBridge)Items.brick;
   }

   public ItemBridge method47() {
      return (ItemBridge)Items.golden_hoe;
   }

   public ItemBridge method48() {
      return (ItemBridge)Items.diamond_hoe;
   }

   public ItemBridge method49() {
      return (ItemBridge)Items.stone_sword;
   }

   public ItemBridge method50() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.sapling);
   }

   public ItemBridge method51() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.enchanting_table);
   }

   public ItemBridge method52() {
      return (ItemBridge)Items.brewing_stand;
   }

   public ItemBridge method53() {
      return (ItemBridge)Items.spawn_egg;
   }

   public ItemBridge method54() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.waterlily);
   }

   public ItemBridge method55() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.crafting_table);
   }

   public ItemBridge method56() {
      return (ItemBridge)Items.magma_cream;
   }

   public ItemBridge method58() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method59() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method60() {
      return (ItemBridge)Items.bone;
   }

   public ItemBridge method61() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.command_block);
   }

   public ItemBridge method62() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.lever);
   }

   public ItemBridge method63() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.cobblestone_wall);
   }

   public ItemBridge method64() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.redstone_lamp);
   }

   public ItemBridge method65() {
      return (ItemBridge)Items.apple;
   }

   public ItemBridge method66() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method67() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method68() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method69() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method70() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method71() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method72() {
      return Ref.MC_VERSION >= 5 ? (ItemBridge)Items.BEETROOT_SOUP$v1_12 : null;
   }

   public ItemBridge method73() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method74() {
      return Ref.MC_VERSION >= 5 ? (ItemBridge)Items.BEETROOT_SEEDS$v1_12 : null;
   }

   public ItemBridge method75() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method76() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method77() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method78() {
      return Ref.MC_VERSION >= 5 ? (ItemBridge)Items.DRAGON_BREATH$v1_12 : null;
   }

   public ItemBridge method79() {
      throw new AbstractMethodErrorImpl();
   }

   public ItemBridge method80() {
      return (ItemBridge)Items.carrot;
   }

   public ItemBridge method81() {
      return (ItemBridge)Items.potato;
   }

   public ItemBridge method82() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.pumpkin);
   }

   public ItemBridge method83() {
      return (ItemBridge)Items.reeds;
   }

   public ItemBridge method84() {
      return (ItemBridge)Items.melon;
   }

   public ItemBridge method85() {
      return (ItemBridge)Item.getItemFromBlock((Block)(Ref.MC_VERSION >= 1 ? Blocks.cactus : Blocks.cactus$v1_7));
   }

   public ItemBridge method86() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.cocoa);
   }

   public ItemBridge method87() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.red_mushroom);
   }

   public ItemBridge method88() {
      return (ItemBridge)Items.nether_wart;
   }

   public ItemBridge method89() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.double_plant);
   }

   public ItemBridge method90() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.double_plant);
   }

   public ItemBridge method91() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.stained_hardened_clay);
   }

   public ItemBridge method92() {
      return (ItemBridge)Item.getItemFromBlock(Blocks.stained_hardened_clay);
   }

   public List<ItemBridge> method93() {
      return ImmutableList.of((ItemBridge)Item.getItemFromBlock(Blocks.stained_glass_pane));
   }

   public List<ItemBridge> method94() {
      return ImmutableList.of((ItemBridge)Item.getItemFromBlock(Blocks.stained_glass));
   }

   public List<ItemBridge> method96() {
      return ImmutableList.of((ItemBridge)Item.getItemFromBlock(Blocks.stained_hardened_clay));
   }

   public List<ItemBridge> method97() {
      return ImmutableList.of((ItemBridge)Item.getItemFromBlock(Blocks.wool));
   }

   public List<ItemBridge> method95() {
      return ImmutableList.of((ItemBridge)Items.dye);
   }

   public ItemBridge method98() {
      return (ItemBridge)Items.compass;
   }

   public List<ItemBridge> method99() {
      if (Ref.MC_VERSION < 5) {
         return Ref.MC_VERSION >= 1
            ? Item.itemRegistry.registryObjects.values().stream().map(arg0 -> (ItemBridge)arg0).toList()
            : Item.itemRegistry$v1_7.registryObjects.values().stream().map(arg0 -> (ItemBridge)arg0).toList();
      }

      Builder builder1 = ImmutableList.builder();

      for (Item item3 : Item.itemRegistry) {
         builder1.add((ItemBridge)item3);
      }

      return builder1.build();
   }
}
