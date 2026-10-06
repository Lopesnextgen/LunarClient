package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.framework.mod.Nameplate4;
import com.moonsworth.lunar.client.framework.mod.Nameplate4Impl;
import com.moonsworth.lunar.client.framework.mod.Nameplate4Impl2;
import com.moonsworth.lunar.client.framework.mod.Nameplate4Loader;
import com.moonsworth.lunar.client.framework.mod.Nameplate4Impl.Type;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.holograms.EventFeatureToggleLegacy;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface ModEnabledState {
   @NotNull
   Optional<ClientOption<Boolean>> method1();

   void method2();

   boolean isEnabled();

   void setEnabled(boolean var1);

   boolean method3();

   @Internal
   void method4(boolean var1);

   default void method5(Framework7Extension var1, boolean var2) {
      ModSupport var3 = (ModSupport)var1.method1(Framework.field18);
      if (var3 != null && var2 && var3.method2()) {
         KeystrokesType var4 = Highlight3Iterator.method9();
         if (var4 == null || !var3.method3(var4)) {
            var3.setFlipped(!var3.method4());
            return;
         }

         if (var3.method3(var4)) {
            var3.setFlipped(!var3.method4());
         } else {
            if (var3.method4()) {
               return;
            }

            var3.setFlipped(false);
         }
      }

      Framework7 var13 = (Framework7)var1.method1(Framework.field20);
      if (var2 && var13 != null && !var13.method3()) {
         var1.updateEnabled();
      } else {
         Framework7Loader var5 = (Framework7Loader)var1.method1(Framework.field3);
         if (var5 != null) {
            Bridge.method8().method67();

            for (MixinHelper_15 var7 : var5.method7()) {
               var7.bridge$setKeyBindState(false);
            }
         }

         Framework4 var14 = (Framework4)var1.method1(Framework.field16);
         boolean var15 = false;
         if (var14 == null) {
            var15 = this.method3() != var2 || var1.method9(Framework.field8, FrameworkType.COMPLETE) != FrameworkType.COMPLETE;
         } else if (this.method3()) {
            if (var2) {
               if (!var14.<Framework7Extension>method1().isEnabled()) {
                  var15 = true;
                  var2 = false;
               }
            } else {
               var15 = true;
            }
         } else if (var2 && var14.<Framework7Extension>method1().isEnabled()) {
            var15 = true;
         }

         if (var15) {
            this.method4(var2);
            Framework10Extension var8 = (Framework10Extension)var1.method1(Framework.field12);
            if (var8 != null) {
               var8.method1(var1, var2, var3 != null && var3.method2());
            }

            var1.updateEnabled();
            var1.method3(var2);
            AlertExtension var9 = (AlertExtension)var1.method1(Framework.field5);
            if (var9 != null) {
               for (Framework7Extension var11 : var9.getChildren()) {
                  ModEnabledState var12 = (ModEnabledState)var11.method1(Framework.field6);
                  if (var12 != null && (!var2 || var12.isEnabled())) {
                     var12.method5(var11, var2);
                  }
               }

               var9.method2(Framework7Extension::updateEnabled);
            }

            boolean var16 = var2;
            ClientEventBus.method29().method12(EventFeatureToggleLegacy.class, () -> new EventFeatureToggleLegacy(var1, var16));
         }

         ThreadModuleDump63.method28(null);
      }
   }

   static ModEnabledState method6(boolean var0) {
      return new Nameplate4Loader(var0);
   }

   static ModEnabledState method7(@NotNull ClientOption<Boolean> var0) {
      return new Nameplate4(var0);
   }

   static ModEnabledState method8(@NotNull ClientOption<Boolean>... var0) {
      return new Nameplate4Impl(Type.OR, var0);
   }

   static ModEnabledState method9(boolean var0) {
      return new Nameplate4Impl2(var0);
   }

   static ModEnabledState method10(String var0, boolean var1) {
      return method11(var0, false, var1);
   }

   static ModEnabledState method11(String var0, boolean var1, boolean var2) {
      return new Nameplate4Loader(var2, ((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("enabled").method4(var2)).method5(var0, var1));
   }
}
