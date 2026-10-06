package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.SettingsManager;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class DriverSettingExtensionLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   protected static Optional<ClientOption<?>> method1(OptionCategory var0, String var1, String var2) {
      SettingsManager var3 = Client.method109().method41();

      Set var4 = switch (var0) {
         case GENERAL -> var3.method6().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case PERFORMANCE -> var3.method7().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case CONTROLS -> var3.method8().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case FEATURE -> {
            Optional var5 = Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().stream().filter(var1x -> var1x.getId().equals(var2)).findFirst();
            if (var5.isPresent()) {
               Framework5 var6 = (Framework5)((Framework7Extension)var5.get()).HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
               if (var6 == null) {
                  throw new IllegalArgumentException("Feature does not have options: " + var2);
               }

               yield var6.method2();
            } else {
               for (Framework7Extension var7 : Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
                  if (var7.method2(Framework.field5)) {
                     AlertExtension var8 = (AlertExtension)var7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
                     if (var8 != null) {
                        for (Framework7Extension var10 : var8.getChildren()) {
                           if (var10.getId().equals(var2)) {
                              Framework5 var11 = (Framework5)var10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
                              if (var11 == null) {
                                 throw new IllegalArgumentException("Feature does not have options: " + var2);
                              }

                              yield var11.method2();
                           }
                        }
                     }
                  }
               }

               throw new IllegalArgumentException("Feature not found: " + var2);
            }
         }
         case REWIND -> Client.method109().method90().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         default -> throw new IncompatibleClassChangeError();
      };
      return method2(var4, var1);
   }

   private static Optional<ClientOption<?>> method2(Collection<ClientOption<?>> var0, String var1) {
      for (ClientOption var3 : var0) {
         if (!(var3 instanceof LabelOption)) {
            if (var3.getId().equals(var1)) {
               return Optional.of(var3);
            }

            List var4 = var3.getChildren();
            if (var4 != null && !var4.isEmpty()) {
               method2(var4, var1);
            }
         }
      }

      return Optional.empty();
   }
}
