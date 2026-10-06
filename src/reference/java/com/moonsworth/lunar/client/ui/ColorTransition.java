package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;

public class ColorTransition extends Animation {
   private final ColorOption field10;
   private final ColorOption field11;
   private boolean state;

   public ColorTransition(long var1, ColorOption var3, ColorOption var4) {
      super(var1);
      this.field10 = var3;
      this.field11 = var4;
   }

   public int method1(boolean var1) {
      return this.method2(var1, 0.0F);
   }

   public int method2(boolean var1, float value) {
      this.method3(var1);
      if (this.method7()) {
         float var3 = super.method1();
         ColorOption var4 = this.state ? this.field10 : this.field11;
         ColorOption var5 = this.state ? this.field11 : this.field10;
         int var6 = var4.method1(value);
         int var7 = var5.method1(value);
         int var8 = (int)Math.abs(var3 * ThreadModuleDump23.method1(var7) + (1.0F - var3) * ThreadModuleDump23.method1(var6));
         int var9 = (int)Math.abs(var3 * ThreadModuleDump23.method2(var7) + (1.0F - var3) * ThreadModuleDump23.method2(var6));
         int var10 = (int)Math.abs(var3 * ThreadModuleDump23.method3(var7) + (1.0F - var3) * ThreadModuleDump23.method3(var6));
         int var11 = (int)Math.abs(var3 * ThreadModuleDump23.method4(var7) + (1.0F - var3) * ThreadModuleDump23.method4(var6));
         return ThreadModuleDump23.method10(var8, var9, var10, var11);
      } else {
         return var1 ? this.field11.method14(value) : this.field10.method14(value);
      }
   }

   private void method3(boolean var1) {
      if (var1 && !this.state) {
         this.state = true;
         this.start();
      } else if (this.state && !var1) {
         this.state = false;
         this.start();
      }
   }
}
