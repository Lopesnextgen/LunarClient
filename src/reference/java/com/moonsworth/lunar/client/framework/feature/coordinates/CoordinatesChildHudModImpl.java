package com.moonsworth.lunar.client.framework.feature.coordinates;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.hud.coordinates.Coordinates;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import lombok.Generated;

public class CoordinatesChildHudModImpl extends CoordinatesHudEntry {
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("presetBiomeColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   protected CoordinatesChildHudModImpl(Coordinates var1, String var2, String var3) {
      super(var1, var2, () -> var1.getBiome().name(), var3);
   }

   protected com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data method2(final Coordinates var1) {
      return new CoordinatesChildHudModImpl.Data(0.0F, 0.0F) {
         protected ToggleOptionBuilder method1(ToggleOptionBuilder var1x) {
            return (ToggleOptionBuilder)var1x.ORICHRORRORHORHOIHCRHOORCRRHOI(var1::moveChildrenTogether);
         }

         protected ToggleOptionBuilder method2(ToggleOptionBuilder var1x) {
            return (ToggleOptionBuilder)var1x.ORICHRORRORHORHOIHCRHOORCRRHOI(var1::moveChildrenTogether);
         }

         protected com.moonsworth.lunar.client.config.option.FloatOption.Data method1(com.moonsworth.lunar.client.config.option.FloatOption.Data var1x) {
            return (com.moonsworth.lunar.client.config.option.FloatOption.Data)var1x.ORICHRORRORHORHOIHCRHOORCRRHOI(var1::moveChildrenTogether);
         }
      };
   }

   public void method2(RootSettingsAssembler var1) {
      super.registerOptions(var1);
      var1.method2(SettingsPage.COLOR, var1x -> var1x.method9(new ClientOption[]{this.field13}));
   }

   public static CoordinatesChildHudModImpl method3(Coordinates var0, String var1, String var2) {
      final String var3 = "COORDINATES_" + ThreadModuleDump46.method5(var1) + "_CHILD";
      return new CoordinatesChildHudModImpl(var0, var1, var2) {
         public String getId() {
            return var3;
         }
      };
   }

   @Generated
   public ToggleOption method17() {
      return this.field13;
   }

   private class Data extends com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data {
      public Data(float var2, float var3) {
         super(CoordinatesChildHudModImpl.this, var2, var3);
      }

      protected int method24() {
         return CoordinatesChildHudModImpl.this.field13.get()
            ? ((Coordinates)((Framework4)CoordinatesChildHudModImpl.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1()).method14().method1()
            : super.registerOptions4();
      }
   }
}
