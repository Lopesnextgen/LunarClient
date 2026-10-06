package com.moonsworth.lunar.client.framework.feature.coordinates;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHud;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry;
import com.moonsworth.lunar.client.util.text.TextUtils;
import lombok.Generated;

public class CoordinatesBiomeChildMod extends CoordinatesHudEntry {
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("presetBiomeColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   protected CoordinatesBiomeChildMod(CoordinatesHud coordinates1, String text2, String text3) {
      super(coordinates1, text2, () -> coordinates1.getBiome().name(), text3);
   }

   protected com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data method2(final CoordinatesHud coordinates1) {
      return new CoordinatesBiomeChildMod.Data(0.0F, 0.0F) {
         protected ToggleOptionBuilder method1(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         protected ToggleOptionBuilder method2(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         protected com.moonsworth.lunar.client.config.option.FloatOption.Data method1(com.moonsworth.lunar.client.config.option.FloatOption.Data data1x) {
            return (com.moonsworth.lunar.client.config.option.FloatOption.Data)data1x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }
      };
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.registerOptions(lightingextension231);
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13}));
   }

   public static CoordinatesBiomeChildMod method3(CoordinatesHud coordinates0, String text1, String text2) {
      final String text3 = "COORDINATES_" + TextUtils.toUpperSnakeCase(text1) + "_CHILD";
      return new CoordinatesBiomeChildMod(coordinates0, text1, text2) {
         public String getId() {
            return text3;
         }
      };
   }

   @Generated
   public ToggleOption method17() {
      return this.field13;
   }

   private class Data extends com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data {
      public Data(float value2, float value3) {
         super(CoordinatesBiomeChildMod.this, value2, value3);
      }

      protected int method24() {
         return CoordinatesBiomeChildMod.this.field13.get()
            ? ((CoordinatesHud)((ChildModBinding)CoordinatesBiomeChildMod.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1()).method14().method1()
            : super.registerOptions4();
      }
   }
}
