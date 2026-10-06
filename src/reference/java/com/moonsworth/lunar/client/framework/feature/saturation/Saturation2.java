package com.moonsworth.lunar.client.framework.feature.saturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.BitSet;
import org.jspecify.annotations.Nullable;

class Saturation2 {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "saturation_outline");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "saturation_outline_hunger");
   private static final int field3 = 4;
   private Saturation2.@Nullable Data2 field4;
   private Saturation2.@Nullable Data2 field5;

   public void reset() {
      if (this.field4 != null) {
         this.field4.delete();
         this.field4 = null;
      }

      if (this.field5 != null) {
         this.field5.delete();
         this.field5 = null;
      }
   }

   public void method1(MixinHelper_4 var1, boolean var2, float var3, float var4, float var5, int var6) {
      if (!(var5 <= 0.0F)) {
         Saturation2.Data2 var7 = this.method2(var2);
         if (!var7.method4()) {
            int var8 = var7.method5();
            float var9 = 9.0F / var8;
            var1.push();
            var1.method39(var3, var4);
            var1.method40(var9, var9);
            var1.method25(var7.method3(), 0.0F, 0.0F, Saturation2.Data2.method2(var5) * var8, 0.0F, var8, var8, var7.method1(), var8, var6);
            var1.pop();
         }
      }
   }

   private Saturation2.Data2 method2(boolean var1) {
      if (var1) {
         if (this.field5 == null) {
            this.field5 = this.method3(true);
         }

         return this.field5;
      } else {
         if (this.field4 == null) {
            this.field4 = this.method3(false);
         }

         return this.field4;
      }
   }

   private Saturation2.Data2 method3(boolean var1) {
      ResourceLocationBridge var2 = var1 ? field2 : field1;
      Saturation2.Data var3 = this.method8(var1);
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3.method3(); var5++) {
         for (int var6 = 0; var6 < var3.method3(); var6++) {
            if (this.method5(var3, var6, var5)) {
               var4.add(new Saturation2.Data3(var6, var5));
            }
         }
      }

      if (var4.isEmpty()) {
         return new Saturation2.Data2(var2, true, var3.method3(), var3.method5());
      }

      int var11 = var3.method3();
      BufferedImage var12 = new BufferedImage(var11 * 4, var11, 2);

      for (Saturation2.Data3 var8 : var4) {
         int var9 = this.method6(var8, var11);

         for (int var10 = this.method7(var8, var11); var10 < 4; var10++) {
            var12.setRGB(var10 * var11 + var8.x(), var8.y(), var9);
         }
      }

      Bridge8Extension33 var13 = Bridge.method8().method22(var12);
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var2, var13);
      return new Saturation2.Data2(var2, false, var3.method3(), var3.method5());
   }

   public int method4(boolean var1) {
      return this.method2(var1).method6();
   }

   private boolean method5(Saturation2.Data var1, int var2, int var3) {
      if (!var1.method1(var2, var3)) {
         return false;
      }

      int var4 = var1.method4();

      for (int var5 = -var4; var5 <= var4; var5++) {
         for (int var6 = -var4; var6 <= var4; var6++) {
            if (var6 * var6 + var5 * var5 <= var4 * var4 && !var1.method1(var2 + var6, var3 + var5)) {
               return true;
            }
         }
      }

      return false;
   }

   private int method6(Saturation2.Data3 var1, int var2) {
      float var3 = var1.y() + 0.25F * (var1.x() - var2 / 2.0F);
      int var4 = Math.round(255.0F - 115.0F * ClampUtils.clamp(var3 / var2, 0.0F, 1.0F));
      return ThreadModuleDump23.method10(var4, var4, var4, 255);
   }

   private int method7(Saturation2.Data3 var1, int var2) {
      float var3 = (var2 - var1.x() - 0.25F * (var1.y() - var2 / 2.0F)) / var2;
      return ClampUtils.clamp((int)Math.ceil(var3 * 4.0F) - 1, 0, 3);
   }

   private Saturation2.Data method8(boolean var1) {
      Saturation.Data var2 = Saturation.method1(var1);
      BufferedImage var3 = ThreadModuleDump59.method4(var2.method1());
      Saturation.Data var4 = Saturation.method3(var1);
      BufferedImage var5 = ThreadModuleDump59.method4(var4.method1());
      int var6 = Math.max(this.method9(var3, var2), this.method9(var5, var4));
      BitSet var7 = new BitSet(var6 * var6);
      int var8 = this.method10(var7, var5, var4, var6);
      int var9 = this.method10(var7, var3, var2, var6);
      int var10 = (int)Math.ceil(var6 / 9.0F);
      int var11 = ThreadModuleDump23.method35(var8, var9, 0.5F);
      return new Saturation2.Data(var7, var6, var10, var11);
   }

   private int method9(@Nullable BufferedImage var1, Saturation.Data var2) {
      if (var1 == null) {
         return 9;
      }

      float var3 = 9.0F * (var1.getWidth() / var2.method3());
      return ClampUtils.clamp(Math.round(var3), 9, 36);
   }

   private int method10(BitSet var1, BufferedImage var2, Saturation.Data var3, int var4) {
      if (var2 == null) {
         return -1;
      }

      float var5 = var2.getWidth() / var3.method3();
      float var6 = 9.0F * var5;
      float var7 = var3.method2() * var5;
      float var8 = var3.v() * var5;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;

      for (int var13 = 0; var13 < var4; var13++) {
         for (int var14 = 0; var14 < var4; var14++) {
            int var15 = (int)(var7 + (var14 + 0.5F) * var6 / var4);
            int var16 = (int)(var8 + (var13 + 0.5F) * var6 / var4);
            if (var15 < var2.getWidth() && var16 < var2.getHeight()) {
               int var17 = var2.getRGB(var15, var16);
               if (ThreadModuleDump23.method4(var17) >= 128) {
                  var1.set(var14 + var13 * var4);
                  var9 += ThreadModuleDump23.method1(var17);
                  var10 += ThreadModuleDump23.method2(var17);
                  var11 += ThreadModuleDump23.method3(var17);
                  var12++;
               }
            }
         }
      }

      return var12 == 0 ? -1 : ThreadModuleDump23.method10(255 - var9 / var12, 255 - var10 / var12, 255 - var11 / var12, 255);
   }

   private class Data {
      private final BitSet field1;
      private final int field2;
      private final int field3;
      private final int field4;

      private Data(BitSet var1, int var2, int var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      private boolean method1(int var1, int var2) {
         return var1 >= 0 && var2 >= 0 && var1 < this.field2 && var2 < this.field2 && this.field1.get(var1 + var2 * this.field2);
      }

      public BitSet method2() {
         return this.field1;
      }

      public int method3() {
         return this.field2;
      }

      public int method4() {
         return this.field3;
      }

      public int method5() {
         return this.field4;
      }
   }

   private class Data2 {
      private final ResourceLocationBridge field1;
      private final boolean field2;
      private final int resolution;
      private final int field3;

      private Data2(ResourceLocationBridge var1, boolean var2, int var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.resolution = var3;
         this.field3 = var4;
      }

      private int method1() {
         return this.resolution * 4;
      }

      private void delete() {
         if (!this.field2) {
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field1);
         }
      }

      private static int method2(float var0) {
         return var0 >= 1.0F ? 3 : ClampUtils.clamp((int)Math.ceil(var0 * 4.0F) - 1, 0, 2);
      }

      public ResourceLocationBridge method3() {
         return this.field1;
      }

      public boolean method4() {
         return this.field2;
      }

      public int method5() {
         return this.resolution;
      }

      public int method6() {
         return this.field3;
      }
   }

   private class Data3 {
      private final int field1;
      private final int field2;

      private Data3(int var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public int x() {
         return this.field1;
      }

      public int y() {
         return this.field2;
      }
   }
}
