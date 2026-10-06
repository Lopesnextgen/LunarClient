package com.moonsworth.lunar.client.mod.skyblock.itemidlore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockItemIdLore extends AbstractFeature {
   public SkyblockItemIdLore(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderTooltipPre.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_ITEM_ID_LORE";
   }

   private void method1(EventRenderTooltipPre data21) {
      String text2 = SkyblockItemUtil.method2((ItemStackBridge)data21.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null));
      if (!text2.isEmpty()) {
         TextComponent text3 = (TextComponent)Component.text("skyblock:" + text2).color(NamedTextColor.DARK_GRAY);
         List list4 = data21.method3();
         list4.add((ClickableText)Bridge.method8().method89(text3));
         data21.method2(list4);
      }
   }
}
