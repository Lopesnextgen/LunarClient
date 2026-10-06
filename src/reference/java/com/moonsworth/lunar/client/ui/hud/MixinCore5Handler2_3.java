package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class MixinCore5Handler2_3 extends MixinCore5Handler2 {
   private final MixinCore5Impl field2;
   private final MixinCore5Impl field3;

   public MixinCore5Handler2_3(MixinCore5 var1, MixinCore5 mixinCore5) {
      super.field1 = new MixinCore5Iterator(true).method5(this.field2 = new MixinCore5Impl(var1)).method5(this.field3 = new MixinCore5Impl(mixinCore5).method1(true));
   }

   public MixinCore5Handler2_3 method1(boolean var1) {
      this.field2.method1(!var1);
      this.field3.method1(var1);
      return this;
   }

   public MixinCore5Handler2_3 method2(Supplier<Boolean> var1) {
      this.field2.method2(() -> !(Boolean)var1.get());
      this.field3.method2(var1);
      return this;
   }

   public MixinCore5Handler2_3 method3(boolean var1) {
      this.field2.method1(var1);
      this.field3.method1(!var1);
      return this;
   }

   public MixinCore5Handler2_3 method4(Supplier<Boolean> var1) {
      this.field2.method2(var1);
      this.field3.method2(() -> !(Boolean)var1.get());
      return this;
   }
}
