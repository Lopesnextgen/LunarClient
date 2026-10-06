package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Supplier;

public class MixinCore5Handler4 implements MixinCore5 {
   private final MixinCore6<Double> field1 = new MixinCore6<>(1.0, false, true);
   private final MixinCore6<Boolean> field2 = new MixinCore6<>(false, false, true);
   private final MixinCore6<Boolean> field3 = new MixinCore6<>(false, false, true);
   private final MixinCore6<Double> field4 = new MixinCore6<>(0.5, false, true);
   private final MixinCore6<ItemStackBridge> field5 = new MixinCore6<>(null, true, true);
   private final TextHudComponent field6 = new TextHudComponent().method15(() -> this.field4.get() * this.field1.get());
   private boolean field7 = false;

   public MixinCore5Handler4() {
      this((ItemStackBridge)null);
   }

   public MixinCore5Handler4(Bridge6_4 var1) {
      this(Bridge.method8().method38(var1));
   }

   public MixinCore5Handler4(Bridge3_23 var1) {
      this(Bridge.method8().method39(var1));
   }

   public MixinCore5Handler4(ItemStackBridge var1) {
      this.field5.method1(var1);
   }

   public MixinCore5Handler4 method1(ItemStackBridge var1) {
      this.field5.method1(var1);
      return this;
   }

   public MixinCore5Handler4 method2(Supplier<ItemStackBridge> var1) {
      this.field5.method2(var1);
      return this;
   }

   public MixinCore5Handler4 method3(Bridge6_4 var1) {
      this.field5.method1(Bridge.method8().method38(var1));
      return this;
   }

   public MixinCore5Handler4 method4(Bridge3_23 var1) {
      this.field5.method1(Bridge.method8().method39(var1));
      return this;
   }

   public MixinCore5Handler4 method5(String var1) {
      this.field7 = var1 != null;
      if (this.field7) {
         this.field6.method1(var1);
      }

      return this;
   }

   public MixinCore5Handler4 method6(double var1) {
      this.field1.method1(var1);
      return this;
   }

   public MixinCore5Handler4 method7(Supplier<Double> var1) {
      this.field1.method2(var1);
      return this;
   }

   public MixinCore5Handler4 method8(double var1) {
      this.field4.method1(var1);
      return this;
   }

   public MixinCore5Handler4 method9(boolean var1) {
      this.field2.method1(var1);
      return this;
   }

   public MixinCore5Handler4 method10(boolean var1) {
      this.field3.method1(var1);
      return this;
   }

   public MixinCore5Handler4 method11(Supplier<Boolean> var1) {
      this.field3.method2(var1);
      return this;
   }

   @Override
   public void clearCache() {
      this.field6.clearCache();
      this.field1.clearCache();
      this.field2.clearCache();
      this.field3.clearCache();
      this.field4.clearCache();
      this.field5.clearCache();
   }

   @Override
   public float getWidth() {
      return (float)(8.0 * this.field1.get());
   }

   @Override
   public float getHeight() {
      return (float)(8.0 * this.field1.get());
   }

   @Override
   public void method1(float var1, float value, HudRenderContext hudRenderContext) {
      ItemStackBridge var4 = this.field5.get();
      if (var4 != null && !var4.bridge$isEmpty()) {
         float var5 = this.field1.get().floatValue();
         hudRenderContext.method6().push();
         hudRenderContext.method6().method38(var1, value, 0.0F);
         hudRenderContext.method6().scale(var5 / 2.0F, var5 / 2.0F, 1.0F);
         hudRenderContext.method6().method38(8.0F, 8.0F, 0.0F);
         if (this.field2.get()) {
            if (ThreadModuleDump63.MC_VERSION >= 6) {
               hudRenderContext.method6().scale(1.2F, 1.2F, 1.0F);
            } else {
               hudRenderContext.method6().scale(1.3F, 1.3F, 1.0F);
            }
         }

         Bridge.method14().method2();
         hudRenderContext.method6().method34(var4, -8, -8, ThreadModuleDump63.method3());
         Bridge.method14().method3();
         hudRenderContext.method6().pop();
         if (this.field3.get()) {
            if (!this.field7) {
               this.field6.method1(var4.bridge$getStackSize() != 1 ? String.valueOf(var4.bridge$getStackSize()) : "");
            }

            this.field6.method1(var1 + this.getWidth() - this.field6.getWidth() + 1.0F, value + this.getHeight() - this.field6.getHeight() + 0.5F, hudRenderContext);
         }
      }
   }
}
