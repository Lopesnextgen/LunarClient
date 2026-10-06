package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.Ref;

public class TextHudRow implements HudRow {
   private final String field1;
   private final ColorOption field2;
   private final boolean field3;
   private final float field4;

   public TextHudRow(String text1, ColorOption lightingextension42222, boolean flag3) {
      this.field1 = text1;
      this.field2 = lightingextension42222;
      this.field3 = flag3;
      this.field4 = Ref.method10().bridge$getStringWidth(text1);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      this.field2.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, this.field1, value2, value3, this.field3);
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
