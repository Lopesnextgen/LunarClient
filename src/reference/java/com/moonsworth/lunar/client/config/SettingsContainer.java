package com.moonsworth.lunar.client.config;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.CategorizedSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump87;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.Contract;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;

public abstract class SettingsContainer extends ItemSetHandler<ClientOption<?>> implements JsonFileConfig, ThreadModuleDump87 {
   public Set<ClientOption<?>> method10() {
      return this.field1;
   }

   @Contract("-> new")
   @Override
   public Set<ClientOption<?>> method13() {
      LinkedHashSet var1 = new LinkedHashSet();

      for (ClientOption var3 : this.field1) {
         var1.add(var3);
         this.create(var3, var1x -> {
            var1.add(var1x);
            return false;
         });
      }

      return var1;
   }

   private void method3(ClientOption<?> var1, Predicate<ClientOption<?>> var2) {
      for (ClientOption var4 : var1.getChildren()) {
         if (var2.test(var4)) {
            return;
         }

         this.create(var4, var2);
      }
   }

   @Override
   public boolean method7() {
      return true;
   }

   @Override
   public void init() {
      super.init();
      this.method11();
   }

   @Override
   public void close() {
      super.close();
      if (ThreadModuleDump63.method8() == null) {
         this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }
   }

   public boolean method11() {
      return false;
   }

   @Override
   public File method6() {
      return this.method11()
         ? new File(ThreadModuleDump48.field25, this.method5())
         : new File(
            ThreadModuleDump48.field25 + File.separator + ThreadModuleDump63.method4().method61().method14().getName(), this.method5()
         );
   }

   private void method7(JsonObject var1, boolean var2) {
      boolean var3 = ConfigMigrator.method3(this, var1) && this.method7();

      for (ClientOption var5 : this.method13()) {
         if (var2 || !var5.method2(OptionTraits.field6)) {
            try {
               var5.load(var1);
            } catch (Exception var7) {
               var7.printStackTrace();
            }
         }
      }

      if (var3) {
         this.method7(true);
      }
   }

   public void load(JsonObject var1) {
      this.method7(var1, this.method11());
   }

   private void method8(JsonObject var1, boolean var2) {
      var1.addProperty("version", ConfigMigrator.field2);

      for (ClientOption var4 : this.method13()) {
         if (var2 || !var4.method2(OptionTraits.field6)) {
            try {
               var4.method1(var1);
            } catch (Exception var6) {
               var6.printStackTrace();
            }
         }
      }
   }

   public void method1(JsonObject var1) {
      this.method8(var1, this.method11());
   }

   protected abstract void method10(SettingsTreeAssembler var1);

   protected SettingsTreeAssembler method12() {
      return new SettingsTreeAssembler(SettingsSectionImpl::new);
   }

   @Override
   protected final Set<ClientOption<?>> method3() {
      return new LinkedHashSet<>();
   }

   public void method2() {
      this.method10().addAll(this.method14());
   }

   private Set<ClientOption<?>> method14() {
      SettingsTreeAssembler var1 = this.method12();
      this.method10(var1);
      return CategorizedSettingsBuilder.method15(
         var1.method12(),
         var1.method2(this.getClass().getSimpleName(), com.moonsworth.lunar.client.framework.build.LunarBuildData.field4),
         com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher.field2 == null,
         null
      );
   }
}
