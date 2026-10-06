package com.moonsworth.lunar.client.mod.skyblock.custommenuclick;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCustomMenuClick extends AbstractFeature {
   public SkyblockCustomMenuClick(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderSlot.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_CUSTOM_MENU_CLICK";
   }

   private void method1(EventRenderSlot highlightimpl51) {
      if (highlightimpl51.method5() != null) {
         ItemStackBridge bridgeextension_42 = highlightimpl51.method5().bridge$getItemStack();
         if ("SKYBLOCK_MENU".equals(SkyblockItemUtil.method2(bridgeextension_42))) {
            highlightimpl51.cancel();
            Ref.method7().bridge$sendCommand("/sbmenu");
         }
      }
   }
}
