package com.moonsworth.lunar.client.config;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.Calculator2Type;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map;
import lombok.Generated;

public class SettingsManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<Calculator2Type, com.moonsworth.lunar.client.config.SettingsContainer> {
   private GeneralSettings field2;
   private PerformanceSettings field3;
   private ControlsSettings field4;
   private InternalSettings field5;

   @Override
   protected Map<Calculator2Type, com.moonsworth.lunar.client.config.SettingsContainer> method3() {
      this.field5 = (InternalSettings)new InternalSettings().IHRRHCCHCIRHOICCHRRRCRCCCCHOCI();
      return ImmutableMap.of(
         Calculator2Type.GENERAL,
         this.field2 = (GeneralSettings)new GeneralSettings().IHRRHCCHCIRHOICCHRRRCRCCCCHOCI(),
         Calculator2Type.PERFORMANCE,
         this.field3 = (PerformanceSettings)new PerformanceSettings().IHRRHCCHCIRHOICCHRRRCRCCCCHOCI(),
         Calculator2Type.CONTROLS,
         this.field4 = (ControlsSettings)new ControlsSettings().IHRRHCCHCIRHOICCHRRRCRCCCCHOCI()
      );
   }

   @Override
   public void init() {
      this.method2().forEach((var0, var1) -> {
         var1.init();

         for (ClientOption var3 : var1.method13()) {
            OptionDataProvider var4 = (OptionDataProvider)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
            if (var4 != null && var4.method2()) {
               var4.method4(var0.getScope(), null);
               var3.method8(var2 -> var4.method2(var0.getScope()));
            }
         }
      });
      this.field5.init();
      this.method4();
   }

   @Override
   public void close() {
      for (com.moonsworth.lunar.client.config.SettingsContainer var2 : this.method2().values()) {
         var2.close();
      }

      this.field5.close();
   }

   public <T> void method2(ClientOption<T> var1) {
      com.moonsworth.lunar.client.config.profile.ModProfileManager var2 = ThreadModuleDump63.method4().method61();
      if (var2 == null || !var2.method16()) {
         for (com.moonsworth.lunar.client.config.SettingsContainer var4 : this.method2().values()) {
            if (var4.method13().contains(var1)) {
               var4.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
               return;
            }
         }

         for (Framework7Extension var6 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (this.method3(var6, var1)) {
               return;
            }
         }
      }
   }

   private <T> boolean method3(Framework7Extension var1, ClientOption<T> var2) {
      Framework5 var3 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var3 != null && var3.method2().contains(var2)) {
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         return true;
      }

      AlertExtension var4 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var4 != null) {
         for (Framework7Extension var6 : var4.getChildren()) {
            if (this.method3(var6, var2)) {
               return true;
            }
         }
      }

      return false;
   }

   private void method4() {
      this.method5().method3("general", this.field2);
      this.method5().method3("performance", this.field3);
      this.method5().method3("controls", this.field4);
   }

   public GuiIterator method5() {
      return Client.method109().method107();
   }

   @Generated
   public GeneralSettings method6() {
      return this.field2;
   }

   @Generated
   public PerformanceSettings method7() {
      return this.field3;
   }

   @Generated
   public ControlsSettings method8() {
      return this.field4;
   }

   @Generated
   public InternalSettings method9() {
      return this.field5;
   }
}
