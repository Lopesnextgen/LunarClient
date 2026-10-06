package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.Ref;

public class StringWailaComponent implements WailaComponent {
   private static final int field1 = 5;
   private final String field2;
   private boolean field3 = true;
   private ColorOption field4 = null;

   public StringWailaComponent(String text1) {
      this.field2 = text1;
   }

   @Override
   public int getWidth() {
      return (int)Ref.method10().bridge$getStringWidth(this.field2);
   }

   @Override
   public int getHeight() {
      return Ref.method10().method19();
   }

   public StringWailaComponent method1() {
      this.field3 = false;
      return this;
   }

   public StringWailaComponent method2(ColorOption lightingextension42221) {
      this.field4 = lightingextension42221;
      return this;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int number3, int number4) {
      (this.field4 == null ? waila2.method16() : this.field4)
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, this.field2, number3 + 1, (float)number4 + (this.field3 ? 5 : 1), (Boolean)waila2.method17().get());
   }
}
