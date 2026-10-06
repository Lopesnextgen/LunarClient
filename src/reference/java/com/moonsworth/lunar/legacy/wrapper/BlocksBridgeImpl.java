package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge3Extension2;
import com.moonsworth.lunar.bridge.BlockButtonBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class BlocksBridgeImpl implements BlocksBridge {
   public BlocksBridgeImpl() {
   }

   public Bridge3_23 method1() {
      return (Bridge3_23)Blocks.carpet;
   }

   public Bridge3_23 method2() {
      return (Bridge3_23)Blocks.end_portal;
   }

   public Bridge3_23 method3() {
      return (Bridge3_23)Blocks.air;
   }

   public Bridge3_23 method4() {
      return (Bridge3_23)Blocks.stone;
   }

   public Bridge3_23 method5() {
      return (Bridge3_23)Blocks.snow_layer;
   }

   public Bridge3_23 method6() {
      return (Bridge3_23)Blocks.bed;
   }

   public Bridge3_23 method7() {
      return (Bridge3_23)Blocks.grass;
   }

   public List<Bridge3_23> method8() {
      return ImmutableList.of((Bridge3_23)Blocks.wool);
   }

   public Bridge3_23 method9() {
      if (Ref.MC_VERSION >= 1) {
         return (Bridge3_23)Blocks.skull;
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   public Bridge3_23 method10() {
      if (Ref.MC_VERSION >= 1) {
         return (Bridge3_23)Blocks.sea_lantern;
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   public Bridge3_23 method12() {
      return (Bridge3_23)Blocks.beacon;
   }

   public BlockButtonBridge method11() {
      return (BlockButtonBridge)Blocks.stone_button;
   }

   public Bridge3_23 method13() {
      return (Bridge3_23)Blocks.coal_block;
   }

   public Bridge3_23 method14() {
      return (Bridge3_23)Blocks.gold_block;
   }

   public Bridge3_23 method15() {
      return (Bridge3_23)Blocks.diamond_block;
   }

   public Bridge3_23 method16() {
      return (Bridge3_23)Blocks.emerald_block;
   }

   public Bridge3_23 method17() {
      return (Bridge3_23)Blocks.quartz_block;
   }

   public Bridge3_23 method18() {
      return (Bridge3_23)Blocks.hardened_clay;
   }

   public Bridge3_23 method19() {
      return (Bridge3_23)Blocks.unpowered_repeater;
   }

   public Bridge3_23 method20() {
      return (Bridge3_23)Blocks.unpowered_comparator;
   }

   public Bridge3_23 method21() {
      return (Bridge3_23)Blocks.waterlily;
   }

   public Bridge3_23 method22() {
      return (Bridge3_23)Blocks.ladder;
   }

   public Bridge3_23 method23() {
      return (Bridge3_23)Blocks.chest;
   }

   public Bridge3_23 method24() {
      return (Bridge3_23)Blocks.trapped_chest;
   }

   public Bridge3_23 method25() {
      return (Bridge3_23)Blocks.furnace;
   }

   public Bridge3_23 method26() {
      return (Bridge3_23)Blocks.crafting_table;
   }

   public Bridge3_23 method27() {
      return (Bridge3_23)Blocks.enchanting_table;
   }

   public Bridge3_23 method28() {
      return (Bridge3_23)Blocks.anvil;
   }

   public Bridge3_23 method29() {
      return (Bridge3_23)Blocks.brewing_stand;
   }

   public Bridge3_23 method30() {
      return (Bridge3_23)Blocks.dispenser;
   }

   public Bridge3_23 method31() {
      return (Bridge3_23)Blocks.dropper;
   }

   public Bridge3_23 method32() {
      return (Bridge3_23)Blocks.hopper;
   }

   public Bridge3_23 method33() {
      return (Bridge3_23)Blocks.ender_chest;
   }

   public Bridge3_23 method34() {
      return (Bridge3_23)Blocks.cocoa;
   }

   public Bridge3_23 method35() {
      return (Bridge3_23)Blocks.stone_slab;
   }

   public Bridge3_23 method36() {
      return (Bridge3_23)Blocks.end_portal_frame;
   }

   public Bridge3_23 method37() {
      if (Ref.MC_VERSION >= 1) {
         return (Bridge3_23)Blocks.prismarine;
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   public Bridge3_23 method38() {
      return (Bridge3_23)Blocks.ice;
   }

   public Bridge3_23 method39() {
      return (Bridge3_23)Blocks.packed_ice;
   }

   public Bridge3_23 method40() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method41() {
      return (Bridge3_23)Blocks.lit_furnace;
   }

   public Bridge3_23 method42() {
      return (Bridge3_23)Blocks.powered_repeater;
   }

   public Bridge3_23 method43() {
      return (Bridge3_23)Blocks.powered_comparator;
   }

   public Bridge3_23 method44() {
      return (Bridge3_23)Blocks.stained_hardened_clay;
   }

   public Bridge3_23 method45() {
      return (Bridge3_23)Blocks.stained_hardened_clay;
   }

   public Bridge3_23 method46() {
      return (Bridge3_23)Blocks.stained_hardened_clay;
   }

   public Bridge3Extension2 method47() {
      return (Bridge3Extension2)Blocks.lever;
   }

   public Bridge3_23 method48() {
      return (Bridge3_23)Blocks.redstone_block;
   }

   public Bridge3_23 method49() {
      return (Bridge3_23)Blocks.stonebrick;
   }

   public Bridge3_23 method50() {
      return (Bridge3_23)Blocks.stonebrick;
   }

   public Bridge3_23 method51() {
      return (Bridge3_23)Blocks.coal_block;
   }

   public Bridge3_23 method52() {
      return (Bridge3_23)Blocks.wheat;
   }

   public Bridge3_23 method53() {
      return (Bridge3_23)Blocks.carrots;
   }

   public Bridge3_23 method54() {
      return (Bridge3_23)Blocks.potatoes;
   }

   public Bridge3_23 method55() {
      return (Bridge3_23)Blocks.pumpkin;
   }

   public Bridge3_23 method56() {
      return (Bridge3_23)Blocks.melon_block;
   }

   public Bridge3_23 method57() {
      return Ref.MC_VERSION >= 1 ? (Bridge3_23)Blocks.reeds : (Bridge3_23)Blocks.reeds;
   }

   public Bridge3_23 method58() {
      return Ref.MC_VERSION >= 1 ? (Bridge3_23)Blocks.cactus : (Bridge3_23)Blocks.cactus$v1_7;
   }

   public Bridge3_23 method59() {
      return (Bridge3_23)Blocks.nether_wart;
   }

   public Bridge3_23 method60() {
      return (Bridge3_23)Blocks.brown_mushroom;
   }

   public Bridge3_23 method61() {
      return (Bridge3_23)Blocks.red_mushroom;
   }

   public Bridge3_23 method62() {
      return (Bridge3_23)Blocks.double_plant;
   }

   public Bridge3_23 method63() {
      return (Bridge3_23)Blocks.double_plant;
   }

   public Bridge3_23 method65() {
      return (Bridge3_23)Blocks.obsidian;
   }

   public Bridge3_23 method66() {
      return (Bridge3_23)Blocks.end_stone;
   }

   public Bridge3_23 method67() {
      return (Bridge3_23)Blocks.web;
   }

   public Bridge3_23 method68() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method108() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method69() {
      return (Bridge3_23)Blocks.monster_egg;
   }

   public Bridge3_23 method70() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method71() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method106() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method72() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method107() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method73() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method74() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method75() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method76() {
      return (Bridge3_23)Blocks.iron_block;
   }

   public Bridge3_23 method77() {
      return (Bridge3_23)Blocks.iron_ore;
   }

   public Bridge3_23 method78() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method79() {
      return (Bridge3_23)Blocks.lapis_block;
   }

   public Bridge3_23 method80() {
      return (Bridge3_23)Blocks.lapis_ore;
   }

   public Bridge3_23 method81() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method82() {
      return (Bridge3_23)Blocks.diamond_ore;
   }

   public Bridge3_23 method83() {
      return (Bridge3_23)Blocks.diamond_block;
   }

   public Bridge3_23 method84() {
      return (Bridge3_23)Blocks.emerald_block;
   }

   public Bridge3_23 method85() {
      return (Bridge3_23)Blocks.emerald_ore;
   }

   public Bridge3_23 method86() {
      return (Bridge3_23)Blocks.gold_block;
   }

   public Bridge3_23 method87() {
      return (Bridge3_23)Blocks.gold_ore;
   }

   public Bridge3_23 method88() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method89() {
      return (Bridge3_23)Blocks.redstone_ore;
   }

   public Bridge3_23 method90() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method91() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method92() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method93() {
      throw new AbstractMethodErrorImpl();
   }

   public Bridge3_23 method94() {
      return (Bridge3_23)Blocks.iron_bars;
   }

   public Bridge3_23 method95() {
      return (Bridge3_23)Blocks.stonebrick;
   }

   public Bridge3_23 method96() {
      return (Bridge3_23)Blocks.stone_brick_stairs;
   }

   public Bridge3_23 method97() {
      return (Bridge3_23)Blocks.stone_slab;
   }

   public Bridge3_23 method98() {
      return (Bridge3_23)Blocks.double_stone_slab;
   }

   @VersionGate(min = 1)
   public Bridge3_23 method99() {
      return (Bridge3_23)Blocks.stone_slab2;
   }

   @VersionGate(min = 1)
   public Bridge3_23 method100() {
      return (Bridge3_23)Blocks.double_stone_slab2;
   }

   public Bridge3_23 method101() {
      return (Bridge3_23)Blocks.stone_stairs;
   }

   public Bridge3_23 method102() {
      return (Bridge3_23)Blocks.cobblestone;
   }

   public Bridge3_23 method103() {
      return (Bridge3_23)Blocks.mossy_cobblestone;
   }

   public Bridge3_23 method104() {
      return (Bridge3_23)Blocks.cobblestone_wall;
   }

   public Bridge3_23 method105() {
      return (Bridge3_23)Blocks.cauldron;
   }

   @VersionGate(min = 1)
   public Bridge3_23 method64() {
      return (Bridge3_23)Blocks.barrier;
   }

   public Bridge3_23 method109() {
      return (Bridge3_23)Blocks.bedrock;
   }

   public Bridge3_23 method110() {
      return (Bridge3_23)Blocks.light_weighted_pressure_plate;
   }

   public Bridge3_23 method111(ResourceLocationBridge horsestats141, int number2) {
      return Ref.MC_VERSION >= 1
         ? (Bridge3_23)Block.blockRegistry.getObject((ResourceLocation)horsestats141)
         : (Bridge3_23)Block.blockRegistry$v1_7.getObject(horsestats141.toString());
   }

   public Bridge3_23 method112(String text1) {
      return this.method111(ResourceLocationBridge.create(text1), 0);
   }

   public List<Bridge3_23> method113() {
      return Ref.MC_VERSION >= 1
         ? Block.blockRegistry.registryObjects.values().stream().map(arg0 -> (Bridge3_23)arg0).toList()
         : Block.blockRegistry$v1_7.registryObjects.values().stream().toList();
   }
}
