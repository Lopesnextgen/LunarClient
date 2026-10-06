package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import net.kyori.adventure.text.TextComponent;

public class ShopPriceLine {
   private final TextComponent field1;
   private final TextComponent field2;
   private final ItemStackBridge field3;

   public ShopPriceLine(TextComponent textComponent, TextComponent textComponent2, ItemStackBridge bridgeextension_43) {
      this.field1 = textComponent;
      this.field2 = textComponent2;
      this.field3 = bridgeextension_43;
   }

   public TextComponent method1() {
      return this.field1;
   }

   public TextComponent method2() {
      return this.field2;
   }

   public ItemStackBridge method3() {
      return this.field3;
   }
}
