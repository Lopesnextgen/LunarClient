package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ProgressBarWidget extends GuiWidget {
   private ResourceLocationBridge field16;
   protected boolean active;
   private boolean field17 = true;
   private float field18 = 1.0F;
   private float field19 = -1.0F;
   private float field20 = -1.0F;
   private float field21 = -1.0F;
   private float field22 = -1.0F;
   private boolean field23 = false;
   private AnimatedValue field24 = new AnimatedValue(1358954495, -1342177281);
   private AnimatedValue field25 = new AnimatedValue(1076176165, 1076176165);
   protected AnimatedValue field26 = new AnimatedValue(553648127, 1358954495);

   public ProgressBarWidget(GuiWidget var1, @NotNull ResourceLocationBridge var2) {
      super(var1);
      this.field16 = var2;
   }

   public ProgressBarWidget(
      GuiWidget var1, @Annotation(method1 = Annotation.Type.GUI_COMPONENT) String var2, @NotNull ResourceLocationBridge var3
   ) {
      super(var1);
      this.field10 = var2;
      this.field16 = var3;
      this.field12 = false;
      this.field13 = true;
   }

   @Override
   public void update() {
   }

   @Override
   public void close() {
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   protected void method2(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      int var4 = this.field25.method2(this.active || var3 && this.method3(var2));
      int var5 = this.field26.method2(this.active || var3 && this.method3(var2));
      LcuiScreen.method53(var1, this.x, this.y, this.width, this.height, 4.0F, var4, 553648127, var5);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (this.field17) {
         this.method2(var1, var2, var3);
      }

      this.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1, var2, var3);
      int var4 = this.field24.method2(this.method3(var2) && var3);
      float var5 = 8.0F * this.field18;
      float var6 = this.field19 == -1.0F ? var5 : this.field19;
      float var7 = this.field20 == -1.0F ? var5 : this.field20;
      float var8 = this.field21 == -1.0F ? this.x + this.width / 2.0F : this.field21;
      float var9 = this.field22 == -1.0F ? this.y + this.height / 2.0F : this.field22;
      LcuiScreen.method31(
         var1,
         this.field16,
         LcuiScreen.method135(var8 - var6 / 2.0F),
         LcuiScreen.method135(var9 - var7 / 2.0F),
         var6,
         var7,
         this.field23 ? var4 : -1711276033
      );
   }

   @Generated
   public void method4(ResourceLocationBridge var1) {
      this.field16 = var1;
   }

   @Generated
   public ResourceLocationBridge getResource() {
      return this.field16;
   }

   @Generated
   public void setActive(boolean var1) {
      this.active = var1;
   }

   @Generated
   public void method5(boolean var1) {
      this.field17 = var1;
   }

   @Generated
   public void method6(float var1) {
      this.field18 = var1;
   }

   @Generated
   public void method7(float var1) {
      this.field19 = var1;
   }

   @Generated
   public float method8() {
      return this.field19;
   }

   @Generated
   public void method9(float var1) {
      this.field20 = var1;
   }

   @Generated
   public float method10() {
      return this.field20;
   }

   @Generated
   public void method11(float var1) {
      this.field21 = var1;
   }

   @Generated
   public float method14() {
      return this.field21;
   }

   @Generated
   public void method13(float var1) {
      this.field22 = var1;
   }

   @Generated
   public float method15() {
      return this.field22;
   }

   @Generated
   public void method15(boolean var1) {
      this.field23 = var1;
   }

   @Generated
   public void method16(AnimatedValue var1) {
      this.field24 = var1;
   }

   @Generated
   public void method17(AnimatedValue var1) {
      this.field25 = var1;
   }

   @Generated
   public void method18(AnimatedValue var1) {
      this.field26 = var1;
   }
}
