package com.moonsworth.lunar.client.mod.skyblock.teamcakehighlight;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.CalculatorType;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;

public class TeamCakeHighlight extends AbstractFeature {
   private final HologramEntityListener field8 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final EquippedItemListener field9 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final EntitySubscription<Bridge6_10> field10 = this.field8
      .method6()
      .method1(Bridge6_10.class)
      .method2(arg0 -> !arg0.bridge$isSelf() && CalculatorType.of(arg0) != null)
      .method4(this);

   public TeamCakeHighlight(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(HudRenderLegacyEvent.class, this::method1);
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      if (!this.field10.isEmpty()) {
         ItemStackBridge bridgeextension_42 = this.field9.method8();
         CalculatorType calculatortype3 = CalculatorType.byItem(bridgeextension_42);
         if (calculatortype3 != null) {
            AbstractRenderContext bridgeextension_94 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_435 = Ref.method13();
            bridgeextension_94.push();
            bridgeextension_94.translate(-bridge2_435.bridge$renderPosX(), -bridge2_435.bridge$renderPosY(), -bridge2_435.bridge$renderPosZ());
            BufferBuilderBridge bridge_286 = bridgeextension_94.method11((Float)((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method19().get());

            for (Bridge6_10 bridge6_108 : this.field10) {
               if (CalculatorType.of(bridge6_108) == calculatortype3) {
                  AxisAlignedBBBridge horsestats129 = bridge6_108.RIIICIRHRCIHOOOORHOICRIICCCRHR(highlightimpl21.method5());
                  WorldRenderUtils.drawBoxOutline(bridge_286, horsestats129, calculatortype3.getHighlightColor());
               }
            }

            bridge_286.end();
            bridgeextension_94.pop();
         }
      }
   }

   public String getId() {
      return "TEAM_CAKE_HIGHLIGHT";
   }
}
