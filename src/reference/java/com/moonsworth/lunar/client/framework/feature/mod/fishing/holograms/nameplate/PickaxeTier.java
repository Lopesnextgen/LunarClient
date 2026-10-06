package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum PickaxeTier {
   NONE,
   DUNGEONBREAKER;

   PickaxeTier() {
   }

   public static PickaxeTier from(ItemStackBridge bridgeextension_40) {
      return "DUNGEONBREAKER".equals(SkyblockItemUtil.method2(bridgeextension_40)) ? DUNGEONBREAKER : NONE;
   }

   public Component getFullDisplayComponent() {
      return this == NONE ? Component.text("None", NamedTextColor.WHITE) : Component.text("Dungeonbreaker", NamedTextColor.GREEN);
   }
}
