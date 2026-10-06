package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class DropdownWidget extends GuiWidget {
   private static final float field16 = 16.0F;
   protected double field17;
   protected float field18;
   protected float field19;
   protected boolean field20;
   protected boolean field21;
   private boolean field22;
   private final boolean field23;
   private boolean field24;

   public DropdownWidget(GuiWidget var1) {
      this(var1, false);
   }

   public DropdownWidget(GuiWidget var1, boolean var2) {
      super(var1);
      this.field23 = var2;
      this.method4((var1x, var2x) -> {
         if (this.method1(var1x)) {
            this.field22 = true;
            return true;
         } else {
            return false;
         }
      });
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + this.width
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.y
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.height;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
   }

   public float method3() {
      return this.field18;
   }

   public MarkerModel.Data2 method4(MarkerModel.Data2 var1) {
      return (MarkerModel.Data2)var1.IHHIOOICIOOICRICCIRCOHCHRIROIC(this.field18);
   }

   public void method5(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.field24 = this.field23 || this.field4 != null && this.field4.method1(var2);
      if (this.field4 != null && !this.field4.method1(var2) && this.field17 != 0.0) {
         this.field17 = 0.0;
      }

      double var4 = Math.round(this.field17 / 5.0);
      this.field17 -= var4;
      if (this.field17 != 0.0) {
         this.field18 = (float)(this.field18 + var4);
      }

      if (this.field21) {
         if (this.field18 < -this.field19 + this.height) {
            this.field18 = -this.field19 + this.height;
            this.field17 = 0.0;
         }

         if (this.field18 > 0.0F) {
            this.field18 = 0.0F;
            this.field17 = 0.0;
         }
      }

      LcuiScreen.method108(this.field18);
      var1.push();
      var1.method38(0.0F, this.field18, 0.0F);
   }

   public boolean method6() {
      return this.field19 > this.height;
   }

   public void method7(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.field21 = true;
      var1.pop();
      LcuiScreen.method108(-this.field18);
      boolean var4 = this.method6();
      if (this.field20 && (!Bridge.method20().method1(0) || !this.method1(var2) || !var3)) {
         this.field20 = false;
      }

      if (this.field22 && !Bridge.method20().method1(0)) {
         this.field22 = false;
      }

      float var5 = this.method8();
      float var6 = this.height - var5;
      if (Bridge.method20().method1(0) && this.field22 && var4) {
         float var7 = var6 <= 0.0F ? 0.0F : this.method9((var2.method12() - this.y - var5 / 2.0F) / var6);
         this.field18 = -var7 * (this.field19 - this.height);
      }

      if (var4) {
         if (this.field18 < -this.field19 + this.height) {
            this.field18 = -this.field19 + this.height;
            this.field17 = 0.0;
         }

         if (this.field18 > 0.0F) {
            this.field18 = 0.0F;
            this.field17 = 0.0;
         }

         float var10 = -this.field18 / (this.field19 - this.height);
         float var8 = this.y + var10 * var6;
         boolean var9 = var2.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x
            && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + this.width
            && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > var8
            && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < var8 + var5;
         LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, 540884285);
         LcuiScreen.method94(
            var1, this.x, var8, this.width, var5, !var3 || !var9 && (!Bridge.method20().method1(0) || !this.field22) ? 551740130 : 1625481954
         );
      }

      if (!var4 && this.field18 != 0.0F) {
         this.field18 = 0.0F;
      }
   }

   private float method8() {
      if (this.field19 <= 0.0F) {
         return this.height;
      }

      float var1 = this.height * this.height / this.field19;
      float var2 = Math.min(16.0F, this.height / 2.0F);
      return Math.max(Math.min(var1, this.height), var2);
   }

   private float method9(float var1) {
      return Math.max(0.0F, Math.min(1.0F, var1));
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public boolean method5(int var1) {
      if (super.method5(var1)) {
         return true;
      } else if (var1 != 0 && this.field24 && this.field19 >= this.height) {
         this.field17 += var1 / 3.0F;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void close() {
   }

   @Generated
   public void method12(double var1) {
      this.field17 = var1;
   }

   @Generated
   public double method14() {
      return this.field17;
   }

   @Generated
   public void method14(float var1) {
      this.field18 = var1;
   }

   @Generated
   public void method15(float var1) {
      this.field19 = var1;
   }

   @Generated
   public boolean method16() {
      return this.field20;
   }

   @Generated
   public boolean method17() {
      return this.field22;
   }
}
