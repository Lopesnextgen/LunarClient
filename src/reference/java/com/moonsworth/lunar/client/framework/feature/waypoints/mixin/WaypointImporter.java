package com.moonsworth.lunar.client.framework.feature.waypoints.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.files.ValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class WaypointImporter implements JsonProvider {
   private final String field1;
   private final ResourceLocationBridge field2;

   public WaypointImporter(String text1, String text2) {
      this.field1 = text1;
      this.field2 = text2 == null ? null : ResourceLocationBridge.create("lunar", text2);
   }

   public abstract List<ValuePair<String, String>> method2();

   public List<Waypoint> method3() {
      List list1 = this.method2();
      if (list1 != null && !list1.isEmpty()) {
         ArrayList list2 = new ArrayList();

         for (ValuePair files6_24 : list1) {
            try {
               Collection list5 = this.method3((String)files6_24.field1, (String)files6_24.field2);
               if (list5 != null) {
                  list2.addAll(list5);
               }
            } catch (WaypointImporter.Data data6) {
               LunarLogger.method8("Waypoints", "Failed to decode waypoint data from " + (String)files6_24.field1 + ": " + data6.getMessage(), new Object[0]);
            }
         }

         return list2;
      } else {
         return Collections.emptyList();
      }
   }

   public Collection<Waypoint> method3(@Nullable String text1, String text2) {
      if (text2 != null && !text2.trim().isEmpty()) {
         try {
            Collection list3 = this.method4(text1, text2);
            return list3 != null ? list3 : Collections.emptyList();
         } catch (Exception exception4) {
            throw new WaypointImporter.Data("Failed to decode waypoint data: " + exception4.getMessage(), exception4);
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected abstract Collection<Waypoint> method4(@Nullable String text1, String text2);

   protected int parseInt(String text1, int number2) {
      try {
         return text1 != null ? Integer.parseInt(text1.trim()) : number2;
      } catch (NumberFormatException numberformatexception4) {
         return number2;
      }
   }

   protected float method5(String text1, float value2) {
      try {
         return text1 != null ? Float.parseFloat(text1.trim()) : value2;
      } catch (NumberFormatException numberformatexception4) {
         return value2;
      }
   }

   protected boolean parseBoolean(String text1, boolean flag2) {
      if (text1 == null) {
         return flag2;
      }

      String text3 = text1.trim().toLowerCase();

      return switch (text3) {
         case "true", "1", "yes", "enabled" -> true;
         case "false", "0", "no", "disabled" -> false;
         default -> flag2;
      };
   }

   protected int method6(String text1) {
      if (text1 == null) {
         return 0;
      } else {
         String text2 = text1.toLowerCase();
         if (text2.contains("nether") || text2.equals("-1")) {
            return -1;
         } else {
            return !text2.contains("end") && !text2.equals("1") ? 0 : 1;
         }
      }
   }

   protected String method7(String text1, boolean flag2) {
      if (text1 != null && !text1.trim().isEmpty()) {
         String text3 = text1.trim();
         return flag2 ? "mp:" + text3 : "sp:" + text3;
      } else {
         return flag2 ? "mp:unknown" : "sp:unknown";
      }
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("name", this.field1);
      if (this.field2 != null) {
         json1.addProperty("icon", this.field2.bridge$getPath());
      }

      return json1;
   }

   @Generated
   public String getName() {
      return this.field1;
   }

   public static class Data extends IOException {
      public Data() {
      }

      public Data(String text1) {
         super(text1);
      }

      public Data(String text1, Throwable exception2) {
         super(text1, exception2);
      }
   }
}
