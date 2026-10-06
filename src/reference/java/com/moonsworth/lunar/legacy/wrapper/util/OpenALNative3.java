package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class OpenALNative3 {
   public static boolean method1(Itemcounter6 var0, Vector3iBridge var1, Bridge2_17 var2, ItemcounterType_3 var3) {
      Bridge3_23 var4 = var2.bridge$getBlock();
      Block var5 = (Block)var4;
      if (var5 != Blocks.dragon_egg && var5 != Blocks.cauldron) {
         if (var5 instanceof BlockBush) {
            if (var3 == ItemcounterType_3.AIR && !var4.bridge$hasCollision(var0, var1)) {
               return true;
            }
         } else {
            if (var5 instanceof BlockSnow) {
               return switch (var3) {
                  case LAND -> var2.bridge$getLayersValue() < 5;
                  case WATER, AIR -> false;
                  default -> throw new IncompatibleClassChangeError();
               };
            }

            if (var5 instanceof BlockTrapDoor || var5 instanceof BlockDoor || var5 instanceof BlockFenceGate) {
               return switch (var3) {
                  case LAND, AIR -> var2.bridge$getOpenValue();
                  case WATER -> false;
                  default -> throw new IncompatibleClassChangeError();
               };
            }

            if (var5 instanceof BlockLiquid) {
               return var4.bridge$isWater();
            }

            if (var5 instanceof BlockPistonMoving) {
               return false;
            }
         }
         return switch (var3) {
            case LAND, AIR -> ThreadModuleDump63.MC_VERSION <= 1
               ? !var5.getMaterial().blocksMovement() && !var5.isFullBlock()
               : var5.isNormalCube((IBlockState)var2);
            case WATER -> var4.bridge$isWater();
            default -> throw new IncompatibleClassChangeError();
         };
      } else {
         return false;
      }
   }
}
