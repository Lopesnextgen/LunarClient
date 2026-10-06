package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.client.render.texture.BaseTexture;

public class StencilEmulator {
   private static StencilEmulator field1;
   private Bridge3_24 field2;
   private final ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "stencil_emulator");
   private Bridge8Extension34 field4;

   public StencilEmulator() {
   }

   public void method1() {
      int number1 = Ref.method3().bridge$getMainRenderTarget().bridge$framebufferWidth();
      int number2 = Ref.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
      if (this.field2 == null) {
         this.method2(number1, number2);
      } else {
         if (this.field2.bridge$framebufferWidth() != number1 || this.field2.bridge$framebufferHeight() != number2) {
            this.field2.bridge$delete();
            this.method2(number1, number2);
         }
      }
   }

   public void method2(int number1, int number2) {
      this.field2 = Bridge3_24.method1(number1, number2, false);
      if (this.field4 == null) {
         this.field4 = Ref.method3().bridge$getTextureManager().method3(this.field3, new BaseTexture());
      }
   }

   public void method3(LegacyGuiGraphicsBridge mixinhelper51) {
      if (mixinhelper51.method29().method38()) {
         mixinhelper51.method29().method30().method48();
      }

      this.method1();
      this.field4.method2((Bridge8Extension)this.field2.bridge$getColorTexture(true));
      Ref.method3().bridge$overrideMainRenderTarget(this.field2, false, true);
   }

   public void method4(LegacyGuiGraphicsBridge mixinhelper51) {
      if (mixinhelper51.method29().method38()) {
         mixinhelper51.method29().method30().method48();
      }

      Ref.method3().bridge$overrideMainRenderTarget(null, false, true);
   }

   public void method5(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5) {
      LcuiScreen.method74(mixinhelper_41, value2, value3, value4, value5);
   }

   public void method6(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value) {
      float value7 = LcuiScreen.getScale();
      GuiResolution threadmoduledump718 = new GuiResolution(Ref.method3());
      double value9 = threadmoduledump718.method1() / value7;
      double value11 = threadmoduledump718.method2() / value7;
      float value13 = value2 * value;
      float value14 = (value3 + LcuiScreen.method109()) * value;
      float value15 = value4 * value;
      float value16 = value5 * value;
      float value17 = (float)(value13 / value9);
      float value18 = (float)((value13 + value15) / value9);
      float value19 = (float)(1.0 - value14 / value11);
      float value20 = (float)(1.0 - (value14 + value16) / value11);
      mixinhelper_41.method9(LunarRenderTypes.field57, this.field3, value2, value3, value4, value5, arg8x -> {
         arg8x.method5(value2, value3).method10(value17, value19).method9(-1).method16();
         arg8x.method5(value2, value3 + value5).method10(value17, value20).method9(-1).method16();
         arg8x.method5(value2 + value4, value3 + value5).method10(value18, value20).method9(-1).method16();
         arg8x.method5(value2 + value4, value3).method10(value18, value19).method9(-1).method16();
      });
   }

   public ResourceLocationBridge method7() {
      return this.field3;
   }

   public static StencilEmulator method8() {
      if (field1 == null) {
         field1 = new StencilEmulator();
      }

      return field1;
   }
}
