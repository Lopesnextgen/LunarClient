package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.function.Supplier;

public class MixinCore5Impl2 extends MixinCore5Handler_2<MixinCore5Impl2> {
   private final MixinCore6<RewindhandlersExtension> field2 = new MixinCore6<>(RewindhandlersExtension.method23(Integer.MIN_VALUE), true, true);
   private final MixinCore6<Boolean> field3 = new MixinCore6<>(true, false, true);

   public MixinCore5Impl2() {
   }

   public MixinCore5Impl2(MixinCore5 var1) {
      super(var1);
   }

   public MixinCore5Impl2 method1(int var1) {
      this.field2.method1(RewindhandlersExtension.method23(var1));
      return this;
   }

   public MixinCore5Impl2 method2(Supplier<Integer> var1) {
      this.field2.method2(() -> RewindhandlersExtension.method23((Integer)var1.get()));
      return this;
   }

   public MixinCore5Impl2 method3(RewindhandlersExtension var1) {
      this.field2.method1(var1);
      return this;
   }

   public MixinCore5Impl2 method4(Supplier<RewindhandlersExtension> var1) {
      this.field2.method2(var1);
      return this;
   }

   public MixinCore5Impl2 method5(boolean var1) {
      this.field3.method1(var1);
      return this;
   }

   public MixinCore5Impl2 method6(Supplier<Boolean> var1) {
      this.field3.method2(var1);
      return this;
   }

   @Override
   public void method1(float var1, float value, HudRenderContext hudRenderContext) {
      RewindhandlersExtension var4 = this.field2.get();
      if (this.field3.get() && var4 != null) {
         LcuiScreen.method119(hudRenderContext.method6(), var1, value, this.getWidth(), this.getHeight(), var4);
      }

      super.method1(var1, value, hudRenderContext);
   }

   @Override
   public void clearCache() {
      this.field2.clearCache();
      this.field3.clearCache();
      super.clearCache();
   }
}
