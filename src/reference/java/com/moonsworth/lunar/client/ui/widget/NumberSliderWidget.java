package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.ui.AnimationTimer;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class NumberSliderWidget<T extends Number & Comparable<T>> extends GuiWidget {
   private final AnimationTimer field16;
   private double field17;
   private T field18;
   private int field19 = 0;
   private int field20 = 0;
   private boolean field21;
   private final ClientOption<T> field22;
   private final NumberRule<T> field23;

   public NumberSliderWidget(ClientOption<T> var1, GuiWidget var2) {
      super(var2);
      this.field16 = new ColorAnimation(500L);
      this.field22 = var1;
      this.field23 = (NumberRule<T>)var1.method1(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      this.field17 = ((Number)var1.get()).doubleValue();
      this.field18 = (T)var1.get();
      this.method4((var1x, var2x) -> {
         this.field21 = true;
         return true;
      });
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1 + 4.0F, var2, var3 - 8.0F, var4);
   }

   @Override
   public float getHeight() {
      return 14.0F;
   }

   @Override
   public void update() {
      this.field19 = this.field20;
      this.field20 = this.field20 + (this.field21 ? 40 : -40);
      if (this.field20 < 0) {
         this.field20 = 0;
      }

      if (this.field20 > 255) {
         this.field20 = 255;
      }
   }

   private void method2() {
      float var1 = this.field16.method9();
      float var2 = var1;
      if (this.field16.method6()) {
         this.field16.start();
         this.field17 = this.field18.doubleValue();
         this.field18 = (T)this.field22.get();
      } else {
         if (var1 > 0.5F) {
            this.field16.method3(250L);
            var1 = 0.5F;
         }

         double var3 = this.field17 + (this.field18.doubleValue() - this.field17) * var2;
         this.field17 = (var3 - ((Number)this.field22.get()).doubleValue() * var1) / (1.0F - var1);
         this.field18 = (T)this.field22.get();
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = this.method15();
      float var5 = this.method14();
      if (!var4) {
         this.field21 = false;
      } else {
         float var6 = this.field23.getMin().floatValue();
         float var7 = this.field23.getMax().floatValue();
         float var8 = var2.method12() - this.x - var5;
         float var9 = var8 / this.method10();
         double var10 = 0.01;
         float var12;
         if (var9 <= 0.01) {
            var12 = var6;
         } else if (var9 >= 0.99) {
            var12 = var7;
         } else {
            var12 = var6 + var9 * (var7 - var6);
            if (Bridge.method18().method1(KeyCode.KEY_LSHIFT) || Bridge.method18().method1(KeyCode.KEY_RSHIFT)) {
               double var13 = Math.log10(var7 - var6) + 0.001;
               if (var13 < 0.0) {
                  var13 = 0.0;
               }

               var10 = Math.pow(10.0, (int)var13) / 10.0;
            }
         }

         this.field22.method10(this.field23.method5(Math.round(var12 / var10) * var10));
      }

      double var18 = Math.abs(((Number)this.field22.get()).doubleValue() - this.field18.doubleValue());
      if (var18 > 0.01F) {
         this.method2();
      }

      float var19 = this.field23.getMin().floatValue();
      float var20 = this.field23.getMax().floatValue();
      Number var21 = (Number)this.field22.get();
      float var11 = var21.floatValue();
      float var22 = (var11 - var19) / (var20 - var19);
      float var23 = this.method10() * var22;
      if (this.field16.isActive()) {
         float var14 = (float)(this.field17 - var19) / (var20 - var19);
         float var15 = this.method10() * var22;
         float var16 = this.method10() * var14;
         float var17 = var16 - var15;
         var23 = var16 - var17 * this.field16.method9();
      }

      float var24 = this.height / 4.0F;
      LcuiScreen.method94(var1, this.x + var5, this.y + this.height / 2.0F - var24 / 2.0F, this.method10(), var24, -15066341);
      int var25 = (int)(this.field19 + (this.field20 - this.field19) * var1.method43());
      if (var25 > 50) {
         this.method4(var1, var25, var19, var20);
      }

      this.method8(var1, var23);
   }

   private void method4(MixinHelper_4 var1, int var2, float var3, float var4) {
      int var5 = 1;
      int var6 = 0;
      if (Math.round((var4 - var3) * 100.0F) % 100 == 0) {
         if (this.field23.method3() >= 2) {
            var5 = Math.round(var4 - var3);
            var6 = this.field23.method3();
         } else if (this.field23.method6()) {
            var5 = Math.round(var4 - var3);
         }
      }

      if (var5 > 20) {
         var5 = 1;
      }

      float var7 = this.method14();

      for (int var8 = 0; var8 <= var5; var8++) {
         float var9 = this.method10() / var5;
         float var10 = var7 + var8 * var9;
         this.method6(var1, var10, var2);

         for (int var11 = 1; var11 < var6; var11++) {
            this.method7(var1, var10 + var9 * var11 / var6, var2);
         }
      }
   }

   private void method5(MixinHelper_4 var1, float var2, float var3, int var4) {
      if (!(var3 <= 0.0F)) {
         LcuiScreen.method94(var1, this.x + var2, this.y + this.height / 2.0F - var3 / 2.0F, 1.0F, var3, var4 << 24 | 3684416);
      }
   }

   private void method6(MixinHelper_4 var1, float var2, int var3) {
      this.method5(var1, var2, this.height / 4.0F + 2.0F, var3);
   }

   private void method7(MixinHelper_4 var1, float var2, int var3) {
      this.method5(var1, var2, this.height / 4.0F, var3);
   }

   private void method8(MixinHelper_4 var1, float var2) {
      float var3 = this.height / 3.0F;
      float var4 = this.method14();
      LcuiScreen.method78(var1, this.x + var2 + var4, this.y + this.height / 2.0F, var3, -11561732);
      LcuiScreen.method63(var1, this.x + var2 + var4, this.y + this.height / 2.0F, var3, var3 - 1.0F, 1.0, 1, 1.0, 620756991);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   private float method10() {
      return this.width - this.height / 3.0F * 2.0F;
   }

   private float method14() {
      return (this.width - this.method10()) / 2.0F;
   }

   public boolean method15() {
      return Bridge.method20().method1(0) && this.field21;
   }
}
