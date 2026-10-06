package com.moonsworth.lunar.client.ui.hud.row;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Hitbox2Handler implements Hitbox2 {
   private final String field1;
   private final ColorOption field2;
   private final boolean field3;
   private final float field4;

   public Hitbox2Handler(String var1, ColorOption var2, boolean var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = ThreadModuleDump63.method10().bridge$getStringWidth(var1);
   }

   @Override
   public void method1(MixinHelper_4 var1, float var2, float var3) {
      this.field2.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, this.field1, var2, var3, this.field3);
   }

   @Override
   public float method2() {
      return this.field4;
   }

   @Override
   public float method3() {
      return 10.0F;
   }
}
