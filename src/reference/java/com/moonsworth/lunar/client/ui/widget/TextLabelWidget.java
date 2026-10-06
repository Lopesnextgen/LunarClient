package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import java.util.function.Supplier;
import lombok.Generated;

public class TextLabelWidget extends GuiWidget {
   private String text;
   protected final CachedFontImpl field16;
   protected ResourceLocationBridge field17 = null;
   protected ResourceLocationBridge field18 = null;
   private boolean field19 = true;
   protected float field20 = 14.0F;
   private AnimatedValue field21 = new AnimatedValue(1076176165, 1076176165);
   protected AnimatedValue field22 = new AnimatedValue(553648127, 1174405119);
   private int textColor = -4275267;
   protected Supplier<Integer> field23 = null;
   protected boolean active;
   protected boolean field24;
   private boolean field25;
   protected int field26;
   protected float field27 = 0.0F;

   public TextLabelWidget(GuiWidget var1, String var2) {
      this(var1, var2, FontRegistry.field8);
   }

   public TextLabelWidget(GuiWidget var1, String var2, CachedFontImpl var3) {
      this(var1, var2, var3, true);
   }

   public TextLabelWidget(GuiWidget var1, String var2, CachedFontImpl var3, boolean var4) {
      super(var1);
      this.field24 = !var4;
      this.text = var2;
      this.field16 = var3;
      this.field12 = false;
      this.field13 = true;
   }

   public void setText(String var1) {
      this.method1(var1, false);
   }

   public void method1(String var1, boolean var2) {
      this.field24 = var2;
      this.text = var1;
   }

   @Override
   public void update() {
      this.field26++;
   }

   @Override
   public void close() {
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   protected void method4(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      int var4 = this.field21.method2(this.active || var3 && this.method3(var2));
      int var5 = this.field22.method2(this.active || var3 && this.method3(var2));
      LcuiScreen.method53(var1, this.x, this.y, this.width, this.height, 4.0F, var4, 553648127, var5);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.method4(var1, var2, var3);
      this.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1, var2, var3);
      int var4 = this.field22.method2(this.active || var3 && this.method3(var2));
      float var5 = this.field16.getHeight();
      int var6 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, ThreadModuleDump23.method8(var4) * 2.5F);
      if (this.field17 != null) {
         LcuiScreen.method31(
            var1,
            this.field17,
            this.x + this.width / 2.0F - this.field20 / 2.0F,
            this.y + this.height / 2.0F - this.field20 / 2.0F,
            this.field20,
            this.field20,
            var6
         );
      } else {
         String var7 = this.method5();
         int var8 = this.field23 == null ? this.textColor : this.field23.get();
         float var9 = this.field18 == null ? this.x : (this.field19 ? this.x - this.field20 / 2.0F : this.x + this.field20 / 2.0F + 1.0F);
         this.field16
            .method13(
               var1,
               var7 + (this.field25 && this.active && this.field26 / 6 % 2 == 0 ? "_" : ""),
               var9 + this.width / 2.0F + 1.0F - this.field16.method4(var7) / 2.0F,
               this.y + this.height / 2.0F - var5 + 1.0F + this.field27,
               536870912
            );
         var9 = this.field16
            .method13(
               var1,
               var7 + (this.field25 && this.active && this.field26 / 6 % 2 == 0 ? "_" : ""),
               var9 + this.width / 2.0F - this.field16.method4(var7) / 2.0F,
               this.y + this.height / 2.0F - var5 + this.field27,
               var8
            );
         if (this.field18 != null) {
            LcuiScreen.method31(
               var1,
               this.field18,
               this.field19 ? var9 + this.field20 / 2.0F : this.x + this.width / 2.0F - this.field16.method4(var7) / 2.0F - this.field20 / 2.0F,
               this.y + this.height / 2.0F - this.field20 / 2.0F,
               this.field20,
               this.field20,
               var6
            );
         }
      }
   }

   public String method5() {
      return !this.field24
         ? this.method1(this.text, new Object[0]).toUpperCase().replace("", " ").trim()
         : this.method1(this.text, new Object[0]);
   }

   @Generated
   public String getText() {
      return this.text;
   }

   @Generated
   public CachedFontImpl method6() {
      return this.field16;
   }

   @Generated
   public void method7(ResourceLocationBridge var1) {
      this.field17 = var1;
   }

   @Generated
   public void method8(ResourceLocationBridge var1) {
      this.field18 = var1;
   }

   @Generated
   public void method9(boolean var1) {
      this.field19 = var1;
   }

   @Generated
   public void method10(float var1) {
      this.field20 = var1;
   }

   @Generated
   public void method11(AnimatedValue var1) {
      this.field21 = var1;
   }

   @Generated
   public void method12(AnimatedValue var1) {
      this.field22 = var1;
   }

   @Generated
   public void setTextColor(int var1) {
      this.textColor = var1;
   }

   @Generated
   public int getTextColor() {
      return this.textColor;
   }

   @Generated
   public void method15(Supplier<Integer> var1) {
      this.field23 = var1;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public void setActive(boolean var1) {
      this.active = var1;
   }

   @Generated
   public boolean method16() {
      return this.field25;
   }

   @Generated
   public void method17(boolean var1) {
      this.field25 = var1;
   }

   @Generated
   public void method18(float var1) {
      this.field27 = var1;
   }
}
