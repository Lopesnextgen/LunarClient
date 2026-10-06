package com.moonsworth.lunar.client.framework.feature.armorstatus.armorstatusbarschild;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Armorstatusbarschild2 {
   private static final int field1 = 9;
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/armor_full.png");
   private static final ResourceLocationBridge field3 = ResourceLocationBridge.create("minecraft", "textures/gui/sprites/hud/armor_empty.png");
   public static final ResourceLocationBridge field4 = ResourceLocationBridge.create("minecraft", "textures/gui/icons.png");
   private static final int field5 = 256;
   private static final int field6 = 34;
   private static final int field7 = 9;
   private static final int field8 = 16;
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create(
      "minecraft", ThreadModuleDump63.MC_VERSION >= 16 ? "textures/misc/enchanted_glint_item.png" : "textures/misc/enchanted_item_glint.png"
   );
   private static final int field10 = -7309112;
   private static final int field11 = 96;
   private static final Armorstatusbarschild2.Data field12 = new Armorstatusbarschild2.Data(0, 4, new boolean[81]);
   @Nullable
   private Armorstatusbarschild2.Data field13 = null;

   public void reset() {
      this.field13 = null;
   }

   @NotNull
   private Armorstatusbarschild2.Data method1() {
      if (this.field13 == null) {
         this.field13 = this.method8();
      }

      return this.field13;
   }

   public void method2(MixinHelper_4 var1, int var2, int var3, Armorstatusbarschild2.Type var4, int var5) {
      if (var5 != 0) {
         Armorstatusbarschild2.Data var6 = this.method1();
         int var7 = var5 >> 24 & 0xFF;
         if (var6.method3() == 0) {
            var5 = var7 << 24 | 16777215;
         } else {
            int var8 = var5 >> 16 & 0xFF;
            int var9 = var5 >> 8 & 0xFF;
            int var10 = var5 & 0xFF;
            int var11 = Math.max(var8, Math.max(var9, var10));
            float var12 = Math.min(255.0F / var6.method3(), 255.0F / Math.max(1, var11));
            var5 = var7 << 24 | (int)(var8 * var12) << 16 | (int)(var9 * var12) << 8 | (int)(var10 * var12);
         }

         this.method4(var1, var2, var3, var4, var5, false);
      }
   }

   public void method3(MixinHelper_4 var1, int var2, int var3, Armorstatusbarschild2.Type var4) {
      this.method4(var1, var2, var3, var4, -1, true);
   }

   private void method4(MixinHelper_4 var1, int var2, int var3, Armorstatusbarschild2.Type var4, int var5, boolean var6) {
      Armorstatusbarschild2.Data var7 = this.method1();
      int var8 = var7.method1(var4);
      int var9 = var7.method2(var4) - var8;
      if (ThreadModuleDump63.MC_VERSION >= 19) {
         var1.method25(var6 ? field3 : field2, var8 + var2, var3, var8, 0.0F, var9, 9.0F, 9.0F, 9.0F, var5);
      } else {
         var1.method25(field4, var8 + var2, var3, (var6 ? 16 : 34) + var8, 9.0F, var9, 9.0F, 256.0F, 256.0F, var5);
      }
   }

   public void method5(MixinHelper_4 var1, int var2, int var3, Armorstatusbarschild2.Type var4, float var5) {
      Armorstatusbarschild2.Data var6 = this.method1();
      int var7 = var6.method1(var4);
      int var8 = var6.method2(var4);
      int var9 = 0xFF000000 | this.method6(var5);
      long var10 = ThreadModuleDump63.method3().bridge$getSystemTime();
      float var12 = (float)Math.floorMod(var10, 13750L) / 13750.0F * 96.0F;
      float var13 = (float)Math.floorMod(var10, 3750L) / 3750.0F * 96.0F;

      for (int var14 = 0; var14 < 9; var14++) {
         int var15 = -1;

         for (int var16 = var7; var16 <= var8; var16++) {
            boolean var17 = var16 < var8 && var6.field3[var16 + var14 * 9];
            if (var17 && var15 < 0) {
               var15 = var16;
            } else if (!var17 && var15 >= 0) {
               this.method7(var1, var2 + var15, var3 + var14, var16 - var15, var15, var14, var9, var12, var13);
               var15 = -1;
            }
         }
      }
   }

   private int method6(float var1) {
      if (var1 >= 1.0F) {
         return 9468104;
      }

      var1 = (float)Math.sqrt(Math.max(0.0F, var1));
      return (int)(144.0F * var1) << 16 | (int)(120.0F * var1) << 8 | (int)(200.0F * var1);
   }

   private void method7(MixinHelper_4 var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8, float var9) {
      var1.method26(field9, var2, var3, var5 + var8, var6 + 96 - var9, var4, 1.0F, 96.0F, 96.0F, var7);
      var1.method26(field9, var2, var3, var5 + 96 - var8, var6 + var9, var4, 1.0F, 96.0F, 96.0F, var7);
   }

   @NotNull
   private Armorstatusbarschild2.Data method8() {
      boolean var1 = ThreadModuleDump63.MC_VERSION >= 19;
      BufferedImage var2 = ThreadModuleDump59.method4(var1 ? field2 : field4);
      if (var2 == null) {
         return field12;
      }

      int var3 = Math.max(1, var2.getWidth() / (var1 ? 9 : 256));
      int var4 = var1 ? 0 : 34 * var3;
      int var5 = var1 ? 0 : 9 * var3;
      int var6 = this.method9(var2, var4, var5, 9 * var3);
      if (var6 == 0) {
         return field12;
      }

      boolean[] var7 = new boolean[81];

      for (int var8 = 0; var8 < 9; var8++) {
         for (int var9 = 0; var9 < 9; var9++) {
            int var10 = var4 + var8 * var3;
            int var11 = var5 + var9 * var3;
            var7[var8 + var9 * 9] = var10 < var2.getWidth() && var11 < var2.getHeight() && (var2.getRGB(var10, var11) >> 24 & 0xFF) >= 128;
         }
      }

      return new Armorstatusbarschild2.Data(var6, this.method10(var7), var7);
   }

   private int method9(BufferedImage var1, int var2, int var3, int var4) {
      int var5 = Math.min(var2 + var4, var1.getWidth());
      int var6 = Math.min(var3 + var4, var1.getHeight());
      int[] var7 = new int[Math.max(0, var5 - var2) * Math.max(0, var6 - var3)];
      int var8 = 0;

      for (int var9 = var2; var9 < var5; var9++) {
         for (int var10 = var3; var10 < var6; var10++) {
            int var11 = var1.getRGB(var9, var10);
            if ((var11 >> 24 & 0xFF) >= 128) {
               int var12 = var11 >> 16 & 0xFF;
               int var13 = var11 >> 8 & 0xFF;
               int var14 = var11 & 0xFF;
               var7[var8++] = Math.max(var12, Math.max(var13, var14));
            }
         }
      }

      if (var8 == 0) {
         return 0;
      }

      Arrays.sort(var7, 0, var8);
      int var15 = var7[Math.round((var8 - 1) * 0.8F)];
      return Math.max(1, var15);
   }

   private int method10(boolean[] var1) {
      int var2 = 9;
      int var3 = -1;

      for (int var4 = 0; var4 < 9; var4++) {
         for (int var5 = 0; var5 < 9; var5++) {
            if (var1[var4 + var5 * 9]) {
               var2 = Math.min(var2, var4);
               var3 = Math.max(var3, var4);
            }
         }
      }

      return var2 <= var3 ? (var2 + var3 + 1) / 2 : 4;
   }

   private class Data {
      private final int field1;
      private final int field2;
      private final boolean[] field3;

      private Data(int var1, int var2, boolean[] var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      private int method1(Armorstatusbarschild2.Type var1) {
         return var1 == Armorstatusbarschild2.Type.RIGHT ? this.method4() : 0;
      }

      private int method2(Armorstatusbarschild2.Type var1) {
         return var1 == Armorstatusbarschild2.Type.LEFT ? this.method4() : 9;
      }

      public int method3() {
         return this.field1;
      }

      public int method4() {
         return this.field2;
      }

      public boolean[] method5() {
         return this.field3;
      }
   }

   public enum Type {
      LEFT,
      RIGHT,
      WHOLE;
   }
}
