package com.moonsworth.lunar.client.ui.hud;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.Module;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.OptionRegistrant;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.Framework11;

public interface MixinCore9Extension extends Module, OptionRegistrant, JsonPersistable {
   @Annotation("metadata")
   GuiIterator method3();

   @Annotation("properties")
   JsonElement method4();

   float getWidth();

   float getHeight();

   void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4);

   boolean method4(boolean var1);

   default boolean method5(boolean var1, Framework7Extension var2) {
      if (!var1) {
         Framework11 var3 = (Framework11)var2.method1(Framework.field19);
         if (var3 != null && !var3.method2()) {
            return false;
         }
      }

      return this.method4(var1);
   }

   float getX();

   float getY();

   default boolean method6() {
      return true;
   }

   void setScale(float var1);

   default float method7() {
      return 0.25F;
   }

   default float method8() {
      return 5.0F;
   }

   FloatOption method9();

   default float method10() {
      return this.getWidth() * this.getScale();
   }

   default float method11() {
      return this.getHeight() * this.getScale();
   }

   default float method12() {
      return this.method33() ? LcuiScreen.getScale() : 1.0F;
   }

   double method13();

   double method14();

   void method15(Data2 var1);

   void method16(float var1, float var2);

   void method17(float var1, float var2);

   void method18();

   default double method19(double var1) {
      return this.method20(this.method26(), var1);
   }

   double method20(HudAnchor var1, double var2);

   default double method21(double var1) {
      return this.method22(this.method26(), var1);
   }

   double method22(HudAnchor var1, double var2);

   default double method23(double var1) {
      return HudAnchor.scalePivotX(this.method26(), this.method19(var1) * this.getScale(), this.getWidth(), this.getScale());
   }

   default double method24(double var1) {
      return HudAnchor.scalePivotY(this.method26(), this.method21(var1) * this.getScale(), this.getHeight(), this.getScale());
   }

   HudAnchor method25();

   HudAnchor method26();

   void method27(HudAnchor var1);

   default float[] method28() {
      ThreadModuleDump71 var1 = LcuiScreen.method151();
      float var2 = (float)this.method19(var1.getScaledWidth());
      float var3 = (float)this.method21(var1.getScaledHeight());
      return new float[]{var2, var3};
   }

   default boolean method29() {
      return false;
   }

   default boolean method30() {
      return true;
   }

   default boolean method31() {
      return true;
   }

   default boolean method32() {
      return false;
   }

   default boolean method33() {
      return false;
   }
}
