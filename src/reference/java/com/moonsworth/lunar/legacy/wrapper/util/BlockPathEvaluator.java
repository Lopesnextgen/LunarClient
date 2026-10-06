package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.PathTypeBridge;
import com.moonsworth.lunar.client.framework.Ref;
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

public class BlockPathEvaluator {
   public BlockPathEvaluator() {
   }

   public static boolean method1(Itemcounter6 itemcounter60, Vec3iBridge horsestats201, BlockStateBridge bridge2_172, PathTypeBridge itemcountertype_33) {
      Bridge3_23 bridge3_234 = bridge2_172.bridge$getBlock();
      Block block5 = (Block)bridge3_234;
      if (block5 != Blocks.dragon_egg && block5 != Blocks.cauldron) {
         if (block5 instanceof BlockBush) {
            if (itemcountertype_33 == PathTypeBridge.AIR && !bridge3_234.bridge$hasCollision(itemcounter60, horsestats201)) {
               return true;
            }
         } else {
            if (block5 instanceof BlockSnow) {
               return switch (itemcountertype_33) {
                  case LAND -> bridge2_172.bridge$getLayersValue() < 5;
                  case WATER, AIR -> false;
                  default -> throw new IncompatibleClassChangeError();
               };
            }

            if (block5 instanceof BlockTrapDoor || block5 instanceof BlockDoor || block5 instanceof BlockFenceGate) {
               return switch (itemcountertype_33) {
                  case LAND, AIR -> bridge2_172.bridge$getOpenValue();
                  case WATER -> false;
                  default -> throw new IncompatibleClassChangeError();
               };
            }

            if (block5 instanceof BlockLiquid) {
               return bridge3_234.bridge$isWater();
            }

            if (block5 instanceof BlockPistonMoving) {
               return false;
            }
         }
         return switch (itemcountertype_33) {
            case LAND, AIR -> Ref.MC_VERSION <= 1
               ? !block5.getMaterial().blocksMovement() && !block5.isFullBlock()
               : block5.isNormalCube((IBlockState)bridge2_172);
            case WATER -> bridge3_234.bridge$isWater();
            default -> throw new IncompatibleClassChangeError();
         };
      } else {
         return false;
      }
   }
}
