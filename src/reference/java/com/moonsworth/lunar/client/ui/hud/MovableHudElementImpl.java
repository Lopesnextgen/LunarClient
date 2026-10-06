package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

final class MovableHudElementImpl implements MovableHudElement {
   private final Framework7Extension field1;

   private MixinCore9Extension method1() {
      return (MixinCore9Extension)this.field1.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);
   }

   @Override
   public String id() {
      return this.field1.getId();
   }

   @Override
   public void method1(float var1, float var2) {
      this.method1().method17(var1, var2);
   }

   @Override
   public float method2() {
      return this.method1().getX();
   }

   @Override
   public float method3() {
      return this.method1().getY();
   }

   @Override
   public float method4() {
      MixinCore9Extension var1 = this.method1();
      return (float)(var1.method19(MarkerModel.method1().method5().HHHCHORHIHRCOHIOICICICHCRRICCI()) * var1.getScale());
   }

   @Override
   public float method5() {
      MixinCore9Extension var1 = this.method1();
      return (float)(var1.method21(MarkerModel.method1().method5().IHRCCHHROHIRCOOOHRRIHOORRHIOHO()) * var1.getScale());
   }

   @Override
   public float method10() {
      return (float)this.method1().method23(MarkerModel.method1().method5().HHHCHORHIHRCOHIOICICICHCRRICCI());
   }

   @Override
   public float method11() {
      return (float)this.method1().method24(MarkerModel.method1().method5().IHRCCHHROHIRCOOOHRRIHOORRHIOHO());
   }

   @Override
   public float getScale() {
      return this.method1().getScale();
   }

   @Override
   public void setScale(float var1) {
      MixinCore9Extension var2 = this.method1();
      var2.setScale(var1 * var2.method12());
   }

   @Override
   public float method6() {
      MixinCore9Extension var1 = this.method1();
      return var1.method7() / var1.method12();
   }

   @Override
   public float method7() {
      MixinCore9Extension var1 = this.method1();
      return var1.method8() / var1.method12();
   }

   @Override
   public HudAnchor method8() {
      return this.method1().method26();
   }

   @Override
   public void method9(HudAnchor var1) {
      this.method1().method27(var1);
   }

   @Override
   public float getWidth() {
      return this.method1().getWidth();
   }

   @Override
   public float getHeight() {
      return this.method1().getHeight();
   }

   @Override
   public boolean method14() {
      return this.method1().method29();
   }

   @Override
   public float[] method15() {
      return this.method1().method28();
   }

   @Override
   public boolean method17() {
      return this.method1().method6();
   }

   @Override
   public boolean method16() {
      GeneralSettings var1 = Client.method109().method41().method6();
      return ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() && !var1.method24().get()
         ? this.field1 instanceof F3DisplayModule
         : !ThreadModuleDump63.method41() || (Boolean)var1.method25().get();
   }

   @Override
   public boolean isEnabled() {
      ModEnabledState var1 = (ModEnabledState)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
      return var1 == null || var1.isEnabled();
   }

   @Override
   public void setEnabled(boolean var1) {
      ModEnabledState var2 = (ModEnabledState)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
      if (var2 != null) {
         var2.setEnabled(var1);
      }
   }

   @Generated
   public MovableHudElementImpl(Framework7Extension var1) {
      this.field1 = var1;
   }
}
