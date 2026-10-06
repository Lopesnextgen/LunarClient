package com.moonsworth.lunar.client.mod.misc.packorganizer;

import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import lombok.Generated;

public class PackOrganizer extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showIncompatible").method4(true))
      .method31();

   public PackOrganizer() {
      super(true);
   }

   public String getId() {
      return "PACK_ORGANIZER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field8})).method6(1);
   }

   public boolean method7() {
      return false;
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }
}
