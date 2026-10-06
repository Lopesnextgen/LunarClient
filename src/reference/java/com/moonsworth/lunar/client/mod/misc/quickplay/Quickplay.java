package com.moonsworth.lunar.client.mod.misc.quickplay;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.quickplay.mixin.QuickplayGamesScreen;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class Quickplay extends AbstractFeature {
   private boolean field8;
   private List<com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay> field9;
   private final Map<String, ModifierKeybindOption> field10 = new HashMap<>();
   private final Set<String> field11 = new LinkedHashSet<>();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)((Data)OptionFactory.method18("quickplayUIKeybind")
         .method5(KeyCode.KEY_R)
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();

   public Quickplay() {
      super(false);
      this.method9(
         ModTraits.field18,
         arg0 -> ((com.moonsworth.lunar.client.framework.mod.ModSupport.Data)arg0.build()).method5(KeystrokesType.HYPIXEL)
      );
   }

   public String getId() {
      return "QUICKPLAY";
   }

   public void method3(boolean flag1) {
      if (flag1 && !this.field8) {
         new Thread(() -> {
            Path path1x = LunarConstants.field12.resolve("hypixel/quickplay.json");

            try {
               String text2 = Files.readString(path1x);
               ArrayList list3 = new ArrayList();

               for (JsonElement element5 : JsonParser.parseString(text2).getAsJsonArray()) {
                  list3.add(this.method9(element5));
               }

               this.method15(ImmutableList.copyOf(list3));
            } catch (Exception exception6) {
               CrashReporter.method5(exception6, "Load QuickPlay Games");
            }
         }).start();
         this.field8 = true;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12});
      this.field12.method3(() -> {
         if (this.method13()) {
            this.mc.bridge$displayScreen(Bridge.method8().method18(new QuickplayGamesScreen()));
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method3(new String[]{"bugfroggy"}).method11(this);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      JsonObject json2 = json1.getAsJsonObject();
      if (json2.has("qpKeyBinds")) {
         JsonObject json3 = json2.get("qpKeyBinds").getAsJsonObject();

         for (Entry entry5 : json3.entrySet()) {
            String text6 = (String)entry5.getKey();
            JsonObject json7 = ((JsonElement)entry5.getValue()).getAsJsonObject();
            this.method7(text6).load(json7);
         }
      }

      if (json2.has("qpFavorites")) {
         this.field11.clear();

         for (JsonElement element10 : json2.getAsJsonArray("qpFavorites")) {
            this.field11.add(element10.getAsString());
         }
      }
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      if (this.field9 != null && !this.field9.isEmpty()) {
         JsonObject json2 = new JsonObject();

         for (com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay4 : this.field9) {
            this.method5(quickplay4, json2);
         }

         json1.add("qpKeyBinds", json2);
         JsonArray array6 = new JsonArray();

         for (String text5 : this.field11) {
            array6.add(new JsonPrimitive(text5));
         }

         json1.add("qpFavorites", array6);
      }
   }

   private void method5(com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay1, JsonObject json2) {
      if (this.field10.containsKey(quickplay1.getKey())) {
         ModifierKeybindOption lightingextension491333 = this.field10.get(quickplay1.getKey());
         JsonObject json4 = new JsonObject();
         lightingextension491333.method1(json4);
         json2.add(quickplay1.getKey(), json4);
      }

      if (quickplay1.method4() != null) {
         for (com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay6 : quickplay1.method4()) {
            this.method5(quickplay6, json2);
         }
      }
   }

   public ModifierKeybindOption method6(com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay1) {
      if (!this.field10.containsKey(quickplay1.getKey())) {
         this.method7(quickplay1.getKey());
      }

      return this.field10.get(quickplay1.getKey());
   }

   private ModifierKeybindOption method7(String text1) {
      ModifierKeybindOption lightingextension491332 = (ModifierKeybindOption)((Data)OptionFactory.method18(text1).CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
         .method31();
      this.field10.put(text1, lightingextension491332);
      lightingextension491332.method3(() -> {
         if (this.method13()) {
            com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay2x = this.method10(text1);
            if (quickplay2x != null) {
               Ref.method7().bridge$sendCommand(quickplay2x.method2());
            }
         }
      });
      return lightingextension491332;
   }

   private boolean method13() {
      return this.isEnabled();
   }

   public com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay method9(JsonElement element1) {
      com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay2 = new com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay();
      JsonObject json3 = element1.getAsJsonObject();
      quickplay2.setKey(json3.get("key").getAsString());
      quickplay2.setName(json3.get("name").getAsString());
      if (json3.has("icon")) {
         quickplay2.method8(json3.get("icon").getAsString());
      }

      if (json3.has("command")) {
         quickplay2.setCommand(json3.get("command").getAsString());
      }

      if (json3.has("modes")) {
         ArrayList list4 = new ArrayList();

         for (JsonElement element6 : json3.get("modes").getAsJsonArray()) {
            com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay7 = this.method9(element6);
            list4.add(quickplay7);
            quickplay7.method5(quickplay2);
         }

         quickplay2.method10(ImmutableList.copyOf(list4));
      }

      return quickplay2;
   }

   public com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay method10(String text1) {
      return this.method11(text1, this.field9);
   }

   private com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay method11(
      String text1, List<com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay> list2
   ) {
      for (com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay4 : list2) {
         if (quickplay4.getKey().equals(text1)) {
            return quickplay4;
         }

         if (quickplay4.method4() != null) {
            com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay5 = this.method11(text1, quickplay4.method4());
            if (quickplay5 != null) {
               return quickplay5;
            }
         }
      }

      return null;
   }

   public boolean method7() {
      return false;
   }

   @Generated
   public boolean method14() {
      return this.field8;
   }

   @Generated
   public List<com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay> method15() {
      return this.field9;
   }

   @Generated
   public void method15(List<com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay> list1) {
      this.field9 = list1;
   }

   @Generated
   public Set<String> method16() {
      return this.field11;
   }
}
