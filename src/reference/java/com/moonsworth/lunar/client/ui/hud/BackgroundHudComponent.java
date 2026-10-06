package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.function.Supplier;

public class BackgroundHudComponent extends HudComponentWrapper<BackgroundHudComponent> {
   private final HudComponentValue<RewindhandlersExtension> field2 = new HudComponentValue<>(RewindhandlersExtension.method23(Integer.MIN_VALUE), true, true);
   private final HudComponentValue<Boolean> field3 = new HudComponentValue<>(true, false, true);

   public BackgroundHudComponent() {
   }

   public BackgroundHudComponent(HudComponent mixincore51) {
      super(mixincore51);
   }

   public BackgroundHudComponent method1(int value) {
      this.field2.method1(RewindhandlersExtension.method23(value));
      return this;
   }

   public BackgroundHudComponent method2(Supplier<Integer> supplier1) {
      this.field2.method2(() -> RewindhandlersExtension.method23((Integer)supplier1.get()));
      return this;
   }

   public BackgroundHudComponent method3(RewindhandlersExtension rewindhandlers) {
      this.field2.method1(rewindhandlers);
      return this;
   }

   public BackgroundHudComponent method4(Supplier<RewindhandlersExtension> supplier1) {
      this.field2.method2(supplier1);
      return this;
   }

   public BackgroundHudComponent method5(boolean flag) {
      this.field3.method1(flag);
      return this;
   }

   public BackgroundHudComponent method6(Supplier<Boolean> supplier1) {
      this.field3.method2(supplier1);
      return this;
   }

   @Override
   public void method1(float value, float value2, HudRenderContext mixincore43) {
      RewindhandlersExtension rewindhandlersextension4 = this.field2.get();
      if (this.field3.get() && rewindhandlersextension4 != null) {
         LcuiScreen.method119(mixincore43.method6(), value, value2, this.getWidth(), this.getHeight(), rewindhandlersextension4);
      }

      super.method1(value, value2, mixincore43);
   }

   @Override
   public void clearCache() {
      this.field2.clearCache();
      this.field3.clearCache();
      super.clearCache();
   }
}
