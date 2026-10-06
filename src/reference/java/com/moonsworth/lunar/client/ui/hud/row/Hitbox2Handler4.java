package com.moonsworth.lunar.client.ui.hud.row;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Hitbox2Handler4 implements Hitbox2 {
   private final ItemStackBridge field1;

   public Hitbox2Handler4(ItemStackBridge var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(MixinHelper_4 var1, float value, float value2) {
      var1.method37(this.field1, value, value2 - 2.0F, ThreadModuleDump63.method3());
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
