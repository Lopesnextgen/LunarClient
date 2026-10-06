package com.moonsworth.lunar.client.config;

import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.config.profile.importer.ExternalProfileLocator;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.MigrationContextLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType3;
import java.util.List;
import lombok.Generated;

public class GlobalOptionsSettings extends com.moonsworth.lunar.client.config.SettingsContainer implements EventRegistrar {
   private final ModifierKeybindOption field2 = (ModifierKeybindOption)OptionFactory.method18("cycleModProfiles").method31();
   private final ToggleOption field3 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("seenOverlayMigration").method4(true))
      .method31();
   private final ToggleOption field4 = (ToggleOption)OptionFactory.method7("seenImportClient").method31();

   public GlobalOptionsSettings() {
      this.handle(ScreenChangeEvent.class, var1 -> {
         this.initAndGet(var1);
         this.init(var1);
      });
   }

   private void method1(ScreenChangeEvent var1) {
      if (!(Boolean)this.field3.get()) {
         if (ThreadModuleDump63.method31(var1.method1()) instanceof FeatureSettingsScreen var3) {
            ThreadModuleDump63.method4().method40().method84().method19(var3);
            var1.setCancelled(true);
            this.field3.method10(true);
            this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         }
      }
   }

   private void method2(ScreenChangeEvent var1) {
      if (ExternalProfileLocator.method1() && !(Boolean)this.field4.get()) {
         Bridge7_8 var2 = ThreadModuleDump63.method31(var1.method1());
         if (var2 instanceof FeatureSettingsScreen) {
            com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field21, new MigrationContextLegacy(true));
            var1.setCancelled(true);
            this.field4.method10(true);
            this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         }
      }
   }

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      var1.method11(new ClientOption[]{this.field2, this.field4, this.field3});
      this.field2.method5(OptionTraits.field6, ThreadModuleDumpType3.INSTANCE);
      this.field4.method1(OptionTraits.field6, ThreadModuleDumpType3.INSTANCE);
      this.field3.method1(OptionTraits.field6, ThreadModuleDumpType3.INSTANCE);
      this.field2
         .method3(
            () -> {
               com.moonsworth.lunar.client.config.profile.ModProfileManager var0 = ThreadModuleDump63.method4().method61();
               List var1x = var0.method2();
               int var2 = var1x.indexOf(var0.method14());
               if (var2 > 0) {
                  var2--;
               } else {
                  var2 = var1x.size() - 1;
               }

               ModProfile var3 = (ModProfile)var1x.get(var2);
               var0.method4(var3);
               ThreadModuleDump63.method4()
                  .method69()
                  .method3(com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("switchedProfile", new Object[]{var3.getDisplayName()}));
            }
         );
   }

   @Override
   public String method5() {
      return "global_options.json";
   }

   @Override
   public boolean method11() {
      return true;
   }

   @Generated
   public ModifierKeybindOption method18() {
      return this.field2;
   }

   @Generated
   public ToggleOption method15() {
      return this.field3;
   }

   @Generated
   public ToggleOption method16() {
      return this.field4;
   }
}
