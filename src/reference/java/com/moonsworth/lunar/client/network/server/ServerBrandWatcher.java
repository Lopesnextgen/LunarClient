package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerBrand;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.network.server.KeystrokesType;

public class ServerBrandWatcher implements EventBusAccess {
   private static final TrackedValue<KeystrokesType> field1 = TrackedValue.method2(null);

   public ServerBrandWatcher() {
      this.handle(EventServerChange.class, this::method5);
      this.handle(EventServerBrand.class, this::method1);
      this.handle(EventDisconnect.class, this::method3);
   }

   private void method1(@Nullable EventServerBrand highlightimpl121) {
      for (KeystrokesType keystrokestype5 : KeystrokesType.values()) {
         if (keystrokestype5.getBrand() != null && ServerUtils.isClientBrand(keystrokestype5.getBrand())) {
            field1.set(keystrokestype5);
            break;
         }
      }

      KeystrokesType keystrokestype6 = (KeystrokesType)field1.get();
      if (keystrokestype6 != null) {
         for (Framework7Extension framework7extension8 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            method2(keystrokestype6, framework7extension8);
         }
      }
   }

   @VisibleForTesting
   public static void method2(KeystrokesType keystrokestype0, Framework7Extension framework7extension1) {
      ModSupport framework132 = (ModSupport)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field18);
      if (framework132 != null && framework132.method2() && framework132.method3(keystrokestype0)) {
         ModEnabledState framework33 = (ModEnabledState)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
         if (framework33 != null) {
            boolean flag4 = framework33.isEnabled();
            if (flag4) {
               framework33.method5(framework7extension1, true);
            }

            framework132.setFlipped(flag4);
         }
      }

      ModChildren alertextension6 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension6 != null) {
         for (Framework7Extension framework7extension5 : alertextension6.getChildren()) {
            method2(keystrokestype0, framework7extension5);
         }
      }
   }

   private void method3(EventDisconnect highlightimpl111) {
      KeystrokesType keystrokestype2 = (KeystrokesType)field1.get();
      if (keystrokestype2 != null) {
         field1.set(null);

         for (Framework7Extension framework7extension4 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            method4(keystrokestype2, framework7extension4);
         }

         method6();
      }
   }

   @VisibleForTesting
   public static void method4(KeystrokesType keystrokestype0, Framework7Extension framework7extension1) {
      ModSupport framework132 = (ModSupport)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field18);
      if (framework132 != null && framework132.method2() && framework132.method3(keystrokestype0)) {
         ModEnabledState framework33 = (ModEnabledState)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
         if (framework33 != null && framework33.isEnabled()) {
            boolean flag4 = framework132.method4();
            framework33.setEnabled(false);
            if (flag4) {
               framework132.setFlipped(true);
               framework33.setEnabled(true);
               framework132.setFlipped(true);
            }
         }
      }

      ModChildren alertextension6 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension6 != null) {
         for (Framework7Extension framework7extension5 : alertextension6.getChildren()) {
            method4(keystrokestype0, framework7extension5);
         }
      }
   }

   private void method5(EventServerChange highlightimpl101) {
      if (highlightimpl101.method1()) {
         method6();
      }
   }

   private static void method6() {
      Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().forEach(arg0 -> {
         arg0.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field4).ifPresent(arg1xx -> arg1xx.method1(arg0, OverrideSource.SERVER, null));
         arg0.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field14).ifPresent(arg0x -> arg0x.method4(arg0xx -> {
            arg0xx.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).ifPresent(arg1xx -> arg1xx.method1(arg0xx, OverrideSource.SERVER, null));
            return false;
         }));
         ModChildren alertextension1x = (ModChildren)arg0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
         if (alertextension1x != null) {
            alertextension1x.method2(arg0x -> {
               arg0x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field4).ifPresent(arg1xx -> arg1xx.method1(arg0x, OverrideSource.SERVER, null));
               arg0x.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field14).ifPresent(arg0xx -> arg0xx.method4(arg0xxx -> {
                  arg0xxx.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).ifPresent(arg1xx -> arg1xx.method1(arg0xxx, OverrideSource.SERVER, null));
                  return false;
               }));
            });
         }
      });

      for (Framework7Extension framework7extension1 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         method7(framework7extension1);
      }
   }

   private static void method7(Framework7Extension framework7extension0) {
      framework7extension0.updateEnabled();
      ModChildren alertextension1 = (ModChildren)framework7extension0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension1 != null) {
         alertextension1.method2(Framework7Extension::updateEnabled);
      }
   }

   public static boolean method8(KeystrokesType keystrokestype0) {
      return field1.get() == keystrokestype0;
   }

   public static KeystrokesType method9() {
      return (KeystrokesType)field1.get();
   }

   @TestOnly
   @VisibleForTesting
   public static void method10(KeystrokesType keystrokestype0) {
      field1.set(keystrokestype0);
   }
}
