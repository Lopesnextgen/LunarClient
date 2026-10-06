package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;

public class SkyblockDebugChime extends AbstractFeature {
   public SkyblockDebugChime(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_CHIME";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{OptionFactory.method14("debugPlayChime").method4(IslandUtils::playSound).method31()}
      );
   }
}
