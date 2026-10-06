package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class SkyBlockCommandKeybinds {
   private final Map<String, ModifierKeybindOption> field1 = new HashMap<>();
   private final Set<String> field2 = new LinkedHashSet<>();
   private int field3 = SkyBlockCommandMigrations.field2;
   private final Map<String, Set<String>> field4 = new HashMap<>();

   public SkyBlockCommandKeybinds() {
   }

   public void method1(SkyBlockCommand fishing_21, String text2) {
      if (fishing_21.method3() && fishing_21.method4()) {
         fishing_21.method6().add(text2);
         this.field4.put(fishing_21.key(), fishing_21.method6());
      } else {
         throw new IllegalArgumentException("Tried to add a user defined subcommand to a command that does not allow it.");
      }
   }

   public void method2(SkyBlockCommand fishing_21, String text2) {
      if (fishing_21.method3() && fishing_21.method4()) {
         fishing_21.method6().remove(text2);
         this.field4.get(fishing_21.key()).remove(text2);
      } else {
         throw new IllegalArgumentException("Tried to remove a user defined subcommand to a command that does not allow it.");
      }
   }

   public void method3() {
      SkyBlockCommandConfig jsondeserializeriterator$data1 = this.method4();
      if (jsondeserializeriterator$data1 != null && !jsondeserializeriterator$data1.method1().isEmpty()) {
         if (this.field3 < SkyBlockCommandMigrations.field2) {
            SkyBlockCommandMigrations.method1(this, jsondeserializeriterator$data1, this.field3);
            this.field3 = SkyBlockCommandMigrations.field2;
         }

         for (Entry entry3 : this.field4.entrySet()) {
            SkyBlockCommand fishing_24 = this.method10((String)entry3.getKey());
            if (fishing_24 != null && fishing_24.method6() != null) {
               fishing_24.method6().addAll((Collection<? extends String>)entry3.getValue());
            }
         }
      }
   }

   private SkyBlockCommandConfig method4() {
      return Ref.method4().method40().method82().method15().method11();
   }

   public ModifierKeybindOption method5(SkyBlockCommand fishing_21) {
      return !this.field1.containsKey(fishing_21.key()) ? this.method7(fishing_21.key()) : this.field1.get(fishing_21.key());
   }

   public ModifierKeybindOption method6(SkyBlockCommand fishing_21, String text2) {
      return !this.field1.containsKey(fishing_21.key(text2)) ? this.method7(fishing_21.key(text2)) : this.field1.get(fishing_21.key(text2));
   }

   ModifierKeybindOption method7(String text1) {
      if (this.field1.containsKey(text1)) {
         return this.field1.get(text1);
      }

      ModifierKeybindOption lightingextension491332 = (ModifierKeybindOption)((Data)OptionFactory.method18(text1).CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field1.put(text1, lightingextension491332);
      lightingextension491332.method3(() -> {
         if (this.method8()) {
            String text2x = this.method9(text1);
            if (text2x != null) {
               Ref.method7().bridge$sendCommand("/" + text2x);
            }
         }
      });
      return lightingextension491332;
   }

   public boolean method8() {
      return Ref.method4().method40().method82().isEnabled() && IslandUtils.isOnIsland();
   }

   public String method9(String text1) {
      SkyBlockCommandConfig jsondeserializeriterator$data2 = this.method4();
      if (jsondeserializeriterator$data2 == null) {
         return null;
      }

      for (SkyBlockCommand fishing_24 : jsondeserializeriterator$data2.method1()) {
         String text5 = this.method12(text1, fishing_24);
         if (text5 != null) {
            return text5;
         }
      }

      return null;
   }

   public SkyBlockCommand method10(String text1) {
      SkyBlockCommandConfig jsondeserializeriterator$data2 = this.method4();
      if (jsondeserializeriterator$data2 == null) {
         return null;
      }

      for (SkyBlockCommand fishing_24 : jsondeserializeriterator$data2.method1()) {
         SkyBlockCommand fishing_25 = this.method11(text1, fishing_24);
         if (fishing_25 != null) {
            return fishing_25;
         }
      }

      return null;
   }

   private SkyBlockCommand method11(String text1, SkyBlockCommand fishing_22) {
      if (fishing_22.key().equals(text1)) {
         return fishing_22;
      }

      if (fishing_22.method3()) {
         if (!fishing_22.method4()) {
            for (SkyBlockCommand fishing_24 : fishing_22.method5()) {
               SkyBlockCommand fishing_25 = this.method11(text1, fishing_24);
               if (fishing_25 != null) {
                  return fishing_25;
               }
            }
         } else if (text1.startsWith(fishing_22.key() + ":")) {
            int index7 = fishing_22.key().length() + 1;
            String text8 = text1.substring(index7);

            for (String text6 : fishing_22.method6()) {
               if (text8.equals(text6)) {
                  return fishing_22;
               }
            }
         }
      }

      return null;
   }

   private String method12(String text1, SkyBlockCommand fishing_22) {
      if (fishing_22.key().equals(text1)) {
         return fishing_22.getCommand();
      }

      if (fishing_22.method3()) {
         if (!fishing_22.method4()) {
            for (SkyBlockCommand fishing_24 : fishing_22.method5()) {
               String text5 = this.method12(text1, fishing_24);
               if (text5 != null) {
                  return text5;
               }
            }
         } else if (text1.startsWith(fishing_22.key() + ":")) {
            int index7 = fishing_22.key().length() + 1;
            String text8 = text1.substring(index7);

            for (String text6 : fishing_22.method6()) {
               if (text8.equals(text6)) {
                  return fishing_22.getCommand() + " " + text6;
               }
            }
         }
      }

      return null;
   }

   public void load(JsonObject json1) {
      JsonObject json2 = json1.getAsJsonObject();
      this.field3 = json2.has("sbCommandsVersion") ? json2.get("sbCommandsVersion").getAsInt() : 0;
      if (json2.has("sbCommandKeyBinds")) {
         JsonObject json3 = json2.get("sbCommandKeyBinds").getAsJsonObject();

         for (Entry entry5 : json3.entrySet()) {
            String text6 = (String)entry5.getKey();
            JsonObject json7 = ((JsonElement)entry5.getValue()).getAsJsonObject();
            this.method7(text6).load(json7);
         }
      }

      if (json2.has("sbCommandFavorites")) {
         this.field2.clear();

         for (JsonElement element15 : json2.getAsJsonArray("sbCommandFavorites")) {
            this.field2.add(element15.getAsString());
         }
      }

      if (json2.has("sbUserDefinedSubCommands")) {
         this.field4.clear();
         JsonObject json12 = json2.get("sbUserDefinedSubCommands").getAsJsonObject();

         for (Entry entry16 : json12.entrySet()) {
            String text17 = (String)entry16.getKey();
            JsonArray array18 = ((JsonElement)entry16.getValue()).getAsJsonArray();
            LinkedHashSet set8 = new LinkedHashSet();

            for (JsonElement element10 : array18) {
               set8.add(element10.getAsString());
            }

            this.field4.put(text17, set8);
         }
      }

      this.method3();
   }

   public void method13(JsonObject json1) {
      this.method3();
      SkyBlockCommandConfig jsondeserializeriterator$data2 = this.method4();
      if (jsondeserializeriterator$data2 != null && !jsondeserializeriterator$data2.method1().isEmpty()) {
         json1.addProperty("sbCommandsVersion", this.field3);
         JsonObject json3 = new JsonObject();

         for (SkyBlockCommand fishing_25 : jsondeserializeriterator$data2.method1()) {
            this.method14(fishing_25, json3);
         }

         json1.add("sbCommandKeyBinds", json3);
         JsonArray array13 = new JsonArray();

         for (String text6 : this.field2) {
            array13.add(new JsonPrimitive(text6));
         }

         json1.add("sbCommandFavorites", array13);
         JsonObject json15 = new JsonObject();

         for (Entry entry7 : this.field4.entrySet()) {
            String text8 = (String)entry7.getKey();
            Set set9 = (Set)entry7.getValue();
            JsonArray array10 = new JsonArray();

            for (String text12 : set9) {
               array10.add(new JsonPrimitive(text12));
            }

            json15.add(text8, array10);
         }

         json1.add("sbUserDefinedSubCommands", json15);
      }
   }

   private void method14(SkyBlockCommand fishing_21, JsonObject json2) {
      if (this.field1.containsKey(fishing_21.key())) {
         ModifierKeybindOption lightingextension491333 = this.field1.get(fishing_21.key());
         JsonObject json4 = new JsonObject();
         lightingextension491333.method1(json4);
         json2.add(fishing_21.key(), json4);
      }

      if (fishing_21.method3()) {
         if (fishing_21.method4()) {
            if (fishing_21.method6() != null) {
               for (String text9 : fishing_21.method6()) {
                  ModifierKeybindOption lightingextension491335 = this.field1.get(fishing_21.key(text9));
                  JsonObject json6 = new JsonObject();
                  lightingextension491335.method1(json6);
                  json2.add(fishing_21.key(text9), json6);
               }
            }
         } else {
            for (SkyBlockCommand fishing_210 : fishing_21.method5()) {
               this.method14(fishing_210, json2);
            }
         }
      }
   }

   @Generated
   public Map<String, ModifierKeybindOption> method15() {
      return this.field1;
   }

   @Generated
   public Set<String> method16() {
      return this.field2;
   }

   @Generated
   public int method17() {
      return this.field3;
   }

   @Generated
   public Map<String, Set<String>> method18() {
      return this.field4;
   }
}
