package com.moonsworth.lunar.client.config;

import com.google.common.collect.Sets;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.FrameworkType;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;

public abstract class FavoriteColorsConfig<F extends Framework7Extension> extends ItemSetHandler<F> implements JsonFileConfig {
   protected abstract Set<F> method1();

   @Override
   protected final Set<F> method3() {
      Set var1 = this.method1();
      var1.removeIf(var0 -> var0.method3(Framework.field18).map(var0x -> !var0x.method1()).orElse(false));
      var1.forEach(this::method4);
      this.method5(var1);
      return var1;
   }

   @SafeVarargs
   protected final LinkedHashSet<F> method3(F... var1) {
      LinkedHashSet var2 = Sets.newLinkedHashSet();
      var2.addAll(Arrays.asList(var1));
      return var2;
   }

   @OverridingMethodsMustInvokeSuper
   protected void method4(F var1) {
      var1.method5();
   }

   protected void method5(Set<F> var1) {
   }

   @OverridingMethodsMustInvokeSuper
   @Override
   public void init() {
      super.init();
      this.method8();
   }

   @Override
   public boolean method8() {
      for (Framework7Extension var2 : this.method13()) {
         var2.method2(Framework.field8, FrameworkType.LOADING_CONFIG);
      }

      boolean var4 = JsonFileConfig.super.method8();

      for (Framework7Extension var3 : this.method13()) {
         method7(var3);
         var3.method3(Framework.field5).ifPresent(var0 -> var0.method2(FavoriteColorsConfig::method7));
      }

      return var4;
   }

   public static void method7(Framework7Extension var0) {
      ModEnabledState var1 = (ModEnabledState)var0.method1(Framework.field6);
      if (var1 != null && var1.method1().isPresent()) {
         if ((Boolean)var0.method9(Framework.field7, false)) {
            var1.method5(var0, var1.isEnabled());
         } else {
            var0.method2(Framework.field7, true);
            ((ClientOption)var1.method1().get()).method7(var2x -> var1.method5(var0, var2x));
         }
      } else {
         Framework10Extension var2 = (Framework10Extension)var0.method1(Framework.field12);
         if (var2 != null) {
            var2.method1(var0, true, false);
         }

         var0.method3(true);
         var0.updateEnabled();
      }

      var0.method2(Framework.field8, FrameworkType.COMPLETE);
   }

   @Override
   public boolean method7() {
      return true;
   }

   public void load(JsonObject var1) {
      boolean var2 = ConfigMigrator.method3(this, var1) && this.method7();
      int var3 = var1.has("version") ? var1.get("version").getAsInt() : 0;

      for (Framework7Extension var5 : this.method13()) {
         try {
            String var6 = var5.getId();
            JsonObject var7;
            if (var1.has(var6) && !var1.get(var6).isJsonNull()) {
               var7 = var1.getAsJsonObject(var6);
               var7.addProperty("version", var3);
            } else {
               var7 = new JsonObject();
            }

            var5.load(var7);
         } catch (Exception var8) {
            var8.printStackTrace();
         }

         var5.method2(Framework.field8, FrameworkType.LOADED_CONFIG);
      }

      if (var2) {
         this.method7(true);
      }
   }

   public void method1(JsonObject var1) {
      var1.addProperty("version", ConfigMigrator.field2);

      for (Framework7Extension var3 : this.method13()) {
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
   }

   @Override
   public void close() {
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }
}
