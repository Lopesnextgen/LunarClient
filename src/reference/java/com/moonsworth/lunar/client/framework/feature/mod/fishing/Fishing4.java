package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class Fishing4 {
   private final Map<String, ModifierKeybindOption> field1 = new HashMap<>();
   private final Set<String> field2 = new LinkedHashSet<>();
   private int field3 = Fishing2_3.field2;
   private final Map<String, Set<String>> field4 = new HashMap<>();

   public void method1(Fishing_2 var1, String var2) {
      if (var1.method3() && var1.method4()) {
         var1.method6().add(var2);
         this.field4.put(var1.key(), var1.method6());
      } else {
         throw new IllegalArgumentException("Tried to add a user defined subcommand to a command that does not allow it.");
      }
   }

   public void method2(Fishing_2 var1, String var2) {
      if (var1.method3() && var1.method4()) {
         var1.method6().remove(var2);
         this.field4.get(var1.key()).remove(var2);
      } else {
         throw new IllegalArgumentException("Tried to remove a user defined subcommand to a command that does not allow it.");
      }
   }

   public void method3() {
      JsonDeserializerIterator$Data var1 = this.method4();
      if (var1 != null && !var1.method1().isEmpty()) {
         if (this.field3 < Fishing2_3.field2) {
            Fishing2_3.method1(this, var1, this.field3);
            this.field3 = Fishing2_3.field2;
         }

         for (Entry var3 : this.field4.entrySet()) {
            Fishing_2 var4 = this.method10((String)var3.getKey());
            if (var4 != null && var4.method6() != null) {
               var4.method6().addAll((Collection<? extends String>)var3.getValue());
            }
         }
      }
   }

   private JsonDeserializerIterator$Data method4() {
      return ThreadModuleDump63.method4().method40().method82().method15().method11();
   }

   public ModifierKeybindOption method5(Fishing_2 var1) {
      return !this.field1.containsKey(var1.key()) ? this.method7(var1.key()) : this.field1.get(var1.key());
   }

   public ModifierKeybindOption method6(Fishing_2 var1, String var2) {
      return !this.field1.containsKey(var1.key(var2)) ? this.method7(var1.key(var2)) : this.field1.get(var1.key(var2));
   }

   ModifierKeybindOption method7(String var1) {
      if (this.field1.containsKey(var1)) {
         return this.field1.get(var1);
      }

      ModifierKeybindOption var2 = (ModifierKeybindOption)((Data)OptionFactory.method18(var1).CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
         .method31();
      this.field1.put(var1, var2);
      var2.method3(() -> {
         if (this.method8()) {
            String var2x = this.method9(var1);
            if (var2x != null) {
               ThreadModuleDump63.method7().bridge$sendCommand("/" + var2x);
            }
         }
      });
      return var2;
   }

   public boolean method8() {
      return ThreadModuleDump63.method4().method40().method82().isEnabled() && Click3.hasIsland();
   }

   public String method9(String var1) {
      JsonDeserializerIterator$Data var2 = this.method4();
      if (var2 == null) {
         return null;
      }

      for (Fishing_2 var4 : var2.method1()) {
         String var5 = this.method12(var1, var4);
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   public Fishing_2 method10(String var1) {
      JsonDeserializerIterator$Data var2 = this.method4();
      if (var2 == null) {
         return null;
      }

      for (Fishing_2 var4 : var2.method1()) {
         Fishing_2 var5 = this.method11(var1, var4);
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   private Fishing_2 method11(String var1, Fishing_2 var2) {
      if (var2.key().equals(var1)) {
         return var2;
      }

      if (var2.method3()) {
         if (!var2.method4()) {
            for (Fishing_2 var4 : var2.method5()) {
               Fishing_2 var5 = this.method11(var1, var4);
               if (var5 != null) {
                  return var5;
               }
            }
         } else if (var1.startsWith(var2.key() + ":")) {
            int var7 = var2.key().length() + 1;
            String var8 = var1.substring(var7);

            for (String var6 : var2.method6()) {
               if (var8.equals(var6)) {
                  return var2;
               }
            }
         }
      }

      return null;
   }

   private String method12(String var1, Fishing_2 var2) {
      if (var2.key().equals(var1)) {
         return var2.getCommand();
      }

      if (var2.method3()) {
         if (!var2.method4()) {
            for (Fishing_2 var4 : var2.method5()) {
               String var5 = this.method12(var1, var4);
               if (var5 != null) {
                  return var5;
               }
            }
         } else if (var1.startsWith(var2.key() + ":")) {
            int var7 = var2.key().length() + 1;
            String var8 = var1.substring(var7);

            for (String var6 : var2.method6()) {
               if (var8.equals(var6)) {
                  return var2.getCommand() + " " + var6;
               }
            }
         }
      }

      return null;
   }

   public void load(JsonObject var1) {
      JsonObject var2 = var1.getAsJsonObject();
      this.field3 = var2.has("sbCommandsVersion") ? var2.get("sbCommandsVersion").getAsInt() : 0;
      if (var2.has("sbCommandKeyBinds")) {
         JsonObject var3 = var2.get("sbCommandKeyBinds").getAsJsonObject();

         for (Entry var5 : var3.entrySet()) {
            String var6 = (String)var5.getKey();
            JsonObject var7 = ((JsonElement)var5.getValue()).getAsJsonObject();
            this.method7(var6).load(var7);
         }
      }

      if (var2.has("sbCommandFavorites")) {
         this.field2.clear();

         for (JsonElement var15 : var2.getAsJsonArray("sbCommandFavorites")) {
            this.field2.add(var15.getAsString());
         }
      }

      if (var2.has("sbUserDefinedSubCommands")) {
         this.field4.clear();
         JsonObject var12 = var2.get("sbUserDefinedSubCommands").getAsJsonObject();

         for (Entry var16 : var12.entrySet()) {
            String var17 = (String)var16.getKey();
            JsonArray var18 = ((JsonElement)var16.getValue()).getAsJsonArray();
            LinkedHashSet var8 = new LinkedHashSet();

            for (JsonElement var10 : var18) {
               var8.add(var10.getAsString());
            }

            this.field4.put(var17, var8);
         }
      }

      this.method3();
   }

   public void method13(JsonObject var1) {
      this.method3();
      JsonDeserializerIterator$Data var2 = this.method4();
      if (var2 != null && !var2.method1().isEmpty()) {
         var1.addProperty("sbCommandsVersion", this.field3);
         JsonObject var3 = new JsonObject();

         for (Fishing_2 var5 : var2.method1()) {
            this.method14(var5, var3);
         }

         var1.add("sbCommandKeyBinds", var3);
         JsonArray var13 = new JsonArray();

         for (String var6 : this.field2) {
            var13.add(new JsonPrimitive(var6));
         }

         var1.add("sbCommandFavorites", var13);
         JsonObject var15 = new JsonObject();

         for (Entry var7 : this.field4.entrySet()) {
            String var8 = (String)var7.getKey();
            Set var9 = (Set)var7.getValue();
            JsonArray var10 = new JsonArray();

            for (String var12 : var9) {
               var10.add(new JsonPrimitive(var12));
            }

            var15.add(var8, var10);
         }

         var1.add("sbUserDefinedSubCommands", var15);
      }
   }

   private void method14(Fishing_2 var1, JsonObject var2) {
      if (this.field1.containsKey(var1.key())) {
         ModifierKeybindOption var3 = this.field1.get(var1.key());
         JsonObject var4 = new JsonObject();
         var3.load(var4);
         var2.add(var1.key(), var4);
      }

      if (var1.method3()) {
         if (var1.method4()) {
            if (var1.method6() != null) {
               for (String var9 : var1.method6()) {
                  ModifierKeybindOption var5 = this.field1.get(var1.key(var9));
                  JsonObject var6 = new JsonObject();
                  var5.load(var6);
                  var2.add(var1.key(var9), var6);
               }
            }
         } else {
            for (Fishing_2 var10 : var1.method5()) {
               this.method14(var10, var2);
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
