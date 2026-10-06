package com.moonsworth.lunar.client.config.option;

import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.network.apollo.ModSettingApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.alert.mixin.Alert;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;

public class OptionAlertHandler<V> implements Alert2<ClientOption<V>, V> {
   @Nullable
   private Alert<V> field1 = null;

   public boolean method1(ClientOption<V> var1, AlertType var2, @Nullable V var3) {
      Object var4 = null;
      if (var3 != null) {
         var4 = var1.method24(var3);
         if (var4 == null) {
            return false;
         }
      }

      if (this.field1 == null) {
         if (var4 == null) {
            return false;
         }

         this.field1 = new Alert<>();
      }

      Object var5 = var1.get();
      if (var4 == null) {
         if (this.field1.method4()) {
            this.field1 = null;
         }
      } else {
         this.field1.method2(var2, (V)var4);
      }

      Object var6 = var1.get();
      this.method2(var1, (V)var6);
      if (!Objects.equals(var5, var6)) {
         OptionUpdateListeners var7 = (OptionUpdateListeners)var1.method1(OptionTraits.field1);
         if (var7 != null) {
            var7.forEach(var1x -> var1x.accept(var6));
         }

         return true;
      } else {
         return false;
      }
   }

   private void method2(ClientOption<V> var1, V var2) {
      if (Client.method109() != null) {
         try {
            ApolloModuleManager var3 = Client.method109().method84();
            if (var3 != null) {
               var3.<ApolloModuleHandler>method3(ModSettingModule.class).ifPresent(var2x -> ((ModSettingApolloHandler)var2x).method17().method2(var1, var2));
            }
         } catch (Throwable var4) {
            Slayer.method6("Apollo", "Apollo intercept broadcast failed", var4);
         }
      }
   }

   @Nullable
   @Override
   public AlertType method2() {
      return this.field1 == null ? null : this.field1.method5();
   }

   @Override
   public Optional<V> method3() {
      return this.field1 == null ? Optional.empty() : this.field1.method1();
   }

   public void method5(ClientOption<V> var1, @Nullable String var2) {
      if (this.field1 != null && this.field1.test(var2)) {
         this.method1(var1, AlertType.SERVER, this.field1.method6());
      }
   }

   public void method6(ClientOption<V> var1) {
      if (this.field1 != null && this.field1.method5() == AlertType.SERVER) {
         this.method1(var1, AlertType.SERVER, null);
      }
   }

   @Override
   public void method6(Predicate<@Nullable String> var1, V var2) {
      if (this.field1 == null) {
         this.field1 = new Alert<>();
      }

      this.field1.method3(var1, (V)var2);
   }

   public Alert2<ClientOption<?>, ?> method8() {
      return (Alert2<ClientOption<?>, ?>)this;
   }
}
