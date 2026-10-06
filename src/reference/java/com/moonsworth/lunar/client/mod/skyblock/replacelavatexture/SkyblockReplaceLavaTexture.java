package com.moonsworth.lunar.client.mod.skyblock.replacelavatexture;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SkyblockReplaceLavaTexture extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("replaceLavaWithWaterEverywhere")
      .method31();

   public SkyblockReplaceLavaTexture(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.field8.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.reload());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   public String getId() {
      return "SKYBLOCK_REPLACE_LAVA_TEXTURE";
   }

   public void method3(boolean flag1) {
      super.method3(flag1);
      this.reload();
   }

   private void reload() {
      this.mc.bridge$getLevelRenderer().bridge$reloadChunks();
   }

   public boolean method13() {
      if (this.method14()) {
         return true;
      }

      if (!this.ROOICICIRCHHHICRIIHCHCHHROHRHI()) {
         return false;
      }

      if ((Boolean)this.field8.get()) {
         return true;
      }

      SkyblockIsland gui2extension31 = IslandUtils.getIsland();
      return gui2extension31 == SkyblockIsland.CRIMSON_ISLES || gui2extension31 == SkyblockIsland.KUUDRA;
   }

   private boolean method14() {
      SkyblockDebugMod skyblockdebugmod1 = Ref.method4().method40().method77();
      return skyblockdebugmod1 == null ? false : skyblockdebugmod1.method13().method13();
   }

   @Generated
   public ToggleOption method15() {
      return this.field8;
   }
}
