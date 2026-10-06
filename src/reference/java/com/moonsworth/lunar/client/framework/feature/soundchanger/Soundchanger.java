package com.moonsworth.lunar.client.framework.feature.soundchanger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;

public class Soundchanger {
   private static Soundchanger field1;
   private static Soundchanger field2;
   private static ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "sound_mod/sound_mappings.json");
   private Map<ResourceLocationBridge, String> field4 = new HashMap<>();
   private Map<String, Soundchanger.Data> field5 = new HashMap<>();

   public Soundchanger() {
   }

   public static Soundchanger method1() {
      if (field1 == null) {
         field1 = method3(true);
      }

      return field1;
   }

   public static Soundchanger method2() {
      if (field2 == null) {
         field2 = method3(false);
      }

      return field2;
   }

   private static Soundchanger method3(boolean flag0) {
      Bridge11_2 bridge11_21 = Ref.method3().bridge$getResourceManager();
      Soundchanger soundchanger2 = new Soundchanger();

      try {
         JsonObject json3 = method5(field3, bridge11_21);
         if (json3 == null) {
            return soundchanger2;
         }

         JsonObject json4 = json3.get(flag0 ? "modern" : "legacy").getAsJsonObject();

         for (Entry entry6 : json4.get("name_mappings").getAsJsonObject().entrySet()) {
            soundchanger2.field4.put(ResourceLocationBridge.create((String)entry6.getKey()), ((JsonElement)entry6.getValue()).getAsString());
         }

         for (Entry entry11 : json4.get("category_mappings").getAsJsonObject().entrySet()) {
            Soundchanger.Data data7 = new Soundchanger.Data();
            JsonObject json8 = ((JsonElement)entry11.getValue()).getAsJsonObject();
            data7.field1 = (String)entry11.getKey();
            data7.prettyName = json8.get("pretty_name").getAsString();
            data7.field2 = data7.field1;
            data7.index = 0;
            soundchanger2.field5.put((String)entry11.getKey(), data7);
            method4(json8, data7, data7.field2, 1);
         }
      } catch (Exception exception9) {
         CrashReporter.method5(exception9, "Loading SoundMappings");
      }

      return soundchanger2;
   }

   private static void method4(JsonObject json0, Soundchanger.Data data1, String text2, int number3) {
      if (json0.has("sub_mappings")) {
         json0.get("sub_mappings").getAsJsonObject().entrySet().forEach(arg3x -> {
            Soundchanger.Data data4 = new Soundchanger.Data();
            data4.field1 = (String)arg3x.getKey();
            data4.prettyName = ((JsonElement)arg3x.getValue()).getAsJsonObject().get("pretty_name").getAsString();
            data4.field2 = text2 + "." + data4.field1;
            data4.index = number3;
            method4(((JsonElement)arg3x.getValue()).getAsJsonObject(), data4, data4.field2, number3 + 1);
            data1.field3.put(data4.field1, data4);
         });
      }
   }

   @Nullable
   private static JsonObject method5(ResourceLocationBridge horsestats140, Bridge11_2 bridge11_21) {
      String text2 = method6(horsestats140, bridge11_21);
      return text2 == null ? null : new JsonParser().parse(text2).getAsJsonObject();
   }

   @Nullable
   private static String method6(ResourceLocationBridge horsestats140, Bridge11_2 bridge11_21) {
      ResourceBridge bridge152 = bridge11_21.bridge$getResource(horsestats140);
      if (bridge152 == null) {
         LunarLogger.method5("Couldn't find the sound mappings file: " + horsestats140, new Object[0]);
         return null;
      }

      try (InputStream input3 = bridge152.bridge$getInputStream()) {
         return IOUtils.toString(input3);
      }
   }

   @Generated
   public Map<ResourceLocationBridge, String> method7() {
      return this.field4;
   }

   @Generated
   public Map<String, Soundchanger.Data> method8() {
      return this.field5;
   }

   public static class Data {
      private String field1;
      private String prettyName;
      private String field2;
      private int index = 0;
      private Map<String, Soundchanger.Data> field3 = new HashMap<>();

      public Data() {
      }

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public String getPrettyName() {
         return this.prettyName;
      }

      @Generated
      public String method2() {
         return this.field2;
      }

      @Generated
      public int getIndex() {
         return this.index;
      }

      @Generated
      public Map<String, Soundchanger.Data> method3() {
         return this.field3;
      }
   }
}
