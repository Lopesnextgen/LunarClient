package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import lombok.Generated;

@VersionGate(min = 33)
final class InventoryButtonWidget implements MovableHudElement {
   private final HighlightButton field1;
   private final String field2;

   public String id() {
      return this.field2;
   }

   public void method1(float value1, float value) {
      this.field1.setX(value1);
      this.field1.setY(value);
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

   public void setScale(float value1) {
      this.field1.setScale(value1);
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

   public void method9(HudAnchor hudAnchor) {
      this.field1.method26(hudAnchor);
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
      float value1 = this.field1.getScale();
      return new float[]{this.field1.method2() / value1, this.field1.method3() / value1};
   }

   public boolean method16() {
      return !method18();
   }

   public boolean isEnabled() {
      return method19().contains(this.field1);
   }

   public void setEnabled(boolean flag) {
      List list2 = method19();
      if (flag && !list2.contains(this.field1)) {
         list2.add(this.field1);
      } else if (!flag) {
         list2.remove(this.field1);
      }
   }

   static boolean method18() {
      GeneralSettings fogloader220 = Client.method109().method41().method6();
      return Ref.method3().bridge$getGameSettings().bridge$showDebugInfo() && !fogloader220.method24().get()
         ? true
         : Ref.method41() && !(Boolean)fogloader220.method25().get();
   }

   private static List<HighlightButton> method19() {
      return InventoryButtonRegistry.method7();
   }

   @Generated
   public InventoryButtonWidget(HighlightButton highlight51, String text) {
      this.field1 = highlight51;
      this.field2 = text;
   }

   @Generated
   public HighlightButton method20() {
      return this.field1;
   }
}
