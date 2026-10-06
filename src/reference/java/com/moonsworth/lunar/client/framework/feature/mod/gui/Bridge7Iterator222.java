package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import lombok.Generated;

@Annotation2(min = 33)
final class Bridge7Iterator222 implements MovableHudElement {
   private final Highlight5 field1;
   private final String field2;

   public String id() {
      return this.field2;
   }

   public void method1(float var1, float var2) {
      this.field1.setX(var1);
      this.field1.setY(var2);
   }

   public float method2() {
      return this.field1.getX();
   }

   public float method3() {
      return this.field1.getY();
   }

   public float method4() {
      return this.field1.method2();
   }

   public float method5() {
      return this.field1.method3();
   }

   public float getScale() {
      return this.field1.getScale();
   }

   public void setScale(float var1) {
      this.field1.setScale(var1);
   }

   public float method6() {
      return 0.5F;
   }

   public float method7() {
      return 5.0F;
   }

   public HudAnchor method8() {
      return this.field1.method16();
   }

   public void method9(HudAnchor var1) {
      this.field1.method26(var1);
   }

   public float getWidth() {
      return this.field1.getSize();
   }

   public float getHeight() {
      return this.field1.getSize();
   }

   public boolean method14() {
      return true;
   }

   public float[] method15() {
      float var1 = this.field1.getScale();
      return new float[]{this.field1.method2() / var1, this.field1.method3() / var1};
   }

   public boolean method16() {
      return !method18();
   }

   public boolean isEnabled() {
      return method19().contains(this.field1);
   }

   public void setEnabled(boolean var1) {
      List var2 = method19();
      if (var1 && !var2.contains(this.field1)) {
         var2.add(this.field1);
      } else if (!var1) {
         var2.remove(this.field1);
      }
   }

   static boolean method18() {
      GeneralSettings var0 = Client.method109().method41().method6();
      return ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() && !var0.method24().get()
         ? true
         : ThreadModuleDump63.method41() && !(Boolean)var0.method25().get();
   }

   private static List<Highlight5> method19() {
      return Bridge7Iterator2.method7();
   }

   @Generated
   public Bridge7Iterator222(Highlight5 var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   public Highlight5 method20() {
      return this.field1;
   }
}
