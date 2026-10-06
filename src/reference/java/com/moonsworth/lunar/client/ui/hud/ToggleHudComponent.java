package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class ToggleHudComponent extends HudComponentDecorator {
   private final HideableHudComponent field2;
   private final HideableHudComponent field3;

   public ToggleHudComponent(HudComponent mixincore51, HudComponent mixincore52) {
      super.field1 = new HudComponentGroup(true).method5(this.field2 = new HideableHudComponent(mixincore51)).method5(this.field3 = new HideableHudComponent(mixincore52).method1(true));
   }

   public ToggleHudComponent method1(boolean flag1) {
      this.field2.method1(!flag1);
      this.field3.method1(flag1);
      return this;
   }

   public ToggleHudComponent method2(Supplier<Boolean> supplier1) {
      this.field2.method2(() -> !(Boolean)supplier1.get());
      this.field3.method2(supplier1);
      return this;
   }

   public ToggleHudComponent method3(boolean flag1) {
      this.field2.method1(flag1);
      this.field3.method1(!flag1);
      return this;
   }

   public ToggleHudComponent method4(Supplier<Boolean> supplier1) {
      this.field2.method2(supplier1);
      this.field3.method2(() -> !(Boolean)supplier1.get());
      return this;
   }
}
