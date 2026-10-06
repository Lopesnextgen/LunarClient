package com.moonsworth.lunar.client.mod.skyblock.hideothersgifts;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GiftTracker;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockHideOthersGifts extends AbstractFeature {
   private final GiftTracker field8 = (GiftTracker)this.method63(GiftTracker.class);

   public SkyblockHideOthersGifts(Skyblock skyblock1) {
      super(true);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntity.class, this::method1);
   }

   private void method1(EventRenderEntity data81) {
      if (this.field8.method2(data81.method1())) {
         data81.setCancelled(true);
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_OTHERS_GIFTS";
   }
}
