package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Consumer;

public class TwoColumnHudComponent extends MixinCore5Handler2 {
   private final MixinCore5Impl3 field2 = new MixinCore5Impl3();
   private final MixinCore5Iterator field3 = new MixinCore5Iterator(true);
   private final MixinCore5Iterator field4 = new MixinCore5Iterator(true, MixinCore5Iterator.field3);
   private final PaddedHudComponent field5 = new PaddedHudComponent(
      new MixinCore5Iterator()
         .method5(this.field3)
         .method5(MixinCore5Iterator.field4)
         .method5(this.field4)
         .method2(() -> this.field2.getWidth() - this.field5.method11())
   );

   public TwoColumnHudComponent() {
      MixinCore5Iterator var1 = new MixinCore5Iterator(MixinCore5Iterator.field2)
         .method5(MixinCore5Iterator.field4)
         .method5(this.field2)
         .method5(MixinCore5Iterator.field4)
         .method2(this.field5::getWidth);
      super.field1 = new MixinCore5Iterator(true).method5(var1).method5(this.field5);
   }

   public TwoColumnHudComponent method1(MixinCore5 var1) {
      this.field2.method1(var1);
      return this;
   }

   public TwoColumnHudComponent method2(MixinCore5 var1, MixinCore5 var2) {
      this.field3.method5(var1);
      this.field4.method5(var2);
      return this;
   }

   public TwoColumnHudComponent method3(int var1, MixinCore5 var2, MixinCore5 mixinCore5) {
      this.field3.method4(var1, var2);
      this.field4.method4(var1, mixinCore5);
      return this;
   }

   public TwoColumnHudComponent method4() {
      this.field3.method8();
      this.field4.method8();
      return this;
   }

   public MixinCore7 method5() {
      return this.field5;
   }

   public TwoColumnHudComponent method6(Consumer<MixinCore7> var1) {
      var1.accept(this.field5);
      return this;
   }
}
