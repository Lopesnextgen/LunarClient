package com.moonsworth.lunar.client.mod.skyblock.dungeonquality;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.List;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonQuality extends AbstractFeature {
   public SkyblockDungeonQuality(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderTooltipPre.class, this::method1);
   }

   private void method1(EventRenderTooltipPre data21) {
      Optional optional2 = data21.HHCOCHOIIIOOROCORRRRORORIOOHRC();
      if (!optional2.isEmpty()) {
         CompoundTagComponent mixinhelper_103 = (CompoundTagComponent)((ItemStackBridge)optional2.get()).bridge$getDataComponent(DataComponentTypes.field1);
         if (mixinhelper_103 != null) {
            CompoundTagBridge bridge_574 = mixinhelper_103.bridge$getData();
            if (bridge_574 != null) {
               if (bridge_574.bridge$contains("item_tier", 3) && bridge_574.bridge$contains("baseStatBoostPercentage", 3)) {
                  int number5 = bridge_574.bridge$getInteger("item_tier");
                  int number6 = bridge_574.bridge$getInteger("baseStatBoostPercentage");
                  Skyblock skyblock7 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                  TextComponent text8 = (TextComponent)Component.text(skyblock7.method226("dungeonQualityFloor", new Object[0]) + ": ", NamedTextColor.GRAY)
                     .append(Component.text(number5, NamedTextColor.RED));
                  TextComponent text9 = (TextComponent)Component.text(skyblock7.method226("dungeonQualityStatBoost", new Object[0]) + ": ", NamedTextColor.GRAY)
                     .append(Component.text("+" + number6 + "%", NamedTextColor.RED));
                  BridgeImplementation bridge210 = Bridge.method8();
                  List list11 = data21.method3();
                  list11.add((ClickableText)bridge210.method89(text8));
                  list11.add((ClickableText)bridge210.method89(text9));
                  data21.method2(list11);
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_QUALITY";
   }
}
