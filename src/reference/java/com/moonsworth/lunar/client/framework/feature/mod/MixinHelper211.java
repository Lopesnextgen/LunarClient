package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper211 extends MixinHelper2_3 {
   private static final ThreadModuleDump70 field2 = new ThreadModuleDump70(-5000, -5000, 0, 0);
   private boolean scissorApplied = false;

   public MixinHelper211(MixinHelper var1) {
      super(var1);
   }

   public void method1(int var1, int var2, int var3, int var4) {
      if (this.method1().field4 == null) {
         this.method2(var1, var2, var3, var4);
      } else {
         ThreadModuleDump70 var5 = this.method1().field4;
         this.method1().field4 = var5.intersection(ThreadModuleDump70.of(var1, var2, var3, var4));
         if (this.method1().field4 == null) {
            this.method1().field4 = field2;
         }

         this.apply();
      }
   }

   public void method2(int var1, int var2, int var3, int var4) {
      this.method1().field4 = ThreadModuleDump70.of(var1, var2, var3, var4);
      this.apply();
   }

   private void apply() {
      this.method6().method17();
      if (this.scissorApplied) {
         this.method16().method44(var0 -> LcuiScreen.method114(var0.method29()));
         this.method16().method45(var0 -> var0.method28());
         this.scissorApplied = false;
      }

      if (this.method2().method18() <= 0 && this.method1().field4 != null) {
         ThreadModuleDump70 var1 = this.method1().field4;
         this.method16()
            .method44(
               var2 -> LcuiScreen.method113(
                  var2.method29(),
                  (int)(var1.getLeft() * this.method1().field9),
                  (int)(var1.getTop() * this.method1().field10),
                  (int)(var1.getRight() * this.method1().field9),
                  (int)(var1.getBottom() * this.method1().field10),
                  LcuiScreen.method151().method3(),
                  LcuiScreen.method151().getScaledHeight()
               )
            );
         this.method16().method45(var1x -> var1x.method27(var1.getLeft(), var1.getTop(), var1.getRight(), var1.getBottom()));
         this.scissorApplied = true;
      }
   }

   public void update() {
      this.apply();
   }

   public boolean method4(int var1, int var2) {
      return this.method1().field4 == null ? true : this.method1().field4.method9(var1, var2);
   }

   public boolean method5(int var1, int var2, int var3, int var4) {
      if (!this.method1().field8) {
         return false;
      }

      int var5 = 0;
      int var6 = 0;
      int var7 = (int)(LcuiScreen.method151().getScaledWidth() / this.method1().field9);
      int var8 = (int)(LcuiScreen.method151().getScaledHeight() / this.method1().field10);
      if (this.method1().field4 != null) {
         var5 = this.method1().field4.method10();
         var6 = this.method1().field4.method11();
         var7 = this.method1().field4.method1();
         var8 = this.method1().field4.method2();
      }

      return var1 + var3 < var5 || var2 + var4 < var6 || var1 > var5 + var7 || var2 > var6 + var8;
   }

   public ThreadModuleDump70 method6(ThreadModuleDump70 var1) {
      return this.method1().field4 == null ? var1 : var1.intersection(this.method1().field4);
   }
}
