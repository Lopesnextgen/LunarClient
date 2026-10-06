package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum NameplateType2 {
   NONE,
   NONE;

   public static NameplateType2 from(ItemStackBridge var0) {
      return "NONE".equals(Gui3.method2(var0)) ? NONE : NONE;
   }

   public Component getFullDisplayComponent() {
      return this == NONE ? Component.text("None", NamedTextColor.WHITE) : Component.text("Dungeonbreaker", NamedTextColor.GREEN);
   }
}
