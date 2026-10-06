package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.hud.HudEditorState;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

@Annotation2(min = 33)
public final class Bridge7Iterator22 implements HudEditorState {
   private final com.moonsworth.lunar.client.ui.LcuiScreen field1;
   private final Bridge7Iterator2 field2;

   public void method1(String var1) {
   }

   public void method2(String var1) {
      ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public boolean method3(String var1) {
      Highlight5 var2 = this.field2.method3(var1).orElse(null);
      if (var2 == null) {
         return false;
      }

      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator(this.field1, var2)));
      return true;
   }

   public void method4(String var1) {
      this.field2.method3(var1).ifPresent(var0 -> {
         Bridge7Iterator2.method7().remove(var0);
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      });
   }

   @Generated
   public Bridge7Iterator22(com.moonsworth.lunar.client.ui.LcuiScreen var1, Bridge7Iterator2 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
