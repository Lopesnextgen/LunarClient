package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HudVisibilityWrapper implements MixinCore9Extension {
   private final MixinCore9Extension field1;

   public HudVisibilityWrapper(MixinCore9Extension mixincore9extension1) {
      this.field1 = mixincore9extension1;
   }

   @Override
   public boolean method4(boolean flag1) {
      if (flag1 && !IslandUtils.isOnIsland() && (Boolean)Ref.method4().method40().method82().method28().get()) {
         return false;
      } else {
         return !flag1 && !IslandUtils.isOnIsland() ? false : this.field1.method4(flag1);
      }
   }

   @Override
   public boolean method30() {
      return IslandUtils.getIsland() == SkyblockIsland.NONE && Ref.method4().method40().method82().method28().get() ? false : this.field1.method30();
   }

   public MixinCore9Extension method5() {
      return this.field1;
   }

   public static HudVisibilityWrapper method4(MixinCore9Extension mixincore9extension0) {
      return new HudVisibilityWrapper(mixincore9extension0);
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
   public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
      this.field1.method3(highlightimpl1, value2, value3, flag4);
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
   public void setScale(float value1) {
      this.field1.setScale(value1);
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
   public void method15(Data2 data21) {
      this.field1.method15(data21);
   }

   @Generated
   @Override
   public void method16(float value1, float value2) {
      this.field1.method16(value1, value2);
   }

   @Generated
   @Override
   public void method17(float value1, float value2) {
      this.field1.method17(value1, value2);
   }

   @Generated
   @Override
   public void method18() {
      this.field1.method18();
   }

   @Generated
   @Override
   public double method19(double value1) {
      return this.field1.method19(value1);
   }

   @Generated
   @Override
   public double method20(HudAnchor gui2extension21, double value2) {
      return this.field1.method20(gui2extension21, value2);
   }

   @Generated
   @Override
   public double method21(double value1) {
      return this.field1.method21(value1);
   }

   @Generated
   @Override
   public double method22(HudAnchor gui2extension21, double value2) {
      return this.field1.method22(gui2extension21, value2);
   }

   @Generated
   @Override
   public double method23(double value1) {
      return this.field1.method23(value1);
   }

   @Generated
   @Override
   public double method24(double value1) {
      return this.field1.method24(value1);
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
   public void method27(HudAnchor gui2extension21) {
      this.field1.method27(gui2extension21);
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
   public void method1(RootSettingsBuilder lightingextension231) {
      this.field1.method27(lightingextension231);
   }

   @Generated
   public void load(JsonObject json1) {
      this.field1.load(json1);
   }

   @Generated
   public void method1(JsonObject json1) {
      this.field1.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
   }

   @Generated
   public int priority() {
      return this.field1.priority();
   }

   private interface Extension {
      boolean method1(boolean flag1);

      boolean method2();

      boolean method3(boolean flag1, Framework7Extension framework7extension2);
   }
}
