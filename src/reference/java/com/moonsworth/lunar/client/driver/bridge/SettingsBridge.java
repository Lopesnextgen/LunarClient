package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.driver.DriverSettingExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.Nullable;

public class SettingsBridge extends DriverSettingExtension {
   public static AbstractKeybindOption<?> field1 = null;

   public SettingsBridge() {
   }

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
   public static void method3(Boolean flag0) {
      Client.method109().method41().method6().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(flag0);
   }

   @CallbackJS("update")
   public static void method4(OptionCategory lightingtype20, String text1, String text2, String text3) {
      method2(lightingtype20, text1, text3).ifPresent(arg1x -> arg1x.method21(text2));
   }

   @CallbackJS("reset")
   public static void method5(OptionCategory lightingtype20, String text1, String text2) {
      method2(lightingtype20, text1, text2).ifPresent(ClientOption::reset);
   }

   @CallbackJS("invokeAction")
   public static void method6(OptionCategory lightingtype20, String text1, String text2) {
      method2(lightingtype20, text1, text2).ifPresent(arg0x -> {
         OptionDisplay nameplate41x = (OptionDisplay)arg0x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field2);
         if (nameplate41x != null) {
            Runnable runnable2x = nameplate41x.method3();
            if (runnable2x != null) {
               runnable2x.run();
            }
         }
      });
   }

   @CallbackJS("setEditingKeybind")
   public static void method7(OptionCategory lightingtype20, String text1, String text2) {
      method2(lightingtype20, text1, text2).ifPresent(arg0x -> {
         if (arg0x instanceof AbstractKeybindOption) {
            field1 = (AbstractKeybindOption<?>)arg0x;
         } else {
            throw new IllegalArgumentException("Option is not a keybind: " + arg0x.getId());
         }
      });
   }

   @CallbackJS("stopEditingKeybind")
   public static void method8() {
      field1 = null;
   }

   @Override
   public void method2(KeyCode bridgetype_81, int number2, int number3, int number4, int number5) {
      if (field1 != null) {
         if (bridgetype_81 == KeyCode.KEY_ESCAPE) {
            if (number4 == 0) {
               if (field1 instanceof ModifierKeybindOption lightingextension491339) {
                  lightingextension491339.method9(KeyCode.KEY_NONE);
               } else if (field1 instanceof SimpleKeybindOption lightingextension4913210) {
                  lightingextension4913210.method5(KeyCode.KEY_NONE, true);
               }

               field1 = null;
            }
         } else {
            if (number4 == 1) {
               if (field1 instanceof ModifierKeybindOption lightingextension491336) {
                  if (bridgetype_81 != KeyCode.KEY_LSHIFT && bridgetype_81 != KeyCode.KEY_LCONTROL && bridgetype_81 != KeyCode.KEY_LMENU) {
                     lightingextension491336.method8(new KeyBind((number5 & 4) != 0, (number5 & 1) != 0, (number5 & 2) != 0, bridgetype_81));
                     field1 = null;
                  }
               } else if (field1 instanceof SimpleKeybindOption lightingextension491327) {
                  lightingextension491327.method5(bridgetype_81, true);
                  field1 = null;
               }
            }
         }
      }
   }
}
