package com.moonsworth.lunar.client.mod.skyblock.hidefood;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderVanillaHud;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;

public class SkyblockHideFood extends AbstractFeature {
   public SkyblockHideFood(Skyblock skyblock1) {
      super(true);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.handle(EventRenderVanillaHud.class, this::method1);
   }

   private void method1(EventRenderVanillaHud highlightimpl21) {
      highlightimpl21.setCancelled(true);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_HIDE_FOOD";
   }
}
