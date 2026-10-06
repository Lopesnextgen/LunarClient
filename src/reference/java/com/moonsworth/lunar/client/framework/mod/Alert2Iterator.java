package com.moonsworth.lunar.client.framework.mod;

import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ModSettingApolloHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.alert.mixin.Alert;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.EnumMap;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Alert2Iterator implements Alert2<Framework7Extension, Boolean> {
   @Nullable
   private Boolean field1 = null;
   private EnumMap<AlertType, Alert<Boolean>> field2;

   public boolean method1(Framework7Extension var1, AlertType var2, @Nullable Boolean var3) {
      ModEnabledState var4;
      if (this.field2 == null) {
         if (var3 == null) {
            return false;
         }

         var4 = (ModEnabledState)var1.method1(Framework.field6);
         if (var4 == null) {
            return false;
         }

         this.field2 = new EnumMap<>(AlertType.class);
      } else {
         var4 = (ModEnabledState)var1.method1(Framework.field6);
         if (var4 == null) {
            return false;
         }
      }

      Boolean var5 = this.method3().orElse(null);
      boolean var6 = var4.method1().<Boolean>map(ClientOption::get).orElse(true);
      if (var3 == null) {
         Alert var7 = this.field2.get(var2);
         if (var7 == null) {
            return false;
         }

         if (var7.method4()) {
            this.field2.remove(var2);
            if (this.field2.isEmpty()) {
               this.field2 = null;
            }
         }
      } else {
         if (this.field1 == null) {
            this.field1 = var6;
         }

         this.field2.computeIfAbsent(var2, var0 -> new Alert()).method2(var2, var3);
      }

      if (ThreadModuleDump63.method4() != null && var1 instanceof AbstractFeature var11) {
         try {
            var4.method1().ifPresent(var3x -> Client.method109().method84().method3(ModSettingModule.class).ifPresent(var4x -> {
               Boolean var5x = var3 != null ? var3 : var6;
               ((ModSettingApolloHandler)var4x).method17().method3(var3x, var11.getId(), var5x);
            }));
         } catch (Throwable var10) {
            Slayer.method6("Apollo", "Apollo broadcast failed", new Object[]{var10});
         }
      }

      Boolean var12 = this.method3().orElse(null);
      if (var5 != var12) {
         ClientOption var8 = var4.method1().orElse(null);
         if (var8 == null) {
            return false;
         }

         Alert2 var9 = (Alert2)var8.method1(com.moonsworth.lunar.client.lighting.nameplate.Nameplate.field5);
         if (var9 != null) {
            var9.method1(var8, var2, var12);
         }

         if (var12 == null && this.field1 != null) {
            var4.setEnabled(this.field1);
            this.field1 = null;
         }

         this.method2(var1);
         return true;
      } else {
         return false;
      }
   }

   private void method2(Framework7Extension var1) {
      var1.updateEnabled();
      AlertExtension var2 = (AlertExtension)var1.method1(Framework.field5);
      if (var2 != null) {
         var2.method2(Framework7Extension::updateEnabled);
      }
   }

   @Nullable
   public AlertType method2() {
      Alert var1 = this.method8();
      return var1 == null ? null : var1.method5();
   }

   public Optional<Boolean> method3() {
      Alert var1 = this.method8();
      return var1 == null ? Optional.empty() : var1.method1();
   }

   public void method5(Framework7Extension var1, @Nullable String var2) {
      AlertExtension var3 = (AlertExtension)var1.method1(Framework.field5);
      if (var3 != null) {
         for (Framework7Extension var5 : var3.getChildren()) {
            Alert2 var6 = (Alert2)var5.method1(Framework.field4);
            if (var6 != null) {
               var6.method4(var5, var2);
            }
         }
      }

      Framework5 var8 = (Framework5)var1.method1(Framework.field14);
      if (var8 != null) {
         for (ClientOption var11 : var8.method2()) {
            Alert2 var7 = (Alert2)var11.method1(com.moonsworth.lunar.client.lighting.nameplate.Nameplate.field5);
            if (var7 != null) {
               var7.method4(var11, var2);
            }
         }
      }

      if (this.field2 != null) {
         Alert var10 = this.field2.get(AlertType.SERVER);
         if (var10 != null && var10.test(var2)) {
            this.method1(var1, AlertType.SERVER, (Boolean)var10.method6());
         }
      }
   }

   public void method6(Framework7Extension var1) {
      AlertExtension var2 = (AlertExtension)var1.method1(Framework.field5);
      if (var2 != null) {
         for (Framework7Extension var4 : var2.getChildren()) {
            Alert2 var5 = (Alert2)var4.method1(Framework.field4);
            if (var5 != null) {
               var5.method5(var4);
            }
         }
      }

      Framework5 var7 = (Framework5)var1.method1(Framework.field14);
      if (var7 != null) {
         for (ClientOption var9 : var7.method2()) {
            Alert2 var6 = (Alert2)var9.method1(com.moonsworth.lunar.client.lighting.nameplate.Nameplate.field5);
            if (var6 != null) {
               var6.method5(var9);
            }
         }
      }

      if (this.field2 != null && this.field2.containsKey(AlertType.SERVER)) {
         this.method1(var1, AlertType.SERVER, null);
      }
   }

   public void method7(Predicate<String> var1, Boolean var2) {
      if (this.field2 == null) {
         this.field2 = new EnumMap<>(AlertType.class);
      }

      Alert var3 = this.field2.computeIfAbsent(AlertType.SERVER, var0 -> new Alert());
      var3.method3(var1, var2);
   }

   @Nullable
   private Alert<Boolean> method8() {
      if (this.field2 != null && !this.field2.isEmpty()) {
         for (Entry var2 : this.field2.entrySet()) {
            Alert var3 = (Alert)var2.getValue();
            Boolean var4 = (Boolean)var3.method1().orElse(null);
            if (var4 != null) {
               return var3;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Nullable
   @Generated
   public Boolean method9() {
      return this.field1;
   }
}
