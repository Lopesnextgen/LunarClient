package com.moonsworth.lunar.client.framework.feature.waypoints.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.files.Files6_2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class Gui2Loader implements JsonProviderLegacy {
   private final String field1;
   private final ResourceLocationBridge field2;

   public Gui2Loader(String var1, String var2) {
      this.field1 = var1;
      this.field2 = var2 == null ? null : ResourceLocationBridge.create("lunar", var2);
   }

   public abstract List<Files6_2<String, String>> method2();

   public List<GuiHandler2> method3() {
      List var1 = this.method2();
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Files6_2 var4 : var1) {
            try {
               Collection var5 = this.method3((String)var4.field1, (String)var4.field2);
               if (var5 != null) {
                  var2.addAll(var5);
               }
            } catch (Gui2Loader.Data var6) {
               Slayer.method8("Waypoints", "Failed to decode waypoint data from " + (String)var4.field1 + ": " + var6.getMessage(), new Object[0]);
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public Collection<GuiHandler2> method3(@Nullable String var1, String var2) {
      if (var2 != null && !var2.trim().isEmpty()) {
         try {
            Collection var3 = this.method4(var1, var2);
            return var3 != null ? var3 : Collections.emptyList();
         } catch (Exception var4) {
            throw new Gui2Loader.Data("Failed to decode waypoint data: " + var4.getMessage(), var4);
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected abstract Collection<GuiHandler2> method4(@Nullable String var1, String var2);

   protected int parseInt(String var1, int var2) {
      try {
         return var1 != null ? Integer.parseInt(var1.trim()) : var2;
      } catch (NumberFormatException var4) {
         return var2;
      }
   }

   protected float method5(String var1, float var2) {
      try {
         return var1 != null ? Float.parseFloat(var1.trim()) : var2;
      } catch (NumberFormatException var4) {
         return var2;
      }
   }

   protected boolean parseBoolean(String var1, boolean var2) {
      if (var1 == null) {
         return var2;
      }

      String var3 = var1.trim().toLowerCase();

      return switch (var3) {
         case "true", "1", "yes", "enabled" -> true;
         case "false", "0", "no", "disabled" -> false;
         default -> var2;
      };
   }

   protected int method6(String var1) {
      if (var1 == null) {
         return 0;
      } else {
         String var2 = var1.toLowerCase();
         if (var2.contains("nether") || var2.equals("-1")) {
            return -1;
         } else {
            return !var2.contains("end") && !var2.equals("1") ? 0 : 1;
         }
      }
   }

   protected String method7(String var1, boolean var2) {
      if (var1 != null && !var1.trim().isEmpty()) {
         String var3 = var1.trim();
         return var2 ? "mp:" + var3 : "sp:" + var3;
      } else {
         return var2 ? "mp:unknown" : "sp:unknown";
      }
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("name", this.field1);
      if (this.field2 != null) {
         var1.addProperty("icon", this.field2.bridge$getPath());
      }

      return var1;
   }

   @Generated
   public String getName() {
      return this.field1;
   }

   public static class Data extends IOException {
      public Data() {
      }

      public Data(String var1) {
         super(var1);
      }

      public Data(String var1, Throwable var2) {
         super(var1, var2);
      }
   }
}
