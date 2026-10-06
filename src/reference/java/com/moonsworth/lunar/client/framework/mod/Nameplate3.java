package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.FavoriteColorsConfig;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Nameplate3 implements AlertExtension, JsonPersistable {
   @Nullable
   private final Predicate<String> field1;
   private final List<Framework7Extension> field2 = new ArrayList<>();
   private JsonObject field3 = null;

   @Override
   public Map<SettingsPage, List<Framework7Extension>> method2() {
      HashMap var1 = new HashMap();

      for (Framework7Extension var3 : this.field2) {
         if (var3.method2(Framework.field17)) {
            for (SettingsPage var6 : ((Framework2)Objects.requireNonNull((Framework2)var3.method1(Framework.field17))).method1()) {
               if (!var1.containsKey(var6)) {
                  var1.put(var6, new ArrayList());
               }

               ((List)var1.get(var6)).add(var3);
            }
         }
      }

      return var1;
   }

   public void method2(Framework7Extension var1, List<Framework7Extension> var2) {
      ModSupport var3 = (ModSupport)var1.method1(Framework.field18);
      if (var3 == null || var3.method1()) {
         this.field2.clear();
         var2.forEach(var1x -> this.method4(var1x, false));
      }
   }

   public boolean method3(@NotNull Framework7Extension var1) {
      return this.method4(var1, true);
   }

   private boolean method4(@NotNull Framework7Extension var1, boolean var2) {
      ModSupport var3 = (ModSupport)var1.method1(Framework.field18);
      if (var3 != null && !var3.method1()) {
         return false;
      }

      this.field2.add(var1);
      var1.method5();
      if (var2) {
         FavoriteColorsConfig.method7(var1);
      }

      return true;
   }

   public void method5(@NotNull Framework7Extension var1) {
      this.field2.remove(var1);
   }

   public void load(JsonObject var1) {
      int var2 = var1.has("version") ? var1.get("version").getAsInt() : 0;
      HashSet var3 = this.field1 == null ? null : new HashSet();
      this.field3 = null;

      for (Framework7Extension var5 : this.getChildren()) {
         try {
            String var6 = var5.getId();
            if (var3 != null) {
               var3.add(var6);
            }

            JsonObject var7 = var1.has(var6) && !var1.get(var6).isJsonNull() ? var1.getAsJsonObject(var6) : new JsonObject();
            var7.addProperty("version", var2);
            var5.load(var7);
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }

      if (this.field1 != null && var3 != null) {
         JsonObject var9 = new JsonObject();

         for (String var11 : var1.keySet()) {
            if (this.field1.test(var11) && !var3.contains(var11)) {
               var9.add(var11, var1.get(var11));
            }
         }

         if (!var9.isEmpty()) {
            this.field3 = var9;
         }
      }
   }

   public void method1(JsonObject var1) {
      for (Framework7Extension var3 : this.getChildren()) {
         try {
            JsonObject var4 = new JsonObject();
            var3.HRICOROOOCCOCOROCRHHCRRIRCOICO(var4);
            if (!var4.entrySet().isEmpty()) {
               var1.add(var3.getId(), var4);
            }
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      if (this.field3 != null) {
         for (Entry var7 : this.field3.entrySet()) {
            var1.add((String)var7.getKey(), (JsonElement)var7.getValue());
         }
      }
   }

   public int priority() {
      return 1500;
   }

   @Generated
   public Nameplate3(@Nullable Predicate<String> var1) {
      this.field1 = var1;
   }

   @Generated
   public List<Framework7Extension> getChildren() {
      return this.field2;
   }
}
