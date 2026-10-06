package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.hud.HudEditorState;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

@VersionGate(min = 33)
public final class ScreenButtonGroup implements HudEditorState {
   private final com.moonsworth.lunar.client.ui.LcuiScreen field1;
   private final InventoryButtonRegistry field2;

   public void method1(String text1) {
   }

   public void method2(String text1) {
      Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public boolean method3(String text1) {
      HighlightButton highlight52 = this.field2.method3(text1).orElse(null);
      if (highlight52 == null) {
         return false;
      }

      Ref.method3().bridge$displayScreen(Bridge.method8().method18(new InventoryButtonsEditor(this.field1, highlight52)));
      return true;
   }

   public void method4(String text1) {
      this.field2.method3(text1).ifPresent(arg0 -> {
         InventoryButtonRegistry.method7().remove(arg0);
         Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   @Generated
   public ScreenButtonGroup(com.moonsworth.lunar.client.ui.LcuiScreen bridge7iterator1, InventoryButtonRegistry bridge7iterator22) {
      this.field1 = bridge7iterator1;
      this.field2 = bridge7iterator22;
   }
}
