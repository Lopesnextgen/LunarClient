package com.moonsworth.lunar.client.config.override;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.config.SettingsContainer;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.Calculator2Type;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class OptionOverrideLoader implements com.moonsworth.lunar.client.config.JsonFileConfig {
   public OptionOverrideLoader() {
      this.method4();
   }

   @Override
   public File method6() {
      String var1 = ThreadModuleDump63.method3().bridge$getMcDataDir() + File.separator + "config" + File.separator;
      return new File(var1, this.method5());
   }

   @Override
   public String method5() {
      return "lunar_option_overrides.json";
   }

   public void load(JsonObject var1) {
      Slayer.method3("Applying Config option overrides", new Object[0]);
      JsonElement var2 = var1.get("mods");
      if (var2 != null && var2.isJsonObject()) {
         JsonObject var3 = var2.getAsJsonObject();
         ModsSettings var4 = ThreadModuleDump63.method4().method40();

         for (Entry var6 : var3.entrySet()) {
            try {
               Framework7Extension var7 = var4.method11((String)var6.getKey());
               if (var7 != null) {
                  JsonElement var8 = (JsonElement)var6.getValue();
                  if (var8.isJsonObject()) {
                     JsonObject var9 = var8.getAsJsonObject();
                     this.method3(var7, var9);
                     Framework5 var10 = (Framework5)var7.method1(Framework.field14);
                     if (var10 != null) {
                        for (ClientOption var12 : var10.method2()) {
                           this.method4(var12, var9);
                        }
                     }
                  }
               }
            } catch (Exception var13) {
               Slayer.method7("Couldn't apply config mod override for [%s]: %s", new Object[]{var6.getKey(), var13});
            }
         }
      }

      JsonElement var14 = var1.get("settings");
      if (var14 != null && var14.isJsonObject()) {
         JsonObject var15 = var14.getAsJsonObject();
         Map var16 = ThreadModuleDump63.method4().method41().IORHHHROCRRHORHRCHCCHHIHICCRCO();

         for (Entry var18 : var16.entrySet()) {
            String var19 = ((Calculator2Type)var18.getKey()).name();
            JsonElement var20 = var15.get(var19);
            if (var20 != null && var20.isJsonObject()) {
               JsonObject var21 = var20.getAsJsonObject();

               for (ClientOption var23 : ((SettingsContainer)var18.getValue()).method13()) {
                  this.method4(var23, var21);
               }
            }
         }
      }
   }

   private void method3(Framework7Extension var1, JsonObject var2) {
      JsonElement var3 = var2.get("enabled");
      if (var3 != null && var3.isJsonPrimitive()) {
         var1.method3(Framework.field4).ifPresent(var2x -> var2x.method1(var1, AlertType.SERVER, var3.getAsBoolean()));
      }
   }

   private void method4(ClientOption var1, JsonObject var2) {
      try {
         JsonElement var3 = var2.get(var1.getId());
         if (var3 == null) {
            return;
         }

         if (var1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field12)) {
            return;
         }

         Alert2 var4 = (Alert2)var1.method1(OptionTraits.field5);
         if (var4 == null) {
            return;
         }

         Optional var5 = var1.method14(var3);
         if (var5.isEmpty()) {
            return;
         }

         Object var6 = var5.get();
         var4.method1(var1, AlertType.SERVER, var6);
         var4.method6(null, null);
      } catch (Exception var7) {
         Slayer.method7("Couldn't apply config option override for [%s]: %s", new Object[]{var1.getId(), var7});
      }
   }

   public void method1(JsonObject var1) {
   }

   @Override
   public void method9() {
   }
}
