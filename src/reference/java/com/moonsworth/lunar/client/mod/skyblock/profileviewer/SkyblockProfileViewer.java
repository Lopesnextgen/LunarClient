package com.moonsworth.lunar.client.mod.skyblock.profileviewer;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.command.PlayerArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.DriverContext;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockProfileViewer extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("useLunarAlias").method31();

   public SkyblockProfileViewer(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method14(new ClientCommand(method1("pv")) {
         public boolean isEnabled() {
            return !(Boolean)SkyblockProfileViewer.this.field8.get();
         }
      });
      this.method14(new ClientCommand(method1("lcpv")) {
         public boolean isEnabled() {
            return (Boolean)SkyblockProfileViewer.this.field8.get();
         }
      });
   }

   private static LiteralCommandNode method1(String text0) {
      return LiteralCommandNode.method1(text0)
         .method3(arg0x -> method2(Ref.method4().method31().getName()))
         .method2(ArgumentCommandNode.method1("name", PlayerArgumentParser.field2).method8(arg0x -> method2(arg0x.getString("name"))));
   }

   private static void method2(String text0) {
      Ref.method3()
         .bridge$schedule(() -> DriverViewportLegacy.method50().method17(DriverRouteRegistry.field20, new SkyblockProfileViewer.Data(text0)));
   }

   public String getId() {
      return "SKYBLOCK_PROFILE_VIEWER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
   }

   private static class Data extends DriverContext {
      private final String userName;

      protected void method2(JsonObject json1) {
         json1.addProperty("userName", this.userName);
      }

      @Generated
      public Data(String text1) {
         this.userName = text1;
      }
   }
}
