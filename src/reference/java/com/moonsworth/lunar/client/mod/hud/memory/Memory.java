package com.moonsworth.lunar.client.mod.hud.memory;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Memory extends AbstractFeature {
   private final EnumOption<Memory.Type> displayMode = (EnumOption<Memory.Type>)OptionFactory.method10("displayMode", Memory.Type.PERCENTAGE)
      .method31();
   private final ToggleOption colorBasedOnUsage = (ToggleOption)OptionFactory.method7("colorBasedOnUsage").method31();
   private final ColorOption lowMemColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lowMemColor"
         )
         .method4(-65536))
      .method31();
   private final ColorOption medMemColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "medMemColor"
         )
         .method4(-256))
      .method31();
   private final ColorOption highMemColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "highMemColor"
         )
         .method4(-16711936))
      .method31();

   public Memory() {
      super(false);
      this.getConditions(ModTraits.field1, new Memory.Data());
   }

   public String getId() {
      return "MEMORY";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> arg1x.method9(new ClientOption[]{this.displayMode}));
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.colorBasedOnUsage, arg1xx -> arg1xx.method9(new ClientOption[]{this.lowMemColor, this.medMemColor, this.highMemColor})
         )
      );
   }

   private class Data extends TypedHudRenderer<String> {
      private int memoryUsage;

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      protected com.moonsworth.lunar.client.config.option.ColorOption.Data getText(com.moonsworth.lunar.client.config.option.ColorOption.Data data1) {
         return (com.moonsworth.lunar.client.config.option.ColorOption.Data)data1.ORICHRORRORHORHOIHCRHOORCRRHOI(Memory.this.colorBasedOnUsage::get);
      }

      public HudSize getSize() {
         return HudSize.method1(10, 18, 22, 46, 56, 62);
      }

      @Nullable
      public String getText(boolean flag1) {
         Runtime runtime2 = Runtime.getRuntime();
         long number3 = runtime2.totalMemory() - runtime2.freeMemory();
         long number5 = runtime2.maxMemory();
         this.memoryUsage = (Integer)Memory.this.HRICOROOOCCOCOROCRHHCRRIRCOICO("memory", (int)(number3 * 100L / number5));

         return Memory.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("memory", new Object[]{switch ((Memory.Type)Memory.this.displayMode.get()) {
            case PERCENTAGE -> this.memoryUsage + "%";
            case MEGABYTES -> number3 / 1048576L + "/" + number5 / 1048576L + " MB";
            case GIGABYTES -> String.format("%.2f/%.2f GB", number3 / 1.0737418E9F, number5 / 1.0737418E9F);
         }});
      }

      protected void renderText(MixinHelper_4 mixinhelper_41, TypedHudRenderer<String> mixincore82, String text3, float value4, float value5, boolean flag6, boolean flag7, boolean flag8) {
         ColorOption lightingextension42229;
         if ((Boolean)Memory.this.colorBasedOnUsage.get()) {
            if (this.memoryUsage >= 75) {
               lightingextension42229 = Memory.this.lowMemColor;
            } else if (this.memoryUsage >= 50) {
               lightingextension42229 = Memory.this.medMemColor;
            } else {
               lightingextension42229 = Memory.this.highMemColor;
            }
         } else {
            lightingextension42229 = this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR;
         }

         lightingextension42229.method43(mixinhelper_41, text3, value4, value5, flag7, flag6 ? this.OOOCCCRICCHOORCCRHHRHHCOOCORRC : null);
      }

      public HudConditionSet getConditions() {
         return HudConditionSet.method5().method1(false).method8();
      }
   }

   private enum Type implements OptionEnumValue {
      PERCENTAGE("percentage"),
      MEGABYTES("megabytes"),
      GIGABYTES("gigabytes");

      private final String id;

      public String id() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
