package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
final class HighlightHandler implements HighlightMigrationStep {
   HighlightHandler() {
   }

   @Override
   public void method1(HighlightMigrationContext highlight31) {
      JsonObject json2 = highlight31.method1();
      float value3 = ThreadModuleDump9.getFloat(json2, "x", 2.0F);
      float value4 = ThreadModuleDump9.getFloat(json2, "y", 2.0F);
      float value5 = ThreadModuleDump9.getFloat(json2, "size", 24.0F);
      float value6 = ThreadModuleDump9.getFloat(json2, "scale", HighlightSerializer.method5());
      HudAnchor gui2extension27 = HudAnchor.getMousePosition(
         new Data2(value3 + value5 * value6 / 2.0F, value4 + value5 * value6 / 2.0F), new Data2(highlight31.method2(), highlight31.method3())
      );
      json2.addProperty("position", gui2extension27.id());
      json2.addProperty("offsetX", value3 - (float)(HudAnchor.anchorOriginX(gui2extension27, highlight31.method2() / value6, value5) * value6));
      json2.addProperty("offsetY", value4 - (float)(HudAnchor.anchorOriginY(gui2extension27, highlight31.method3() / value6, value5) * value6));
   }
}
