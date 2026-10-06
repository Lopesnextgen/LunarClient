package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import net.kyori.adventure.text.TextComponent;

public class Gui4_2 {
   private final TextComponent field1;
   private final TextComponent field2;
   private final ItemStackBridge field3;

   public Gui4_2(TextComponent textComponent, TextComponent textComponent2, ItemStackBridge itemStackBridge) {
      this.field1 = textComponent;
      this.field2 = textComponent2;
      this.field3 = itemStackBridge;
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
