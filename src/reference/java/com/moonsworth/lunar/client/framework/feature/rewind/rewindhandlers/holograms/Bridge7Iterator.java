package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextAreaWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class Bridge7Iterator extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TextAreaWidget field19;

   public Bridge7Iterator() {
      this.field19.HORHROIOIOICIRHIOCOICHHHIHCIIO((var0, var1) -> {
         ThreadModuleDump63.method4().method40().method85().method16();
         DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field12);
         return true;
      });
   }

   protected List<GuiWidget> method25() {
      return List.of(this.field19 = new TextAreaWidget(null, "cancel"));
   }

   public void init() {
      this.field19
         .method2(
            this.method22() / 2.0F - 100.0F, this.method23() / 2.0F + 10.0F, 200.0F, 20.0F
         );
   }

   public void update() {
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         var1, 0.0F, 0.0F, this.method22(), this.method23(), -15526886
      );
      FontRegistry.field13
         .method14(var1, "Loading Rewind...", this.method22() / 2.0F, this.method23() / 2.0F - 20.0F, -218498305);
   }

   public void method11(Data2 var1, int var2) {
   }

   public void method12(Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public void close() {
   }
}
