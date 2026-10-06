package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.ui.hud.HudPlacement;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public abstract class GuiWidget implements Calculator2 {
   protected float x;
   protected float y;
   protected float width;
   protected float height;
   protected float field1 = 0.0F;
   protected float field2 = 0.0F;
   private boolean field3 = false;
   protected final GuiWidget field4;
   protected ThreadModuleDump51 field5;
   protected ThreadModuleDump51 field6;
   protected ThreadModuleDump51 field7;
   protected BooleanSupplier field8 = () -> true;
   protected ThreadModuleDump51 field9 = this::method16;
   protected String field10 = null;
   protected Object[] field11 = null;
   protected boolean field12 = true;
   protected boolean field13 = false;
   protected final Bridge5_12 field14 = ThreadModuleDump63.method3();
   protected final Client field15 = Client.method109();

   public GuiWidget(GuiWidget var1) {
      this.field4 = var1;
   }

   public boolean method1(MarkerModel.Data2 var1) {
      float var2 = this.x + this.field1;
      float var3 = this.y + this.field2;
      return this.method13()
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() > var2
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < var2 + this.width
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > var3
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < var3 + this.height;
   }

   public void method2(float var1, float var2, float var3, float var4) {
      this.x = LcuiScreen.method135(var1);
      this.y = LcuiScreen.method135(var2);
      this.width = var3;
      this.height = var4;
      this.field3 = true;
   }

   public void setX(float var1) {
      this.x = LcuiScreen.method135(var1);
   }

   public void setY(float var1) {
      this.y = LcuiScreen.method135(var1);
   }

   public abstract void update();

   public abstract void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3);

   public abstract void method4(char var1, KeyCode var2);

   public boolean method5(int var1) {
      return false;
   }

   public abstract void close();

   public boolean method6(MarkerModel.Data2 var1, int var2) {
      return this.field5 != null && this.field8.getAsBoolean() && this.field5.accept(var1, var2);
   }

   public boolean method7(MarkerModel.Data2 var1, int var2) {
      return this.field7 != null && this.field8.getAsBoolean() && this.field7.accept(var1, var2);
   }

   public boolean method8(MarkerModel.Data2 var1, int var2) {
      return this.field6 != null && this.field8.getAsBoolean() && this.field6.accept(var1, var2);
   }

   public void method9(MixinHelper_4 var1) {
      LcuiScreen.method70(var1, this);
   }

   public void method10(MixinHelper_4 var1, int var2) {
      LcuiScreen.method71(var1, this, var2);
   }

   public void method11() {
   }

   public Optional<GuiWidget> method12() {
      return Optional.ofNullable(this.field4);
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   public boolean method13() {
      return this.field8.getAsBoolean();
   }

   public void method14(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (this.field10 != null && this.method1(var2) && var3) {
         var1.push();
         var1.method38(0.0F, 0.0F, 10.0F);
         boolean var4 = LcuiScreen.method107();
         int[] var5 = null;
         if (var4) {
            var5 = LcuiScreen.method112(var1);
         }

         boolean var6 = this.field9.accept(var2, 0);
         String var7 = this.method1(this.field10, this.field11);
         if (this.field13) {
            var7 = var7.toUpperCase();
         }

         float var8 = FontRegistry.method9().method4(var7) + 8.0F;
         float var9 = !var6 ? this.y + this.height + 8.0F : this.y - 8.0F - FontRegistry.method12().getHeight() * 2;
         float var10 = this.x + this.width / 2.0F - var8 / 2.0F;
         if (this.field12) {
            var10 = (float)var2.HHHCHORHIHRCOHIOICICICHCRRICCI() + 8.0F;
            var9 = (float)var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - 2.0F;
         }

         this.method15(var1, var7, var10, var9, var8, FontRegistry.method8().getHeight() * 2 + 5);
         if (var4) {
            LcuiScreen.method110(var1, var5);
         }

         var1.pop();
      }
   }

   protected void method15(MixinHelper_4 var1, String var2, float var3, float var4, float var5, float var6) {
      LcuiScreen.method117(var1, var3, var4, var5, var6, 5.0F, Integer.MIN_VALUE);
      FontRegistry.method9().method18(new ModernRenderStateScope(var1), var2, var3 + 4.0F, var4 + 2.0F, Integer.MAX_VALUE, false);
   }

   private boolean method16(MarkerModel.Data2 var1, int var2) {
      HudAnchor var3 = HudAnchor.getMousePosition(var1);
      return var3.getVertical() != HudPlacement.TOP;
   }

   public void method17(String var1, Object... var2) {
      this.field10 = var1;
      this.field11 = var2;
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }

   @Generated
   public void setWidth(float var1) {
      this.width = var1;
   }

   @Generated
   public void setHeight(float var1) {
      this.height = var1;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public void method20(float var1) {
      this.field1 = var1;
   }

   @Generated
   public void method21(float var1) {
      this.field2 = var1;
   }

   @Generated
   public float method22() {
      return this.field1;
   }

   @Generated
   public float method23() {
      return this.field2;
   }

   @Generated
   public boolean method24() {
      return this.field3;
   }

   @Generated
   public void method25(ThreadModuleDump51 var1) {
      this.field5 = var1;
   }

   @Generated
   public void method26(ThreadModuleDump51 var1) {
      this.field6 = var1;
   }

   @Generated
   public void method27(ThreadModuleDump51 var1) {
      this.field7 = var1;
   }

   @Generated
   public BooleanSupplier method28() {
      return this.field8;
   }

   @Generated
   public void method29(BooleanSupplier var1) {
      this.field8 = var1;
   }

   @Generated
   public void method30(ThreadModuleDump51 var1) {
      this.field9 = var1;
   }

   @Generated
   public String method31() {
      return this.field10;
   }

   @Generated
   public Object[] method32() {
      return this.field11;
   }

   @Generated
   public boolean method33() {
      return this.field12;
   }

   @Generated
   public void method34(boolean var1) {
      this.field12 = var1;
   }

   @Generated
   public void method35(boolean var1) {
      this.field13 = var1;
   }
}
