package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.gui.BugReportCategory;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import org.jetbrains.annotations.Nullable;

public class BugReportBridge implements DriverGuiExtensionLegacy, Calculator2 {
   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("title", this.method1("reportABug", new Object[0]));
      var1.addProperty("shortDescription", this.method1("shortDescriptionChars", new Object[0]));
      var1.addProperty("description", this.method1("description", new Object[0]));
      JsonArray var2 = new JsonArray();

      for (BugReportCategory var6 : BugReportCategory.values()) {
         var2.add(var6.toString());
      }

      var1.add("categories", var2);
      return var1;
   }

   public String getLanguagePath() {
      return "gui.bug";
   }
}
