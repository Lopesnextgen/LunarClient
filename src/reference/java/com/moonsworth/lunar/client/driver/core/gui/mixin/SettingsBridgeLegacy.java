package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.driver.DriverSettingExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class SettingsBridgeLegacy extends DriverSettingExtensionLegacy {
   public static AbstractKeybindOption<?> field1 = null;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method107();
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @CallbackJS("setAdvancedMode")
   public static void method3(Boolean var0) {
      Client.method109().method41().method6().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(var0);
   }

   @CallbackJS("update")
   public static void method4(OptionCategory var0, String var1, String var2, String var3) {
      method2(var0, var1, var3).ifPresent(var1x -> var1x.method21(var2));
   }

   @CallbackJS("reset")
   public static void method5(OptionCategory var0, String var1, String var2) {
      method2(var0, var1, var2).ifPresent(ClientOption::reset);
   }

   @CallbackJS("invokeAction")
   public static void method6(OptionCategory var0, String var1, String var2) {
      method2(var0, var1, var2).ifPresent(var0x -> {
         OptionDisplay var1x = (OptionDisplay)var0x.method1(OptionTraits.field2);
         if (var1x != null) {
            Runnable var2x = var1x.method3();
            if (var2x != null) {
               var2x.run();
            }
         }
      });
   }

   @CallbackJS("setEditingKeybind")
   public static void method7(OptionCategory var0, String var1, String var2) {
      method2(var0, var1, var2).ifPresent(var0x -> {
         if (var0x instanceof AbstractKeybindOption) {
            field1 = (AbstractKeybindOption<?>)var0x;
         } else {
            throw new IllegalArgumentException("Option is not a keybind: " + var0x.getId());
         }
      });
   }

   @CallbackJS("stopEditingKeybind")
   public static void method8() {
      field1 = null;
   }

   @Override
   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (field1 != null) {
         if (var1 == KeyCode.KEY_ESCAPE) {
            if (var4 == 0) {
               if (field1 instanceof ModifierKeybindOption var9) {
                  var9.method9(KeyCode.KEY_NONE);
               } else if (field1 instanceof SimpleKeybindOption var10) {
                  var10.method5(KeyCode.KEY_NONE, true);
               }

               field1 = null;
            }
         } else {
            if (var4 == 1) {
               if (field1 instanceof ModifierKeybindOption var6) {
                  if (var1 != KeyCode.KEY_LSHIFT && var1 != KeyCode.KEY_LCONTROL && var1 != KeyCode.KEY_LMENU) {
                     var6.method8(new KeyCombo((var5 & 4) != 0, (var5 & 1) != 0, (var5 & 2) != 0, var1));
                     field1 = null;
                  }
               } else if (field1 instanceof SimpleKeybindOption var7) {
                  var7.method5(var1, true);
                  field1 = null;
               }
            }
         }
      }
   }
}
