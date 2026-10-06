package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.CategorizedSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionRegistrant;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class Nameplate6 implements Framework5, JsonPersistable {
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
      LinkedHashSet var1 = new LinkedHashSet();

      for (ClientOption var3 : this.field2) {
         var1.add(var3);
         this.method5(var3, var1x -> {
            var1.add(var1x);
            return false;
         });
      }

      return var1;
   }

   @Contract("-> new")
   @Override
   public Set<ClientOption<?>> method3() {
      Set var1 = this.method2();
      if (this.field4 != null) {
         var1.addAll(this.field4);
      }

      return var1;
   }

   @Override
   public void method4(Predicate<ClientOption<?>> var1) {
      for (ClientOption var3 : this.field2) {
         if (var1.test(var3)) {
            return;
         }

         this.method5(var3, var1);
      }
   }

   private void method5(ClientOption<?> var1, Predicate<ClientOption<?>> var2) {
      for (ClientOption var4 : var1.getChildren()) {
         if (var2.test(var4)) {
            return;
         }

         this.method5(var4, var2);
      }
   }

   @Override
   public void method5(Framework7Extension var1) {
      this.field2.clear();
      this.field2.addAll(this.method8(var1));
   }

   protected RootSettingsAssembler method7() {
      return new RootSettingsAssembler(SettingsSectionImpl::new);
   }

   protected Set<ClientOption<?>> method8(Framework7Extension var1) {
      RootSettingsAssembler var2 = this.method7();
      var1.method2(var2);
      this.method9(var1, var2);
      return CategorizedSettingsBuilder.method15(
         var2.method12(),
         var2.method2(var1.getId(), com.moonsworth.lunar.client.framework.build.LunarBuildData.field4),
         ModMetadataFetcher.field2 == null,
         var1x -> {
            this.field3.add(var1x.getId());
            if (this.field4 != null) {
               this.field4.add(var1x);
            }
         }
      );
   }

   private void method9(Framework7Extension var1, RootSettingsAssembler var2) {
      for (Object var4 : var1.RRIHOHIHHHCHCIRRIORIIRCOOOOIHH().values()) {
         if (var4 instanceof OptionRegistrant var5) {
            var5.method1(var2);
         }
      }
   }

   public void load(JsonObject var1) {
      JsonElement var2 = var1.get("options");
      JsonObject var3 = var2 != null && !var2.isJsonNull() ? var2.getAsJsonObject() : new JsonObject();
      Set var4 = this.method2();
      HashSet var5 = new HashSet();

      for (ClientOption var7 : this.method2()) {
         try {
            var7.method16(var3, this.field6);
            var5.add(var7.getId());
         } catch (Exception var11) {
            var11.printStackTrace();
         }
      }

      for (ClientOption var8 : this.method2()) {
         if (!var4.contains(var8)) {
            try {
               var8.method16(var3, this.field6);
               var5.add(var8.getId());
            } catch (Exception var10) {
               var10.printStackTrace();
            }
         }
      }

      JsonObject var14 = new JsonObject();

      for (String var9 : var3.keySet()) {
         if (!var5.contains(var9) && (this.field3.contains(var9) || this.field1.test(var9))) {
            var14.add(var9, var3.get(var9));
         }
      }

      this.field5 = var14.isEmpty() ? null : var14;
      if (this.field4 != null) {
         this.field4.clear();
         this.field4 = null;
      }
   }

   public void method1(JsonObject var1) {
      JsonObject var2 = new JsonObject();
      HashSet var3 = this.field5 == null ? null : new HashSet();

      for (ClientOption var5 : this.method2()) {
         var5.method1(var2);
         if (var3 != null) {
            var3.add(var5.getId());
         }
      }

      if (this.field5 != null) {
         for (Entry var7 : this.field5.entrySet()) {
            if (var3 == null || !var3.contains(var7.getKey())) {
               var2.add((String)var7.getKey(), (JsonElement)var7.getValue());
            }
         }
      }

      if (!var2.entrySet().isEmpty()) {
         var1.add("options", var2);
      }
   }

   public int priority() {
      return 200;
   }

   @Generated
   public Nameplate6(Predicate<String> var1, boolean var2) {
      this.field1 = var1;
      this.field6 = var2;
   }
}
