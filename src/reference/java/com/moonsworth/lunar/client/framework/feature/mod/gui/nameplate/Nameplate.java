package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate implements MixinCore9Extension {
   private final MixinCore9Extension field1;

   public Nameplate(MixinCore9Extension var1) {
      this.field1 = var1;
   }

   @Override
   public boolean method4(boolean var1) {
      if (var1 && !Click3.hasIsland() && (Boolean)ThreadModuleDump63.method4().method40().method82().method28().get()) {
         return false;
      } else {
         return !var1 && !Click3.hasIsland() ? false : this.field1.method4(var1);
      }
   }

   @Override
   public boolean method30() {
      return Click3.getIsland() == Gui2Extension3.NONE && ThreadModuleDump63.method4().method40().method82().method28().get() ? false : this.field1.method30();
   }

   public MixinCore9Extension method5() {
      return this.field1;
   }

   public static Nameplate method4(MixinCore9Extension var0) {
      return new Nameplate(var0);
   }

   @Generated
   @Override
   public GuiIterator method3() {
      return this.field1.method3();
   }

   @Generated
   @Override
   public JsonElement method4() {
      return this.field1.method4();
   }

   @Generated
   @Override
   public float getWidth() {
      return this.field1.getWidth();
   }

   @Generated
   @Override
   public float getHeight() {
      return this.field1.getHeight();
   }

   @Generated
   @Override
   public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      this.field1.method3(var1, var2, var3, var4);
   }

   @Generated
   @Override
   public float getX() {
      return this.field1.getX();
   }

   @Generated
   @Override
   public float getY() {
      return this.field1.getY();
   }

   @Generated
   @Override
   public boolean method6() {
      return this.field1.method6();
   }

   @Generated
   @Override
   public void setScale(float var1) {
      this.field1.setScale(var1);
   }

   @Generated
   @Override
   public float method7() {
      return this.field1.method7();
   }

   @Generated
   @Override
   public float method8() {
      return this.field1.method8();
   }

   @Generated
   @Override
   public FloatOption method9() {
      return this.field1.method9();
   }

   @Generated
   @Override
   public float method10() {
      return this.field1.method10();
   }

   @Generated
   @Override
   public float method11() {
      return this.field1.method11();
   }

   @Generated
   @Override
   public float method12() {
      return this.field1.method12();
   }

   @Generated
   @Override
   public double method13() {
      return this.field1.method13();
   }

   @Generated
   @Override
   public double method14() {
      return this.field1.method14();
   }

   @Generated
   @Override
   public void method15(Data2 var1) {
      this.field1.method15(var1);
   }

   @Generated
   @Override
   public void method16(float var1, float var2) {
      this.field1.method16(var1, var2);
   }

   @Generated
   @Override
   public void method17(float var1, float var2) {
      this.field1.method17(var1, var2);
   }

   @Generated
   @Override
   public void method18() {
      this.field1.method18();
   }

   @Generated
   @Override
   public double method19(double var1) {
      return this.field1.method19(var1);
   }

   @Generated
   @Override
   public double method20(HudAnchor var1, double var2) {
      return this.field1.method20(var1, var2);
   }

   @Generated
   @Override
   public double method21(double var1) {
      return this.field1.method21(var1);
   }

   @Generated
   @Override
   public double method22(HudAnchor var1, double var2) {
      return this.field1.method22(var1, var2);
   }

   @Generated
   @Override
   public double method23(double var1) {
      return this.field1.method23(var1);
   }

   @Generated
   @Override
   public double method24(double var1) {
      return this.field1.method24(var1);
   }

   @Generated
   @Override
   public HudAnchor method25() {
      return this.field1.method25();
   }

   @Generated
   @Override
   public HudAnchor method26() {
      return this.field1.method26();
   }

   @Generated
   @Override
   public void method27(HudAnchor var1) {
      this.field1.method27(var1);
   }

   @Generated
   @Override
   public float[] method28() {
      return this.field1.method28();
   }

   @Generated
   @Override
   public boolean method29() {
      return this.field1.method29();
   }

   @Generated
   @Override
   public boolean method31() {
      return this.field1.method31();
   }

   @Generated
   @Override
   public boolean method32() {
      return this.field1.method32();
   }

   @Generated
   @Override
   public boolean method33() {
      return this.field1.method33();
   }

   @Generated
   @Override
   public float method1() {
      return this.field1.method1();
   }

   @Generated
   @Override
   public float method2() {
      return this.field1.method2();
   }

   @Generated
   @Override
   public float getScale() {
      return this.field1.getScale();
   }

   @Generated
   public void method1(RootSettingsAssembler var1) {
      this.field1.method27(var1);
   }

   @Generated
   public void load(JsonObject var1) {
      this.field1.load(var1);
   }

   @Generated
   public void method1(JsonObject var1) {
      this.field1.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1);
   }

   @Generated
   public int priority() {
      return this.field1.priority();
   }

   private interface Extension {
      boolean method1(boolean var1);

      boolean method2();

      boolean method3(boolean var1, Framework7Extension var2);
   }
}
