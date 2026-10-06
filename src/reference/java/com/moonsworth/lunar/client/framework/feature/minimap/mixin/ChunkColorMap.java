package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ChunkColorMap {
   private int[] field1 = new int[256];
   private boolean built;

   public boolean method1(com.moonsworth.lunar.client.framework.feature.minimap.MinimapMap minimap1, @Nullable ChunkBridge itemcounter22, long number3, boolean flag5) {
      if (itemcounter22 == null) {
         return false;
      }

      int[] items6 = new int[this.field1.length];

      for (int index7 = 0; index7 < 16; index7++) {
         ChunkBridge itemcounter28;
         ChunkColorMap minimap49;
         if (index7 == 0) {
            int number10 = MapCoord.method5(number3);
            int number11 = MapCoord.method6(number3) - 1;
            WorldBridgeExtension itemcounter6extension12 = Ref.method8();
            itemcounter28 = itemcounter6extension12 == null ? itemcounter22 : itemcounter6extension12.bridge$getChunk(number10, number11);
            minimap49 = (ChunkColorMap)minimap1.method22().getIfPresent(MapCoord.method2(number10, number11));
         } else {
            itemcounter28 = itemcounter22;
            minimap49 = this;
         }

         for (int index18 = 0; index18 < 16; index18++) {
            TerrainColumn minimap319 = this.method2(itemcounter22, flag5, index18, index7);
            TerrainColumn minimap320;
            if (minimap49 == null) {
               minimap320 = minimap319;
            } else {
               minimap320 = minimap49.method2(itemcounter28, flag5, index18, index7 - 1 & 15);
            }

            int number13 = itemcounter22.bridge$getBlockState(index18, minimap319.method1(), index7).bridge$getMapColor(itemcounter22, index18, minimap319.method1(), index7);
            short number14;
            if (minimap319.method1() == minimap320.method1()) {
               number14 = 220;
            } else if (minimap319.method1() > minimap320.method1()) {
               number14 = 255;
            } else {
               number14 = 180;
            }

            items6[index7 * 16 + index18] = ColorUtils.method33(number13, number14);
            if (minimap319.getFluidState() != null) {
               int number15 = minimap319.getFluidState().bridge$getMapColor(itemcounter22, index18, minimap319.method1() + 1, index7);
               float value16 = Math.min(1.0F, minimap319.getDepth() / 30.0F);
               number15 = ColorUtils.method33(number15, (int)(255.0F * (0.6F + 0.4F * (1.0F - value16))));
               if (minimap319.getFluidState().bridge$getBlock().bridge$isWater()) {
                  items6[index7 * 16 + index18] = ColorUtils.method35(number15, items6[index7 * 16 + index18], Math.min(1.0F, 0.5F + 0.4F * value16));
               } else {
                  items6[index7 * 16 + index18] = number15;
               }
            }
         }
      }

      this.built = true;
      boolean flag17 = !Arrays.equals(this.field1, items6);
      if (flag17) {
         this.method6(items6);
      }

      return flag17;
   }

   public TerrainColumn method2(ChunkBridge itemcounter21, boolean flag2, int number3, int number4) {
      if (itemcounter21 == null) {
         return new TerrainColumn(0, 0, null);
      }

      int number7 = itemcounter21.bridge$getWorld().bridge$getMinBuildHeight();
      int number5;
      if (flag2) {
         number5 = this.method3(itemcounter21, number3, number4);
      } else {
         number5 = Math.min(itemcounter21.bridge$getHeightmapHeight(number3 & 15, number4 & 15), itemcounter21.bridge$getWorld().bridge$getMaxBuildHeight());
      }

      int index6 = number5;
      int index9 = 0;
      BlockStateBridge bridge2_1710 = null;

      boolean flag8;
      do {
         BlockStateBridge bridge2_1711 = itemcounter21.bridge$getBlockState(number3 & 15, index6, number4 & 15);
         int number12 = bridge2_1711.bridge$getMapColor(itemcounter21, number3 & 15, index6, number4 & 15);
         if (number12 == 0 || number12 == -16777216) {
            flag8 = true;
         } else if (bridge2_1711.bridge$isFluid()) {
            flag8 = true;
            index9++;
            if (bridge2_1710 == null) {
               bridge2_1710 = bridge2_1711;
            }
         } else {
            flag8 = bridge2_1711.bridge$getBlock().bridge$isExcludedFromMinimap();
         }
      } while (flag8 && index6-- >= number7);

      return new TerrainColumn(index6, index9, bridge2_1710);
   }

   private int method3(ChunkBridge itemcounter21, int number2, int number3) {
      int number4 = itemcounter21.bridge$getWorld().bridge$getMinBuildHeight();
      int number5 = itemcounter21.bridge$getWorld().bridge$getMaxBuildHeight();
      boolean flag6 = this.method4(itemcounter21, number2, number5, number3);
      boolean flag7 = !flag6;

      for (int index8 = number5 - 1; index8 >= number4; index8--) {
         boolean flag9 = this.method4(itemcounter21, number2, index8, number3);
         if (!flag9 && flag6 && flag7) {
            return index8;
         }

         flag6 = flag9;
         if (!flag7 && !flag9) {
            flag7 = true;
         }
      }

      return number4;
   }

   private boolean method4(ChunkBridge itemcounter21, int number2, int number3, int number4) {
      return itemcounter21.bridge$getBlockState(number2 & 15, number3, number4 & 15).bridge$getBlock().bridge$isAir();
   }

   @Generated
   public ChunkColorMap() {
   }

   @Generated
   public int[] method5() {
      return this.field1;
   }

   @Generated
   public void method6(int[] items1) {
      this.field1 = items1;
   }

   @Generated
   public boolean isBuilt() {
      return this.built;
   }

   @Generated
   public void setBuilt(boolean flag1) {
      this.built = flag1;
   }
}
