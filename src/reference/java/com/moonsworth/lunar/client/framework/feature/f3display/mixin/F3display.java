package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display2;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Consumer;

public abstract class F3display {
   public static final int field1 = 242;
   public static final int field2 = 52;
   private final int[] values;
   private final String label;
   protected int field3 = 0;
   protected int field4 = 0;
   protected String field5;
   private long field6 = 0L;
   private int valueIndex = -1;
   private boolean field7 = false;

   public F3display(int var1, String var2) {
      this.values = new int[var1];
      this.label = var2;
   }

   public void method1() {
      this.valueIndex = -1;
      this.field7 = false;
      this.field4 = 0;
      this.field6 = 0L;
   }

   public void forEach(Consumer<Integer> var1) {
      for (int var2 = this.valueIndex; var2 >= 0; var2--) {
         var1.accept(this.values[var2]);
      }

      if (this.field7) {
         for (int var3 = this.values.length - 1; var3 > this.valueIndex; var3--) {
            var1.accept(this.values[var3]);
         }
      }
   }

   public void method2(int var1) {
      this.valueIndex++;
      if (this.valueIndex >= this.values.length) {
         this.valueIndex = 0;
         this.field7 = true;
      }

      if (this.field7) {
         int var2 = this.values[this.valueIndex];
         this.field6 -= var2;
         if (var2 == this.field4 && var1 < this.field4) {
            this.method4(this.valueIndex);
         }
      }

      this.values[this.valueIndex] = var1;
      this.field6 += var1;
      if (var1 > this.field4) {
         this.field4 = var1;
      }
   }

   protected void method3(int var1, String var2) {
      if (ThreadModuleDump63.MC_VERSION < var1) {
         this.field5 = var2;
      }
   }

   private void method4(int var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < this.values.length; var3++) {
         if (var3 != var1 && this.values[var3] > var2) {
            var2 = this.values[var3];
         }
      }

      this.field4 = var2;
   }

   public int method5() {
      return this.field7 ? this.values.length : this.valueIndex + 1;
   }

   public double method6() {
      int var1 = this.method5();
      return var1 == 0 ? 0.0 : (double)this.field6 / var1;
   }

   public int method7() {
      return this.valueIndex == -1 ? 0 : this.values[this.valueIndex];
   }

   protected int[] method8() {
      return null;
   }

   public int method9() {
      return Math.max(this.field4, this.field3);
   }

   public boolean method10() {
      return this.field5 != null;
   }

   public abstract String method11(int var1);

   protected int method12(int var1, F3DisplayModule var2) {
      return var2.getDisplayOptions().field5.method14(0.0F);
   }

   public boolean method13(F3display2 var1, F3DisplayModule var2, float var3, float var4, boolean var5) {
      if (this.method10() && !var5) {
         return false;
      }

      byte var6 = 50;
      short var7 = 240;
      var3++;
      var4++;
      Data var8 = var2.getDisplayOptions();
      int var9 = var8.field4.method14(0.0F);
      int var10 = var2.getDisplayOptions().field5.method14(0.0F);
      MixinHelper_4 var11 = var1.method13();
      this.method14(var11, (int)var3 - 1, (int)var4 - 1, var7 + 2, var6 + 2, var9);
      int var12 = var7 / this.values.length;
      int var13 = this.method9();
      if (!this.method10()) {
         int[] var14 = new int[this.method5()];
         int var15 = var14.length;

         for (int var16 = this.valueIndex; var16 >= 0; var16--) {
            var14[--var15] = this.values[var16];
         }

         if (this.field7) {
            for (int var34 = this.values.length - 1; var34 > this.valueIndex; var34--) {
               var14[--var15] = this.values[var34];
            }
         }

         int var35 = (int)(var3 + var7 - var14.length * var12);
         var11.method33(var35, var4, var12, var6, var10, var14, var13);
      }

      Bridge10_2 var32 = ThreadModuleDump63.method10();
      boolean var33 = (Boolean)var8.field1.get();
      if (this.label != null && !this.label.isEmpty()) {
         float var36 = var32.bridge$getStringWidth(this.label);
         var11.method22(var32, this.label, (int)(var3 + var7 - var36 - 1.0F), (int)(var4 + 1.0F), var9, var33);
      }

      if (this.method10()) {
         var3 += 121.0F;
         var4 += 26.0F;
         String var38 = "This chart is not available for";
         String var39 = "versions lower than " + this.field5;
         int var40 = var32.method19();
         var4 -= var40 / 2.0F + 1.0F;
         var11.method30(var32, var38, (int)var3, (int)var4, -43691, var33);
         var4 += var40;
         var11.method30(var32, var39, (int)var3, (int)var4, -43691, var33);
         return true;
      }

      String var37 = this.method11(var13);
      var11.method22(var32, var37, (int)var3 + 1, (int)var4 + 1, var9, var33);
      int[] var17 = this.method8();
      if (var17 == null) {
         return true;
      }

      for (int var22 : var17) {
         double var23 = (double)var22 / var13;
         float var18 = (float)(var4 + var6 - var23 * var6);
         var11.method1((int)var3, (int)var18, (int)(var3 + var7), (int)(var18 + 1.0F), var9);
         String var25 = this.method11(var22);
         var11.method22(var32, var25, (int)var3 + 1, (int)var18 + 2, var9, var33);
      }

      return true;
   }

   private void method14(MixinHelper_4 var1, int var2, int var3, int var4, int var5, int var6) {
      var1.method1(var2, var3, var2 + var4, var3 + 1, var6);
      var1.method1(var2, var3, var2 + 1, var3 + var5, var6);
      var1.method1(var2 + var4 - 1, var3, var2 + var4, var3 + var5, var6);
      var1.method1(var2, var3 + var5 - 1, var2 + var4, var3 + var5, var6);
   }
}
