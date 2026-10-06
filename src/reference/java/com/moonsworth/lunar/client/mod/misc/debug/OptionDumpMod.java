package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.config.SettingsContainer;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.Calculator2Type;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;

public class OptionDumpMod extends AbstractFeature {
   public OptionDumpMod() {
      super(false);
   }

   public String getId() {
      return "OPTION_DUMP_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new OptionProvider[]{
            OptionFactory.method14("dumpAllOptionManagerStructures").method4(this::method13).method5(200.0F),
            OptionFactory.method14("dumpAllFeatureStructures").method4(this::method14).method5(200.0F)
         }
      );
   }

   private void method13() {
      Path path1 = Paths.get("./optionManagerStructure_dump.txt");

      try (FileWriter filewriter2 = new FileWriter(path1.toFile())) {
         for (Entry entry4 : Ref.method4().method41().IORHHHROCRRHORHRCHCCHHIHICCRCO().entrySet()) {
            int index5 = 0;
            int index6 = 0;
            int index7 = 0;
            int index8 = 0;

            for (ClientOption lightingextension10 : ((SettingsContainer)entry4.getValue()).method13()) {
               if (!lightingextension10.getId().isEmpty()) {
                  if (lightingextension10 instanceof LabelOption) {
                     index6++;
                  } else if (lightingextension10 instanceof ColorOption) {
                     index7++;
                  } else if (lightingextension10.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7)) {
                     index5++;
                  }

                  index8++;
               }
            }

            filewriter2.write(
               ((Calculator2Type)entry4.getKey()).name() + "(categories: " + index6 + ", options: " + index8 + ", sliders: " + index5 + ", colors: " + index7 + ")\n"
            );

            for (ClientOption lightingextension15 : ((SettingsContainer)entry4.getValue()).method10()) {
               this.method6(filewriter2, 1, lightingextension15);
            }
         }
      } catch (IOException exception13) {
         System.out.println("An error occurred while writing to the file: " + exception13.getMessage());
      }
   }

   private void method14() {
      Path path1 = Paths.get("./featureStructure_dump.txt");

      try (FileWriter filewriter2 = new FileWriter(path1.toFile())) {
         for (Framework7Extension framework7extension4 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            this.method5(filewriter2, 0, framework7extension4);
         }
      } catch (IOException exception7) {
         System.out.println("An error occurred while writing to the file: " + exception7.getMessage());
      }
   }

   private void method5(FileWriter filewriter1, int number2, Framework7Extension framework7extension3) {
      ModChildren alertextension4 = (ModChildren)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      OptionContainer framework55 = (OptionContainer)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
      int index6 = 0;
      int index7 = 0;
      int index8 = 0;
      int index9 = 0;
      if (framework55 != null) {
         for (ClientOption lightingextension11 : framework55.method2()) {
            if (!lightingextension11.getId().isEmpty()) {
               if (lightingextension11 instanceof LabelOption) {
                  index7++;
               } else if (lightingextension11 instanceof ColorOption) {
                  index8++;
               } else if (lightingextension11.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7)) {
                  index6++;
               }

               index9++;
            }
         }
      }

      filewriter1.write(
         "  ".repeat(number2)
            + framework7extension3.getId()
            + " (children: "
            + (alertextension4 != null ? alertextension4.getChildren().size() : 0)
            + ", categories: "
            + index7
            + ", options: "
            + index9
            + ", sliders: "
            + index6
            + ", colors: "
            + index8
            + ")\n"
      );
      if (alertextension4 != null) {
         for (Framework7Extension framework7extension14 : alertextension4.getChildren()) {
            this.method5(filewriter1, number2 + 2, framework7extension14);
         }
      }

      if (framework55 != null) {
         for (ClientOption lightingextension15 : framework55.method1()) {
            if (!lightingextension15.getId().isEmpty()) {
               this.method6(filewriter1, number2 + 1, lightingextension15);
            }
         }
      }
   }

   private void method6(FileWriter filewriter1, int number2, ClientOption<?> lightingextension3) {
      filewriter1.write(
         "  ".repeat(number2)
            + "- "
            + lightingextension3.getId()
            + (lightingextension3 instanceof LabelOption ? " Category" : (lightingextension3.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7) ? " Slider" : ""))
            + "\n"
      );

      for (ClientOption lightingextension5 : lightingextension3.getChildren()) {
         this.method6(filewriter1, number2 + 1, lightingextension5);
      }
   }
}
