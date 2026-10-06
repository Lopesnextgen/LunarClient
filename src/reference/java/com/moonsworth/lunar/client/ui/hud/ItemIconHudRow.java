package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Ref;

public class ItemIconHudRow implements HudRow {
   private final ItemStackBridge field1;

   public ItemIconHudRow(ItemStackBridge bridgeextension_41) {
      this.field1 = bridgeextension_41;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      mixinhelper_41.method37(this.field1, value2, value3 - 2.0F, Ref.method3());
   }

   @Override
   public float method2() {
      return 16.0F;
   }

   @Override
   public float method3() {
      return 15.0F;
   }
}
