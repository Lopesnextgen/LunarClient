package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Iterator;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ModSettingsConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      this.method2(var1.getAsJsonObject(), null);
   }

   protected void method2(JsonObject var1, Predicate<@Nullable String> var2) {
      ConfigMigrator.method3(this, var1);
      Iterator var3 = ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().iterator();

      while (true) {
         Framework7Extension var4;
         Alert2 var5;
         JsonObject var8;
         while (true) {
            if (!var3.hasNext()) {
               return;
            }

            var4 = (Framework7Extension)var3.next();
            var5 = (Alert2)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
            if (var5 != null) {
               String var6 = var4.getId();
               String var7 = ConfigMigrator.method5(var4);
               if (var1.has(var7)) {
                  var8 = var1.getAsJsonObject(var7);
                  break;
               }

               if (var1.has(var6)) {
                  var8 = var1.getAsJsonObject(var6);
                  break;
               }
            }
         }

         if (var8 != null) {
            if (var8.has("enabled")) {
               if (var2 == null) {
                  var5.method1(var4, AlertType.SERVER, var8.get("enabled").getAsBoolean());
               } else {
                  var5.method6(var2, var8.get("enabled").getAsBoolean());
                  var4.updateEnabled();
               }
            }

            JsonObject var9 = !var8.has("properties") ? null : var8.getAsJsonObject("properties");
            if (var9 != null) {
               AlertExtension var10 = (AlertExtension)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
               if (var10 != null) {
                  var10.method3(var4x -> {
                     Alert2 var5x = (Alert2)var4x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
                     if (var5x == null) {
                        return false;
                     }

                     String var6x = var4x.getId();
                     AlertType var7x = var5x.method2();
                     if (var7x == AlertType.SERVER || (var6x = this.method3(var6x, var4x, var9)) != null) {
                        ClientOption var8x = var4x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field6).flatMap(ModEnabledState::method1).orElse(null);
                        if (var8x != null) {
                           ThreadModuleDump63.method4().method72().method8(var9, var6x, var4x, var8x, var5x, var2);
                        }

                        var4.updateEnabled();
                     }

                     return true;
                  });
               }

               Framework5 var11 = (Framework5)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
               if (var11 != null) {
                  for (ClientOption var13 : var11.method2()) {
                     String var14 = var13.getId();
                     Alert2 var15 = (Alert2)var13.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field5);
                     if (var15 != null && !var13.method2(OptionTraits.field12)) {
                        AlertType var16 = var15.method2();
                        if (var16 == AlertType.SERVER || (var14 = this.method4(var14, var13, var9)) != null) {
                           ThreadModuleDump63.method4().method72().method8(var9, var14, var13, var13, var15, var2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   private String method3(String var1, Framework7Extension var2, JsonObject var3) {
      if (var3.has(var1)) {
         return var1;
      }

      String var4 = ConfigMigrator.method6(var2, var3::has);
      return !var1.equals(var4) && var3.has(var4) ? var4 : null;
   }

   @Nullable
   private String method4(String var1, ClientOption<?> var2, JsonObject var3) {
      if (var3.has(var1)) {
         return var1;
      }

      String var4 = ConfigMigrator.method9(var2, var3::has);
      return !var1.equals(var4) && var3.has(var4) ? var4 : null;
   }
}
