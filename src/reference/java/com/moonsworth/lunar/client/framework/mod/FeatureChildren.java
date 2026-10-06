package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.FavoriteColorsConfig;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
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

public class FeatureChildren implements ModChildren, JsonConfigurable {
   @Nullable
   private final Predicate<String> field1;
   private final List<Framework7Extension> field2 = new ArrayList<>();
   private JsonObject field3 = null;

   @Override
   public Map<SettingsPage, List<Framework7Extension>> method2() {
      HashMap map1 = new HashMap();

      for (Framework7Extension framework7extension3 : this.field2) {
         if (framework7extension3.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field17)) {
            for (SettingsPage rewindhandlerstype6 : ((ModCategories)Objects.requireNonNull((ModCategories)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field17))).method1()) {
               if (!map1.containsKey(rewindhandlerstype6)) {
                  map1.put(rewindhandlerstype6, new ArrayList());
               }

               ((List)map1.get(rewindhandlerstype6)).add(framework7extension3);
            }
         }
      }

      return map1;
   }

   public void method2(Framework7Extension framework7extension1, List<Framework7Extension> list2) {
      ModSupport framework133 = (ModSupport)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field18);
      if (framework133 == null || framework133.method1()) {
         this.field2.clear();
         list2.forEach(arg1x -> this.method4(arg1x, false));
      }
   }

   public boolean method3(@NotNull Framework7Extension framework7extension1) {
      return this.method4(framework7extension1, true);
   }

   private boolean method4(@NotNull Framework7Extension framework7extension1, boolean flag2) {
      ModSupport framework133 = (ModSupport)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field18);
      if (framework133 != null && !framework133.method1()) {
         return false;
      }

      this.field2.add(framework7extension1);
      framework7extension1.method5();
      if (flag2) {
         FavoriteColorsConfig.method7(framework7extension1);
      }

      return true;
   }

   public void method5(@NotNull Framework7Extension framework7extension1) {
      this.field2.remove(framework7extension1);
   }

   public void load(JsonObject json1) {
      int number2 = json1.has("version") ? json1.get("version").getAsInt() : 0;
      HashSet set3 = this.field1 == null ? null : new HashSet();
      this.field3 = null;

      for (Framework7Extension framework7extension5 : this.getChildren()) {
         try {
            String text6 = framework7extension5.getId();
            if (set3 != null) {
               set3.add(text6);
            }

            JsonObject json7 = json1.has(text6) && !json1.get(text6).isJsonNull() ? json1.getAsJsonObject(text6) : new JsonObject();
            json7.addProperty("version", number2);
            framework7extension5.load(json7);
         } catch (Exception exception8) {
            exception8.printStackTrace();
         }
      }

      if (this.field1 != null && set3 != null) {
         JsonObject json9 = new JsonObject();

         for (String text11 : json1.keySet()) {
            if (this.field1.test(text11) && !set3.contains(text11)) {
               json9.add(text11, json1.get(text11));
            }
         }

         if (!json9.isEmpty()) {
            this.field3 = json9;
         }
      }
   }

   public void method1(JsonObject json1) {
      for (Framework7Extension framework7extension3 : this.getChildren()) {
         try {
            JsonObject json4 = new JsonObject();
            framework7extension3.HRICOROOOCCOCOROCRHHCRRIRCOICO(json4);
            if (!json4.entrySet().isEmpty()) {
               json1.add(framework7extension3.getId(), json4);
            }
         } catch (Exception exception5) {
            exception5.printStackTrace();
         }
      }

      if (this.field3 != null) {
         for (Entry entry7 : this.field3.entrySet()) {
            json1.add((String)entry7.getKey(), (JsonElement)entry7.getValue());
         }
      }
   }

   public int priority() {
      return 1500;
   }

   @Generated
   public FeatureChildren(@Nullable Predicate<String> predicate1) {
      this.field1 = predicate1;
   }

   @Generated
   public List<Framework7Extension> getChildren() {
      return this.field2;
   }
}
