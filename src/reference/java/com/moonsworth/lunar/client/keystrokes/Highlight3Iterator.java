package com.moonsworth.lunar.client.keystrokes;

import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerBrandEvent;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.network.server.KeystrokesType;

public class Highlight3Iterator implements EventRegistrar {
   private static final TrackedValue<KeystrokesType> field1 = TrackedValue.method2(null);

   public Highlight3Iterator() {
      this.handle(ServerChangeEvent.class, this::method5);
      this.handle(ServerBrandEvent.class, this::method1);
      this.handle(DisconnectEvent.class, this::method3);
   }

   private void method1(@Nullable ServerBrandEvent var1) {
      for (KeystrokesType var5 : KeystrokesType.values()) {
         if (var5.getBrand() != null && ThreadModuleDump3.method5(var5.getBrand())) {
            field1.set(var5);
            break;
         }
      }

      KeystrokesType var6 = field1.get();
      if (var6 != null) {
         for (Framework7Extension var8 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            method2(var6, var8);
         }
      }
   }

   @VisibleForTesting
   public static void method2(KeystrokesType var0, Framework7Extension var1) {
      ModSupport var2 = (ModSupport)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field18);
      if (var2 != null && var2.method2() && var2.method3(var0)) {
         ModEnabledState var3 = (ModEnabledState)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
         if (var3 != null) {
            boolean var4 = var3.isEnabled();
            if (var4) {
               var3.method5(var1, true);
            }

            var2.setFlipped(var4);
         }
      }

      AlertExtension var6 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var6 != null) {
         for (Framework7Extension var5 : var6.getChildren()) {
            method2(var0, var5);
         }
      }
   }

   private void method3(DisconnectEvent var1) {
      KeystrokesType var2 = field1.get();
      if (var2 != null) {
         field1.set(null);

         for (Framework7Extension var4 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            method4(var2, var4);
         }

         method6();
      }
   }

   @VisibleForTesting
   public static void method4(KeystrokesType var0, Framework7Extension var1) {
      ModSupport var2 = (ModSupport)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field18);
      if (var2 != null && var2.method2() && var2.method3(var0)) {
         ModEnabledState var3 = (ModEnabledState)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
         if (var3 != null && var3.isEnabled()) {
            boolean var4 = var2.method4();
            var3.setEnabled(false);
            if (var4) {
               var2.setFlipped(true);
               var3.setEnabled(true);
               var2.setFlipped(true);
            }
         }
      }

      AlertExtension var6 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var6 != null) {
         for (Framework7Extension var5 : var6.getChildren()) {
            method4(var0, var5);
         }
      }
   }

   private void method5(ServerChangeEvent var1) {
      if (var1.method1()) {
         method6();
      }
   }

   private static void method6() {
      ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().forEach(var0 -> {
         var0.method3(Framework.field4).ifPresent(var1xx -> var1xx.method1(var0, AlertType.SERVER, null));
         var0.method3(Framework.field14).ifPresent(var0x -> var0x.method4(var0xx -> {
            var0xx.method3(OptionTraits.field5).ifPresent(var1xx -> var1xx.method1(var0xx, AlertType.SERVER, null));
            return false;
         }));
         AlertExtension var1x = (AlertExtension)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var1x != null) {
            var1x.method2(var0x -> {
               var0x.method3(Framework.field4).ifPresent(var1xx -> var1xx.method1(var0x, AlertType.SERVER, null));
               var0x.method3(Framework.field14).ifPresent(var0xx -> var0xx.method4(var0xxx -> {
                  var0xxx.method3(OptionTraits.field5).ifPresent(var1xx -> var1xx.method1(var0xxx, AlertType.SERVER, null));
                  return false;
               }));
            });
         }
      });

      for (Framework7Extension var1 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         method7(var1);
      }
   }

   private static void method7(Framework7Extension var0) {
      var0.updateEnabled();
      AlertExtension var1 = (AlertExtension)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var1 != null) {
         var1.method2(Framework7Extension::updateEnabled);
      }
   }

   public static boolean method8(KeystrokesType var0) {
      return field1.get() == var0;
   }

   public static KeystrokesType method9() {
      return field1.get();
   }

   @TestOnly
   @VisibleForTesting
   public static void method10(KeystrokesType var0) {
      field1.set(var0);
   }
}
