package com.moonsworth.lunar.client.framework.feature.saturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper$Extension;
import com.moonsworth.lunar.bridge.MixinHelper$Extension2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump2;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump90;
import javax.annotation.Nullable;
import lombok.Generated;

class Saturation3 {
   private static final int field1 = 10;
   private final com.moonsworth.lunar.client.mod.render.saturation.Saturation field2;
   private final Saturation2 field3;

   @Nullable
   protected MixinHelper$Extension2 method1(ItemStackBridge var1) {
      if (!var1.bridge$hasCustomLore() && ThreadModuleDump63.method7() != null) {
         ThreadModuleDump90 var2 = ThreadModuleDump2.getFoodValues(var1);
         ThreadModuleDump90 var3 = ThreadModuleDump2.getPlayerFoodValues(var1, ThreadModuleDump63.method7());
         int var4 = Math.max(var2.method1(), var3.method1());
         int var5 = (int)Math.ceil(Math.abs(var4) / 2.0F);
         if (var5 == 0) {
            return null;
         }

         float var6 = Math.max(var2.method2(), var3.method2());
         int var7 = (int)Math.ceil(Math.abs(var6) / 2.0F);
         String var8 = null;
         float var9;
         if (var5 > 10) {
            var8 = "x" + (var4 < 0 ? -var5 : var5);
            var9 = 10.0F + ThreadModuleDump63.method10().bridge$getStringWidth(var8);
         } else {
            var9 = var5 * 9;
         }

         String var10 = null;
         float var11;
         if (var7 <= 10 && var7 != 0) {
            var11 = var7 * 9;
         } else {
            var10 = "x" + (var6 < 0.0F ? -var7 : var7);
            var11 = 10.0F + ThreadModuleDump63.method10().bridge$getStringWidth(var10);
         }

         Saturation3.Data var12 = new Saturation3.Data(
            ThreadModuleDump2.givesBadEffect(var1), var5 > 10 ? 1 : var5, var7 > 10 ? 1 : var7, var8, var10, var3.method1(), var2.method1(), var3.method2()
         );
         return Bridge.method8().method90(MixinHelper$Extension.of((var2x, var3x) -> this.method2(var12, var3x)), (int)Math.ceil(Math.max(var9, var11)), 20);
      } else {
         return null;
      }
   }

   private void method2(Saturation3.Data var1, MixinHelper_4 var2) {
      this.method3(var1, var2);
      this.method5(var1, var2);
   }

   private void method3(Saturation3.Data var1, MixinHelper_4 var2) {
      int var3 = (var1.method2() - 1) * 9;
      Saturation.Data var4 = var1.method6() < 0 ? Saturation.method4(var1.method1()) : Saturation.method3(var1.method1());

      for (byte var5 = 0; var5 < var1.method2() * 2; var5 += 2) {
         this.method4(var2, var4, var3, 0, -1);
         Saturation.Data var6 = Saturation.method6(var1.method1(), var1.method7() - 1 == var5);
         if (var6 != null) {
            this.method4(var2, var6, var3, 0, 1090519039);
         }

         if (var1.method6() > var5) {
            boolean var7 = var1.method6() - 1 == var5;
            this.method4(var2, var7 ? Saturation.method2(var1.method1()) : Saturation.method1(var1.method1()), var3, 0, -1);
         }

         var3 -= 9;
      }

      if (var1.method4() != null) {
         this.method7(var2, var3 + 18, 1, var1.method4(), 2);
      }
   }

   private void method4(MixinHelper_4 var1, Saturation.Data var2, int var3, int var4, int var5) {
      var1.method25(var2.method1(), var3, var4, var2.method2(), var2.v(), 9.0F, 9.0F, var2.method3(), var2.method3(), var5);
   }

   private void method5(Saturation3.Data var1, MixinHelper_4 var2) {
      int var3 = (var1.method3() - 1) * 9;
      byte var4 = 10;
      float var5 = Math.abs(var1.method8());

      for (byte var6 = 0; var6 < var1.method3() * 2; var6 += 2) {
         int var7 = var1.method8() < 0.0F ? -5565952 : this.field2.getSaturationColor(var1.field1, var3 + var4);
         this.method6(var2, var1.method1(), var3, var4, (var5 - var6) / 2.0F, var7, var5 <= var6 ? 0.5F : 1.0F);
         var3 -= 9;
      }

      if (var1.method5() != null) {
         this.method7(var2, var3 + 18, var4 + 1, var1.method5(), 1);
      }
   }

   private void method6(MixinHelper_4 var1, boolean var2, int var3, int var4, float var5, int var6, float var7) {
      this.method4(var1, Saturation.method1(var2), var3, var4, ThreadModuleDump23.method18(-14145496, var7));
      this.field3.method1(var1, var2, var3, var4, 1.0F, ThreadModuleDump23.method18(-16119286, var7));
      this.field3.method1(var1, var2, var3, var4, var5, ThreadModuleDump23.method18(var6, var7));
   }

   private void method7(MixinHelper_4 var1, int var2, int var3, String var4, int var5) {
      var1.push();
      var1.method39(var2, var3);
      var1.method40(0.75F, 0.75F);
      var1.method18(ThreadModuleDump63.method10(), var4, 3, var5, -5592406, false);
      var1.pop();
   }

   @Generated
   public Saturation3(com.moonsworth.lunar.client.mod.render.saturation.Saturation var1, Saturation2 var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   private class Data {
      private final boolean field1;
      private final int field2;
      private final int field3;
      @Nullable
      private final String field4;
      @Nullable
      private final String field5;
      private final int field6;
      private final int field7;
      private final float field8;

      private Data(boolean var1, int var2, int var3, @Nullable String var4, @Nullable String var5, int var6, int var7, float var8) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
         this.field8 = var8;
      }

      public boolean method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public int method3() {
         return this.field3;
      }

      @Nullable
      public String method4() {
         return this.field4;
      }

      @Nullable
      public String method5() {
         return this.field5;
      }

      public int method6() {
         return this.field6;
      }

      public int method7() {
         return this.field7;
      }

      public float method8() {
         return this.field8;
      }
   }
}
