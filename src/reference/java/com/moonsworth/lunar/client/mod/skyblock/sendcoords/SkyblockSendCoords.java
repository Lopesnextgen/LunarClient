package com.moonsworth.lunar.client.mod.skyblock.sendcoords;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSendCoords extends AbstractFeature {
   private final SimpleKeybindOption field8 = (SimpleKeybindOption)((Data)OptionFactory.method17("sendCoordsKeybind").method18(this))
      .method31();

   public SkyblockSendCoords(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"Diana", "Griffin", "Burrow"})
         .method11(this);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SEND_COORDS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      this.field8.method3(this::method13);
   }

   private void method13() {
      Ref.method7().bridge$sendCommand("/sendcoords");
   }
}
