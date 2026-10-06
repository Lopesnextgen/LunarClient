package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5Impl;

public class ChromaTexture {
   private static ResourceLocationBridge field1;

   public static ResourceLocationBridge method1() {
      if (field1 == null) {
         field1 = ResourceLocationBridge.create("lunar", "lunar_chroma_tex");
         Bridge8Extension34 var0 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(field1, new Alert5Impl());
         var0.bridge$allocate(true, false, 256, 256, TexturePixelFormat.RGBA8, false, false);
         int[] var1 = new int[65536];

         for (int var2 = 255; var2 >= 0; var2--) {
            for (int var3 = 0; var3 < 256; var3++) {
               var1[var2 * 256 + var3] = method6(var3 / 255.0F, var2 / 255.0F);
            }
         }

         var0.bridge$upload(0, 0, 256, 256, var1);
      }

      return field1;
   }

   public static void method2(Bridge2_32 var0, int var1) {
      method3(var0, var1, method4(var1));
   }

   public static void method3(Bridge2_32 var0, int var1, float var2) {
      float var3 = ThreadModuleDump23.method5(var1);
      float var4 = ThreadModuleDump23.greenFloat(var1);
      float var5 = ThreadModuleDump23.method7(var1);
      int var6 = ThreadModuleDump23.method4(var1);
      float var7 = Math.max(var3, Math.max(var4, var5));
      float var8 = Math.min(var3, Math.min(var4, var5));
      float var9 = var7 - var8;
      float var10;
      if (var7 == 0.0F) {
         var10 = 0.0F;
      } else {
         var10 = var9 / var7;
      }

      int var11 = Math.round(var7 * 255.0F);
      int var12 = var6 << 24 | var11 << 16 | var11 << 8 | var11;
      var0.method10(method5(var2), method5(var10)).method9(var12);
   }

   public static float method4(int var0) {
      float var1 = ThreadModuleDump23.method5(var0);
      float var2 = ThreadModuleDump23.greenFloat(var0);
      float var3 = ThreadModuleDump23.method7(var0);
      float var4 = Math.max(var1, Math.max(var2, var3));
      float var5 = Math.min(var1, Math.min(var2, var3));
      float var6 = var4 - var5;
      if (var6 == 0.0F) {
         return 0.0F;
      }

      if (var4 == var1) {
         float var7 = (var2 - var3) / var6;
         if (var7 < 0.0F) {
            var7 += 6.0F;
         }

         return var7 / 6.0F;
      } else {
         return var4 == var2 ? ((var3 - var1) / var6 + 2.0F) / 6.0F : ((var1 - var2) / var6 + 4.0F) / 6.0F;
      }
   }

   private static float method5(float var0) {
      return (Math.round(var0 * 255.0F) + 0.5F) / 256.0F;
   }

   private static int method6(float var0, float var1) {
      int var2 = (int)(var0 * 6.0F);
      float var3 = var0 * 6.0F - var2;
      float var4 = 1.0F - var1;
      float var5 = 1.0F - var3 * var1;
      float var6 = 1.0F - (1.0F - var3) * var1;

      return switch (Math.abs(var2) % 6) {
         case 0 -> method7(1.0F, var6, var4, 1.0F);
         case 1 -> method7(var5, 1.0F, var4, 1.0F);
         case 2 -> method7(var4, 1.0F, var6, 1.0F);
         case 3 -> method7(var4, var5, 1.0F, 1.0F);
         case 4 -> method7(var6, var4, 1.0F, 1.0F);
         case 5 -> method7(1.0F, var4, var5, 1.0F);
         default -> throw new IllegalStateException("Unexpected sector: " + var2 % 6);
      };
   }

   private static int method7(float var0, float var1, float var2, float var3) {
      int var4 = Math.round(var0 * 255.0F);
      int var5 = Math.round(var1 * 255.0F);
      int var6 = Math.round(var2 * 255.0F);
      int var7 = Math.round(var3 * 255.0F);
      return var7 << 24 | var6 << 16 | var5 << 8 | var4;
   }
}
