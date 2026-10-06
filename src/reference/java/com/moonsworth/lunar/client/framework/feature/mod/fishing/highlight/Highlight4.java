package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Objects;
import java.util.OptionalInt;

@Annotation2(min = 33)
public final class Highlight4 {
   private static final float field1 = 0.6666667F;

   public static boolean method1() {
      return LcuiScreen.method151() != null
         && ThreadModuleDump63.method3().bridge$displayWidth() > 0
         && ThreadModuleDump63.method3().bridge$displayHeight() > 0;
   }

   public static Highlight5 method2(JsonObject var0) {
      JsonObject var1 = var0.deepCopy();
      OptionalInt var2 = ThreadModuleDump9.findInt(var1, "version");
      int var3 = var2.isPresent() ? Math.max(0, var2.getAsInt()) : 0;
      if (var3 > Highlight2.field2) {
         var3 = var1.has("x") && var1.has("y") ? 0 : Highlight2.field2;
      }

      if (var3 < Highlight2.field2) {
         Highlight2.method1(var1, var3);
      }

      HudAnchor var4 = Objects.requireNonNullElse(HudAnchor.fromId(ThreadModuleDump9.getString(var1, "position", "")), HudAnchor.TOP_LEFT);
      Highlight5 var5 = new Highlight5(
         ThreadModuleDump9.getFloat(var1, "offsetX", 0.0F),
         ThreadModuleDump9.getFloat(var1, "offsetY", 0.0F),
         var4,
         ThreadModuleDump9.getFloat(var1, "size", 24.0F),
         ThreadModuleDump9.getFloat(var1, "scale", method5()),
         com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2.valueOf(
            ThreadModuleDump9.getString(var1, "shape", com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin.Gui2Extension2.SQUARE.name())
         ),
         ThreadModuleDump9.getInt(var1, "buttonColor", -1862270977),
         ThreadModuleDump9.getInt(var1, "borderColor", 1076176165),
         ThreadModuleDump9.getString(var1, "command", "/help"),
         ThreadModuleDump9.getString(var1, "hoverText", ""),
         Gui2Extension.valueOf(ThreadModuleDump9.getString(var1, "textSide", Gui2Extension.BELOW.name())),
         ThreadModuleDump9.getString(var1, "itemId", "minecraft:golden_sword"),
         ThreadModuleDump9.getBoolean(var1, "showInContainers", false)
      );
      if (var1.has("x") && var1.has("y")) {
         var5.method9(ThreadModuleDump9.getFloat(var1, "x", 0.0F), ThreadModuleDump9.getFloat(var1, "y", 0.0F));
      }

      return var5;
   }

   public static JsonObject method3(Highlight5 var0) {
      JsonObject var1 = new JsonObject();
      method4(var0, var1);
      var1.addProperty("position", var0.method16().id());
      var1.addProperty("offsetX", var0.getX());
      var1.addProperty("offsetY", var0.getY());
      var1.addProperty("version", Highlight2.field2);
      var1.addProperty("scale", var0.getScale());
      var1.addProperty("size", var0.getSize());
      var1.addProperty("shape", var0.method17().name());
      var1.addProperty("buttonColor", var0.method18());
      var1.addProperty("borderColor", var0.method19());
      var1.addProperty("command", var0.getCommand());
      var1.addProperty("hoverText", var0.method20());
      var1.addProperty("textSide", var0.method21().name());
      var1.addProperty("itemId", var0.getItemId());
      var1.addProperty("showInContainers", var0.method23());
      return var1;
   }

   private static void method4(Highlight5 var0, JsonObject var1) {
      if (method1()) {
         var1.addProperty("x", var0.method2());
         var1.addProperty("y", var0.method3());
      } else if (var0.method8()) {
         var1.addProperty("x", var0.method38());
         var1.addProperty("y", var0.method39());
      }
   }

   static float method5() {
      return 0.6666667F / LcuiScreen.getScale();
   }
}
