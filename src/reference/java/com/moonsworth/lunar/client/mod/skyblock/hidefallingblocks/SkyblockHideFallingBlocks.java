package com.moonsworth.lunar.client.mod.skyblock.hidefallingblocks;

import com.moonsworth.lunar.bridge.EntityFallingBlockBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockHideFallingBlocks extends AbstractFeature {
   private boolean field8;

   public SkyblockHideFallingBlocks(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.RENDER));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntity.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_HIDE_FALLING_BLOCKS";
   }

   private void method1(EventRenderEntity data81) {
      if (data81.method1() instanceof EntityFallingBlockBridge) {
         data81.setCancelled(true);
      }
   }
}
