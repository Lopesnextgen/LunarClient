package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightTextSide;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Objects;
import java.util.OptionalInt;

@VersionGate(min = 33)
public final class HighlightSerializer {
   private static final float field1 = 0.6666667F;

   public HighlightSerializer() {
   }

   public static boolean method1() {
      return LcuiScreen.method151() != null
         && Ref.method3().bridge$displayWidth() > 0
         && Ref.method3().bridge$displayHeight() > 0;
   }

   public static HighlightButton method2(JsonObject json0) {
      JsonObject json1 = json0.deepCopy();
      OptionalInt optionalint2 = ThreadModuleDump9.findInt(json1, "version");
      int number3 = optionalint2.isPresent() ? Math.max(0, optionalint2.getAsInt()) : 0;
      if (number3 > HighlightConfigMigrations.field2) {
         number3 = json1.has("x") && json1.has("y") ? 0 : HighlightConfigMigrations.field2;
      }

      if (number3 < HighlightConfigMigrations.field2) {
         HighlightConfigMigrations.method1(json1, number3);
      }

      HudAnchor gui2extension24 = Objects.requireNonNullElse(HudAnchor.fromId(ThreadModuleDump9.getString(json1, "position", "")), HudAnchor.TOP_LEFT);
      HighlightButton highlight55 = new HighlightButton(
         ThreadModuleDump9.getFloat(json1, "offsetX", 0.0F),
         ThreadModuleDump9.getFloat(json1, "offsetY", 0.0F),
         gui2extension24,
         ThreadModuleDump9.getFloat(json1, "size", 24.0F),
         ThreadModuleDump9.getFloat(json1, "scale", method5()),
         com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape.valueOf(
            ThreadModuleDump9.getString(json1, "shape", com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.HighlightButtonShape.SQUARE.name())
         ),
         ThreadModuleDump9.getInt(json1, "buttonColor", -1862270977),
         ThreadModuleDump9.getInt(json1, "borderColor", 1076176165),
         ThreadModuleDump9.getString(json1, "command", "/help"),
         ThreadModuleDump9.getString(json1, "hoverText", ""),
         HighlightTextSide.valueOf(ThreadModuleDump9.getString(json1, "textSide", HighlightTextSide.BELOW.name())),
         ThreadModuleDump9.getString(json1, "itemId", "minecraft:golden_sword"),
         ThreadModuleDump9.getBoolean(json1, "showInContainers", false)
      );
      if (json1.has("x") && json1.has("y")) {
         highlight55.method9(ThreadModuleDump9.getFloat(json1, "x", 0.0F), ThreadModuleDump9.getFloat(json1, "y", 0.0F));
      }

      return highlight55;
   }

   public static JsonObject method3(HighlightButton highlight50) {
      JsonObject json1 = new JsonObject();
      method4(highlight50, json1);
      json1.addProperty("position", highlight50.method16().id());
      json1.addProperty("offsetX", highlight50.getX());
      json1.addProperty("offsetY", highlight50.getY());
      json1.addProperty("version", HighlightConfigMigrations.field2);
      json1.addProperty("scale", highlight50.getScale());
      json1.addProperty("size", highlight50.getSize());
      json1.addProperty("shape", highlight50.method17().name());
      json1.addProperty("buttonColor", highlight50.method18());
      json1.addProperty("borderColor", highlight50.method19());
      json1.addProperty("command", highlight50.getCommand());
      json1.addProperty("hoverText", highlight50.method20());
      json1.addProperty("textSide", highlight50.method21().name());
      json1.addProperty("itemId", highlight50.getItemId());
      json1.addProperty("showInContainers", highlight50.method23());
      return json1;
   }

   private static void method4(HighlightButton highlight50, JsonObject json1) {
      if (method1()) {
         json1.addProperty("x", highlight50.method2());
         json1.addProperty("y", highlight50.method3());
      } else if (highlight50.method8()) {
         json1.addProperty("x", highlight50.method38());
         json1.addProperty("y", highlight50.method39());
      }
   }

   static float method5() {
      return 0.6666667F / LcuiScreen.getScale();
   }
}
