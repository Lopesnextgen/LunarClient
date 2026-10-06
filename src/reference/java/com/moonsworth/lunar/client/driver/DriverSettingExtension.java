package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.SettingsManager;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class DriverSettingExtension implements DriverGuiExtension, GuiIterator.Extension {
   public DriverSettingExtension() {
   }

   protected static Optional<ClientOption<?>> method1(OptionCategory lightingtype20, String text1, String text2) {
      SettingsManager holograms63 = Client.method109().method41();

      Set set4 = switch (lightingtype20) {
         case GENERAL -> holograms63.method6().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case PERFORMANCE -> holograms63.method7().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case CONTROLS -> holograms63.method8().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         case FEATURE -> {
            Optional optional5 = Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().stream().filter(arg1x -> arg1x.getId().equals(text2)).findFirst();
            if (optional5.isPresent()) {
               OptionContainer framework56 = (OptionContainer)((Framework7Extension)optional5.get()).HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
               if (framework56 == null) {
                  throw new IllegalArgumentException("Feature does not have options: " + text2);
               }

               yield framework56.method2();
            } else {
               for (Framework7Extension framework7extension7 : Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
                  if (framework7extension7.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field5)) {
                     ModChildren alertextension8 = (ModChildren)framework7extension7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
                     if (alertextension8 != null) {
                        for (Framework7Extension framework7extension10 : alertextension8.getChildren()) {
                           if (framework7extension10.getId().equals(text2)) {
                              OptionContainer framework511 = (OptionContainer)framework7extension10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
                              if (framework511 == null) {
                                 throw new IllegalArgumentException("Feature does not have options: " + text2);
                              }

                              yield framework511.method2();
                           }
                        }
                     }
                  }
               }

               throw new IllegalArgumentException("Feature not found: " + text2);
            }
         }
         case REWIND -> Client.method109().method90().IIORHHIRHIORHRCCCOICCRCHRRCCRH();
         default -> throw new IncompatibleClassChangeError();
      };
      return method2(set4, text1);
   }

   private static Optional<ClientOption<?>> method2(Collection<ClientOption<?>> list0, String text1) {
      for (ClientOption lightingextension3 : list0) {
         if (!(lightingextension3 instanceof LabelOption)) {
            if (lightingextension3.getId().equals(text1)) {
               return Optional.of(lightingextension3);
            }

            List list4 = lightingextension3.getChildren();
            if (list4 != null && !list4.isEmpty()) {
               method2(list4, text1);
            }
         }
      }

      return Optional.empty();
   }
}
