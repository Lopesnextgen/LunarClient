package com.moonsworth.lunar.client.ui.hud;

import java.util.function.Supplier;

public class PaddedHudComponent extends MixinCore5Handler_2<PaddedHudComponent> implements MixinCore7 {
   private final MixinCore6<Float> field2 = new MixinCore6<>(0.0F, false, true);
   private final MixinCore6<Float> field3 = new MixinCore6<>(0.0F, false, true);
   private final MixinCore6<Float> field4 = new MixinCore6<>(0.0F, false, true);
   private final MixinCore6<Float> field5 = new MixinCore6<>(0.0F, false, true);

   public PaddedHudComponent() {
   }

   public PaddedHudComponent(MixinCore5 var1) {
      super(var1);
   }

   public PaddedHudComponent method2(float var1) {
      this.field2.method1(var1);
      this.field3.method1(var1);
      this.field4.method1(var1);
      this.field5.method1(var1);
      return this;
   }

   public PaddedHudComponent method3(Supplier<Float> var1) {
      this.field2.method2(var1);
      this.field3.method2(var1);
      this.field4.method2(var1);
      this.field5.method2(var1);
      return this;
   }

   public PaddedHudComponent method4(float var1) {
      this.field2.method1(var1);
      return this;
   }

   public PaddedHudComponent method5(Supplier<Float> var1) {
      this.field2.method2(var1);
      return this;
   }

   public PaddedHudComponent method6(float var1) {
      this.field3.method1(var1);
      return this;
   }

   public PaddedHudComponent method7(Supplier<Float> var1) {
      this.field3.method2(var1);
      return this;
   }

   public PaddedHudComponent method8(float var1) {
      this.field4.method1(var1);
      return this;
   }

   public PaddedHudComponent method9(Supplier<Float> var1) {
      this.field4.method2(var1);
      return this;
   }

   public PaddedHudComponent method10(float var1) {
      this.field5.method1(var1);
      return this;
   }

   public PaddedHudComponent method11(Supplier<Float> var1) {
      this.field5.method2(var1);
      return this;
   }

   public float method11() {
      return this.field2.get() + this.field3.get();
   }

   @Override
   public float getWidth() {
      return this.field2.get() + super.getWidth() + this.field3.get();
   }

   @Override
   public float getHeight() {
      return this.field4.get() + super.getHeight() + this.field5.get();
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      super.method1(var1 + this.field2.get(), var2 + this.field4.get(), var3);
   }

   @Override
   public void clearCache() {
      this.field2.clearCache();
      this.field3.clearCache();
      this.field4.clearCache();
      this.field5.clearCache();
      super.clearCache();
   }
}
