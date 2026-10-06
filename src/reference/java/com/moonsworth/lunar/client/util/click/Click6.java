package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.alert.Alert5Handler;

public class Click6 {
   private static Click6 field1;
   private Bridge3_24 field2;
   private final ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "stencil_emulator");
   private Bridge8Extension34 field4;

   public void method1() {
      int var1 = ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferWidth();
      int var2 = ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
      if (this.field2 == null) {
         this.method2(var1, var2);
      } else {
         if (this.field2.bridge$framebufferWidth() != var1 || this.field2.bridge$framebufferHeight() != var2) {
            this.field2.bridge$delete();
            this.method2(var1, var2);
         }
      }
   }

   public void method2(int var1, int var2) {
      this.field2 = Bridge3_24.method1(var1, var2, false);
      if (this.field4 == null) {
         this.field4 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field3, new Alert5Handler());
      }
   }

   public void method3(LegacyGuiGraphicsBridge var1) {
      if (var1.method29().method38()) {
         var1.method29().method30().method48();
      }

      this.method1();
      this.field4.method2((Bridge8Extension)this.field2.bridge$getColorTexture(true));
      ThreadModuleDump63.method3().bridge$overrideMainRenderTarget(this.field2, false, true);
   }

   public void method4(LegacyGuiGraphicsBridge var1) {
      if (var1.method29().method38()) {
         var1.method29().method30().method48();
      }

      ThreadModuleDump63.method3().bridge$overrideMainRenderTarget(null, false, true);
   }

   public void method5(MixinHelper_4 var1, float var2, float var3, float var4, float var5) {
      LcuiScreen.method74(var1, var2, var3, var4, var5);
   }

   public void method6(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float value) {
      float var7 = LcuiScreen.getScale();
      ThreadModuleDump71 var8 = new ThreadModuleDump71(ThreadModuleDump63.method3());
      double var9 = var8.getScaledWidth_double() / var7;
      double var11 = var8.getScaledHeight_double() / var7;
      float var13 = var2 * value;
      float var14 = (var3 + LcuiScreen.method109()) * value;
      float var15 = var4 * value;
      float var16 = var5 * value;
      float var17 = (float)(var13 / var9);
      float var18 = (float)((var13 + var15) / var9);
      float var19 = (float)(1.0 - var14 / var11);
      float var20 = (float)(1.0 - (var14 + var16) / var11);
      var1.method9(LunarRenderTypes.field57, this.field3, var2, var3, var4, var5, var8x -> {
         var8x.method5(var2, var3).method10(var17, var19).method9(-1).method16();
         var8x.method5(var2, var3 + var5).method10(var17, var20).method9(-1).method16();
         var8x.method5(var2 + var4, var3 + var5).method10(var18, var20).method9(-1).method16();
         var8x.method5(var2 + var4, var3).method10(var18, var19).method9(-1).method16();
      });
   }

   public ResourceLocationBridge method7() {
      return this.field3;
   }

   public static Click6 method8() {
      if (field1 == null) {
         field1 = new Click6();
      }

      return field1;
   }
}
