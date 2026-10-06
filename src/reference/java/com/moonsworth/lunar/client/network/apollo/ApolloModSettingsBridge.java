package com.moonsworth.lunar.client.network.apollo;

import com.google.common.base.CaseFormat;
import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.mods.ApolloModsManager;
import com.lunarclient.apollo.mods.ApolloModsManager.Container;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;

public class ApolloModSettingsBridge {
   public static Container field1;

   public Map<String, Value> method1() {
      HashMap var1 = new HashMap();

      for (Framework7Extension var3 : ThreadModuleDump63.method4().method40().method1()) {
         if (var3 instanceof AbstractFeature var4) {
            this.method5(var4, var1);
         }
      }

      return var1;
   }

   public void method2(ClientOption<?> var1, Object var2) {
      this.method3(var1, (String)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field9), var2);
   }

   public void method3(ClientOption<?> var1, @Nullable String var2, Object var3) {
      if (var2 != null) {
         if (ThreadModuleDump63.method9() != null) {
            if (ThreadModuleDump63.method4() != null) {
               if (field1 != null) {
                  ApolloModuleManager var4 = ThreadModuleDump63.method4().method84();
                  if (var4.method24()) {
                     if (!ThreadModuleDump63.method4().method40().method85().method19()) {
                        String var5 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, var2);
                        String var6 = var1.method4();
                        String var7 = var5 + "." + var6;
                        Option var8 = (Option)field1.getModStatusOptions().get(var7);
                        if (var8 != null) {
                           Value var9;
                           try {
                              var9 = NetworkOptions.wrapValue(Value.newBuilder(), var8.getTypeToken().getType(), var3);
                           } catch (ClassCastException var12) {
                              String var11 = String.format(
                                 "Failed to wrap option '%s'. The provided value '%s' (Type: %s) could not be converted.",
                                 var7,
                                 var3,
                                 var3.getClass().getSimpleName()
                              );
                              Slayer.method6("Apollo", var11);
                              return;
                           }

                           ConfigurableSettings var10 = ConfigurableSettings.newBuilder().setApolloModule("mod_setting").putProperties(var7, var9).build();
                           var4.sendPacket(var10);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean method4(ClientOption<?> var1) {
      return !var1.isDefault() && com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher.method10(var1);
   }

   private void method5(AbstractFeature var1, Map<String, Value> var2) {
      String var3 = var1.method12();
      var1.method3(Framework.field6)
         .flatMap(ModEnabledState::method1)
         .filter(var0 -> !var0.isDefault())
         .ifPresent(var3x -> this.method6(var3, (ClientOption<?>)var3x, var2, var3x.get()));
      Framework5 var4 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var4 != null) {
         var4.method4(var3x -> {
            this.method6(var3, var3x, var2, var3x.get());
            return false;
         });
      }
   }

   private void method6(String var1, ClientOption<?> var2, Map<String, Value> var3, Object var4) {
      if (this.method4(var2)) {
         String var5 = var1 + "." + var2.method4();
         Option var6 = (Option)field1.getModStatusOptions().get(var5);
         if (var6 != null) {
            Value var7;
            try {
               var7 = NetworkOptions.wrapValue(Value.newBuilder(), var6.getTypeToken().getType(), var4);
            } catch (ClassCastException var10) {
               String var9 = String.format(
                  "Failed to wrap option '%s'. The provided value '%s' (Type: %s) could not be converted.", var5, var4, var4.getClass().getSimpleName()
               );
               Slayer.method6("Apollo", var9);
               return;
            }

            var3.put(var5, var7);
         }
      }
   }

   static {
      Client.field2.submit(() -> field1 = ApolloModsManager.loadModOptions());
   }
}
