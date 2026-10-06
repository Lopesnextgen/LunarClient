package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4.Type;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.function.Supplier;

public class ScrollableHudComponent extends MixinCore5Handler_2<ScrollableHudComponent> {
   private static double field2 = 0.0;
   private static boolean field3 = false;
   private final MixinCore6<Float> field4 = new MixinCore6<>(null, true, true);
   private final MixinCore6<Boolean> field5 = new MixinCore6<>(false, false, true);
   private final boolean field6;
   private final Click4 field7 = new Click4(0.0, Type.SIN_OUT);
   private final Click4 field8 = new Click4(0.0, Type.SIN_IN_OUT);

   public ScrollableHudComponent() {
      this.field6 = true;
   }

   public ScrollableHudComponent(MixinCore5 var1) {
      super(var1);
      this.field6 = true;
   }

   public ScrollableHudComponent(boolean var1) {
      this.field6 = var1;
   }

   public ScrollableHudComponent(MixinCore5 var1, boolean var2) {
      super(var1);
      this.field6 = var2;
   }

   public ScrollableHudComponent method1(Float var1) {
      this.field4.method1(var1);
      return this;
   }

   public ScrollableHudComponent method2(Supplier<Float> var1) {
      this.field4.method2(var1);
      return this;
   }

   public ScrollableHudComponent method3(boolean var1) {
      this.field5.method1(var1);
      return this;
   }

   public ScrollableHudComponent method4(Supplier<Boolean> var1) {
      this.field5.method2(var1);
      return this;
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      Float var4 = this.field4.get();
      float var5 = this.getHeight();
      float var6 = super.getHeight();
      float var7 = this.getWidth();
      float var8 = super.getWidth();
      float var9 = this.field6 ? var6 : var8;
      if (var4 != null && var9 > var4) {
         this.field8.animateTo(this.field5.get() && var3.method7() ? 4.0 : 0.0, 250L);
         LcuiScreen.method111(var3.method6(), var1, var2, var7, var5, var3.getScale());
         if (this.field6) {
            super.method1(var1, var2 - this.field7.getFloatValue(), var3);
         } else {
            super.method1(var1 - this.field7.getFloatValue(), var2, var3);
         }

         LcuiScreen.method112(var3.method6());
         boolean var10 = var3.method8().HHHCHORHIHRCOHIOICICICHCRRICCI() >= var1
            && var3.method8().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= var2
            && var3.method8().HHHCHORHIHRCOHIOICICICHCRRICCI() <= var1 + var7
            && var3.method8().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= var2 + var5;
         if (var10 && field2 != 0.0) {
            double var11 = this.field7.getValue2();
            double var13 = this.field6 ? var6 - var5 : var8 - var7;
            double var15 = Math.min(Math.max(var11 - field2 * 20.0, 0.0), var13);
            this.field7.animateTo(var15, 100L);
            field2 += (var15 - var11) / 20.0;
         }
      } else {
         this.field8.animateTo(0.0, 250L);
         super.method1(var1, var2, var3);
      }

      if (this.field8.getValue() > 0.0) {
         float var12;
         float var17;
         float var18;
         float var19;
         if (this.field6) {
            float var14 = this.field7.getFloatValue() / (var6 - var5);
            var19 = Math.max(Math.min(10.0F, var5), var5 / var6 * var5);
            var17 = var1 + super.getWidth() + 2.0F;
            var18 = ThreadModuleDump67.lerp(var2, var2 + var5 - var19, var14);
            var12 = 2.0F;
         } else {
            float var20 = this.field7.getFloatValue() / (var8 - var7);
            var12 = Math.max(Math.min(10.0F, var7), var7 / var8 * var7);
            var18 = var2 + var6 + 2.0F;
            var17 = ThreadModuleDump67.lerp(var1, var1 + var7 - var12, var20);
            var19 = 2.0F;
         }

         LcuiScreen.method119(
            var3.method6(),
            var17,
            var18,
            var12,
            var19,
            RewindhandlersExtension.method23(ThreadModuleDump23.method18(-3355444, this.field8.getFloatValue() / 4.0F))
         );
      }
   }

   @Override
   public float getWidth() {
      if (this.field6) {
         return super.getWidth() + this.field8.getFloatValue();
      }

      Float var1 = this.field4.get();
      return var1 == null ? super.getWidth() : Math.min(var1, super.getWidth());
   }

   @Override
   public float getHeight() {
      if (!this.field6) {
         return super.getHeight() + this.field8.getFloatValue();
      }

      Float var1 = this.field4.get();
      return var1 == null ? super.getHeight() : Math.min(var1, super.getHeight());
   }

   @Override
   public void clearCache() {
      this.field4.clearCache();
      this.field5.clearCache();
      super.clearCache();
   }

   static {
      ClientEventBus.method29().method2(MarkerInputEvent.class, var0 -> {
         if (var0.method4() == MouseInputTypeLegacy.SCROLL && ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            field2 = field2 + (ThreadModuleDump63.MC_VERSION > 6 ? var0.method9() : var0.method9() / 120.0);
            field3 = false;
         }
      });
      ClientEventBus.method29().method4(HudBaseRenderEvent.class, var0 -> {
         if (field2 != 0.0) {
            if (field3) {
               field2 = 0.0;
            }

            field3 = !field3;
         }
      }, 1000);
   }
}
