package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.CategorizedSettingsBuilder;
import com.moonsworth.lunar.client.config.option.SettingsRegistrant;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class FeatureOptionContainer implements OptionContainer, JsonConfigurable {
   protected final Predicate<String> field1;
   protected final LinkedHashSet<ClientOption<?>> field2 = new LinkedHashSet<>();
   protected final Set<String> field3 = ModMetadataFetcher.field2 == null ? new HashSet<>() : null;
   protected @Nullable Set<ClientOption<?>> field4 = ModMetadataFetcher.field2 == null ? new HashSet<>() : null;
   protected JsonObject field5 = null;
   private final boolean field6;

   @Override
   public Set<ClientOption<?>> method1() {
      return this.field2;
   }

   @Contract("-> new")
   @Override
   public Set<ClientOption<?>> method2() {
      LinkedHashSet set1 = new LinkedHashSet();

      for (ClientOption lightingextension3 : this.field2) {
         set1.add(lightingextension3);
         this.method5(lightingextension3, arg1x -> {
            set1.add(arg1x);
            return false;
         });
      }

      return set1;
   }

   @Contract("-> new")
   @Override
   public Set<ClientOption<?>> method3() {
      Set set1 = this.method2();
      if (this.field4 != null) {
         set1.addAll(this.field4);
      }

      return set1;
   }

   @Override
   public void method4(Predicate<ClientOption<?>> predicate1) {
      for (ClientOption lightingextension3 : this.field2) {
         if (predicate1.test(lightingextension3)) {
            return;
         }

         this.method5(lightingextension3, predicate1);
      }
   }

   private void method5(ClientOption<?> lightingextension1, Predicate<ClientOption<?>> predicate2) {
      for (ClientOption lightingextension4 : lightingextension1.getChildren()) {
         if (predicate2.test(lightingextension4)) {
            return;
         }

         this.method5(lightingextension4, predicate2);
      }
   }

   @Override
   public void method5(Framework7Extension framework7extension1) {
      this.field2.clear();
      this.field2.addAll(this.method8(framework7extension1));
   }

   protected RootSettingsBuilder method7() {
      return new RootSettingsBuilder(SettingsSectionImpl::new);
   }

   protected Set<ClientOption<?>> method8(Framework7Extension framework7extension1) {
      RootSettingsBuilder lightingextension232 = this.method7();
      framework7extension1.method2(lightingextension232);
      this.method9(framework7extension1, lightingextension232);
      return CategorizedSettingsBuilder.method15(
         lightingextension232.method12(),
         lightingextension232.method2(framework7extension1.getId(), com.moonsworth.lunar.client.framework.build.LunarBuildData.field4),
         ModMetadataFetcher.field2 == null,
         arg1x -> {
            this.field3.add(arg1x.getId());
            if (this.field4 != null) {
               this.field4.add(arg1x);
            }
         }
      );
   }

   private void method9(Framework7Extension framework7extension1, RootSettingsBuilder lightingextension232) {
      for (Object obj4 : framework7extension1.RRIHOHIHHHCHCIRRIORIIRCOOOOIHH().values()) {
         if (obj4 instanceof SettingsRegistrant lighting_35) {
            lighting_35.method1(lightingextension232);
         }
      }
   }

   public void load(JsonObject json1) {
      JsonElement element2 = json1.get("options");
      JsonObject json3 = element2 != null && !element2.isJsonNull() ? element2.getAsJsonObject() : new JsonObject();
      Set set4 = this.method2();
      HashSet set5 = new HashSet();

      for (ClientOption lightingextension7 : this.method2()) {
         try {
            lightingextension7.method16(json3, this.field6);
            set5.add(lightingextension7.getId());
         } catch (Exception exception11) {
            exception11.printStackTrace();
         }
      }

      for (ClientOption lightingextension8 : this.method2()) {
         if (!set4.contains(lightingextension8)) {
            try {
               lightingextension8.method16(json3, this.field6);
               set5.add(lightingextension8.getId());
            } catch (Exception exception10) {
               exception10.printStackTrace();
            }
         }
      }

      JsonObject json14 = new JsonObject();

      for (String text9 : json3.keySet()) {
         if (!set5.contains(text9) && (this.field3.contains(text9) || this.field1.test(text9))) {
            json14.add(text9, json3.get(text9));
         }
      }

      this.field5 = json14.isEmpty() ? null : json14;
      if (this.field4 != null) {
         this.field4.clear();
         this.field4 = null;
      }
   }

   public void method1(JsonObject json1) {
      JsonObject json2 = new JsonObject();
      HashSet set3 = this.field5 == null ? null : new HashSet();

      for (ClientOption lightingextension5 : this.method2()) {
         lightingextension5.method1(json2);
         if (set3 != null) {
            set3.add(lightingextension5.getId());
         }
      }

      if (this.field5 != null) {
         for (Entry entry7 : this.field5.entrySet()) {
            if (set3 == null || !set3.contains(entry7.getKey())) {
               json2.add((String)entry7.getKey(), (JsonElement)entry7.getValue());
            }
         }
      }

      if (!json2.entrySet().isEmpty()) {
         json1.add("options", json2);
      }
   }

   public int priority() {
      return 200;
   }

   @Generated
   public FeatureOptionContainer(Predicate<String> predicate1, boolean flag2) {
      this.field1 = predicate1;
      this.field6 = flag2;
   }
}
