package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.config.SettingsContainer;
import com.moonsworth.lunar.client.framework.metadata.RemoteMetadataManager;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.Calculator2Type;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map.Entry;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ClientSettingsConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      this.method2(var1.getAsJsonObject(), null);
   }

   protected void method2(JsonObject var1, Predicate<String> var2) {
      RemoteMetadataManager var3 = ThreadModuleDump63.method4().method72();

      for (Entry var5 : ThreadModuleDump63.method4().method41().IORHHHROCRRHORHRCHCCHHIHICCRCO().entrySet()) {
         String var6 = ((Calculator2Type)var5.getKey()).name();
         if (var1.has(var6) && var1.get(var6).isJsonObject()) {
            JsonObject var7 = var1.get(var6).getAsJsonObject();

            for (ClientOption var9 : ((SettingsContainer)var5.getValue()).method13()) {
               String var10 = this.method3(var9.getId(), var9, var7);
               if (var10 != null && !var9.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field12)) {
                  Alert2 var11 = (Alert2)var9.method1(OptionTraits.field5);
                  if (var11 != null) {
                     var3.method8(var7, var10, var9, var9, var11, var2);
                  }
               }
            }
         }
      }
   }

   @Nullable
   private String method3(String var1, ClientOption<?> var2, JsonObject var3) {
      if (var3.has(var1)) {
         return var1;
      }

      String var4 = ConfigMigrator.method9(var2, var3::has);
      return !var1.equals(var4) && var3.has(var4) ? var4 : null;
   }
}
