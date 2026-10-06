package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Minimap4 {
   private int[] colors = new int[256];
   private boolean built;

   public boolean update(com.moonsworth.lunar.client.framework.feature.minimap.Minimap var1, @Nullable Itemcounter2 var2, long var3, boolean var5) {
      if (var2 == null) {
         return false;
      }

      int[] var6 = new int[this.colors.length];

      for (int var7 = 0; var7 < 16; var7++) {
         Itemcounter2 var8;
         Minimap4 var9;
         if (var7 == 0) {
            int var10 = Minimap2.method5(var3);
            int var11 = Minimap2.method6(var3) - 1;
            Itemcounter6Extension var12 = ThreadModuleDump63.method8();
            var8 = var12 == null ? var2 : var12.bridge$getChunk(var10, var11);
            var9 = (Minimap4)var1.method22().getIfPresent(Minimap2.method2(var10, var11));
         } else {
            var8 = var2;
            var9 = this;
         }

         for (int var18 = 0; var18 < 16; var18++) {
            Minimap3 var19 = this.getTerrainColumn(var2, var5, var18, var7);
            Minimap3 var20;
            if (var9 == null) {
               var20 = var19;
            } else {
               var20 = var9.getTerrainColumn(var8, var5, var18, var7 - 1 & 15);
            }

            int var13 = var2.bridge$getBlockState(var18, var19.method1(), var7).bridge$getMapColor(var2, var18, var19.method1(), var7);
            short var14;
            if (var19.method1() == var20.method1()) {
               var14 = 220;
            } else if (var19.method1() > var20.method1()) {
               var14 = 255;
            } else {
               var14 = 180;
            }

            var6[var7 * 16 + var18] = ThreadModuleDump23.method33(var13, var14);
            if (var19.getFluidState() != null) {
               int var15 = var19.getFluidState().bridge$getMapColor(var2, var18, var19.method1() + 1, var7);
               float var16 = Math.min(1.0F, var19.getDepth() / 30.0F);
               var15 = ThreadModuleDump23.method33(var15, (int)(255.0F * (0.6F + 0.4F * (1.0F - var16))));
               if (var19.getFluidState().bridge$getBlock().bridge$isWater()) {
                  var6[var7 * 16 + var18] = ThreadModuleDump23.method35(var15, var6[var7 * 16 + var18], Math.min(1.0F, 0.5F + 0.4F * var16));
               } else {
                  var6[var7 * 16 + var18] = var15;
               }
            }
         }
      }

      this.built = true;
      boolean var17 = !Arrays.equals(this.colors, var6);
      if (var17) {
         this.setColors(var6);
      }

      return var17;
   }

   public Minimap3 getTerrainColumn(Itemcounter2 var1, boolean var2, int var3, int var4) {
      if (var1 == null) {
         return new Minimap3(0, 0, null);
      }

      int var7 = var1.bridge$getWorld().bridge$getMinBuildHeight();
      int var5;
      if (var2) {
         var5 = this.computeSurfaceHeight(var1, var3, var4);
      } else {
         var5 = Math.min(var1.bridge$getHeightmapHeight(var3 & 15, var4 & 15), var1.bridge$getWorld().bridge$getMaxBuildHeight());
      }

      int var6 = var5;
      int var9 = 0;
      Bridge2_17 var10 = null;

      boolean var8;
      do {
         Bridge2_17 var11 = var1.bridge$getBlockState(var3 & 15, var6, var4 & 15);
         int var12 = var11.bridge$getMapColor(var1, var3 & 15, var6, var4 & 15);
         if (var12 == 0 || var12 == -16777216) {
            var8 = true;
         } else if (var11.bridge$isFluid()) {
            var8 = true;
            var9++;
            if (var10 == null) {
               var10 = var11;
            }
         } else {
            var8 = var11.bridge$getBlock().bridge$isExcludedFromMinimap();
         }
      } while (var8 && var6-- >= var7);

      return new Minimap3(var6, var9, var10);
   }

   private int computeSurfaceHeight(Itemcounter2 var1, int var2, int var3) {
      int var4 = var1.bridge$getWorld().bridge$getMinBuildHeight();
      int var5 = var1.bridge$getWorld().bridge$getMaxBuildHeight();
      boolean var6 = this.isAir(var1, var2, var5, var3);
      boolean var7 = !var6;

      for (int var8 = var5 - 1; var8 >= var4; var8--) {
         boolean var9 = this.isAir(var1, var2, var8, var3);
         if (!var9 && var6 && var7) {
            return var8;
         }

         var6 = var9;
         if (!var7 && !var9) {
            var7 = true;
         }
      }

      return var4;
   }

   private boolean isAir(Itemcounter2 var1, int var2, int var3, int var4) {
      return var1.bridge$getBlockState(var2 & 15, var3, var4 & 15).bridge$getBlock().bridge$isAir();
   }

   @Generated
   public int[] getColors() {
      return this.colors;
   }

   @Generated
   public void setColors(int[] var1) {
      this.colors = var1;
   }

   @Generated
   public boolean isBuilt() {
      return this.built;
   }

   @Generated
   public void setBuilt(boolean var1) {
      this.built = var1;
   }
}
