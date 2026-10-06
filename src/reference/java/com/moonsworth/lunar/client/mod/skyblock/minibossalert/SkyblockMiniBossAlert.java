package com.moonsworth.lunar.client.mod.skyblock.minibossalert;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockMiniBossAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final Component field9 = Component.text("Mini-Boss Spawned!", NamedTextColor.RED);

   public SkyblockMiniBossAlert(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(TypedChatMessage.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_MINI_BOSS_ALERT";
   }

   private void method1(TypedChatMessage data1) {
      String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
      if (text2.startsWith("SLAYER MINI-BOSS ") && text2.endsWith(" has spawned!")) {
         this.field8.method2(ComparableImpl.method2().method1("SLAYER_MINI").method2(field9).method6());
      }
   }
}
