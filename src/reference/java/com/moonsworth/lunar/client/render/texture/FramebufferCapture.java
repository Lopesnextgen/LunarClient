package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.alert.Alert5Handler;
import lombok.Generated;

public class FramebufferCapture {
   private final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "framebuffer/" + System.currentTimeMillis() + "-" + ThreadModuleDump48.field24.nextInt());
   private Bridge8Extension34 field2;
   protected Bridge3_24 field3 = null;
   private boolean field4 = true;

   public void method1(int var1, int var2) {
      this.method2(var1, var2, false);
   }

   public void method2(int var1, int var2, boolean var3) {
      if (this.field3 == null) {
         this.field3 = Bridge3_24.method1(var1, var2, true);
         this.field3.bridge$setClearColor(0.0F, 0.0F, 0.0F, this.field4 ? 0.0F : 1.0F);
         this.field3.bridge$framebufferClear();
         this.method3();
      } else if (this.field3.bridge$framebufferWidth() != var1 || this.field3.bridge$framebufferHeight() != var2 || var3) {
         this.field3.bridge$createBindFramebuffer(var1, var2);
         this.field3.bridge$setClearColor(0.0F, 0.0F, 0.0F, this.field4 ? 0.0F : 1.0F);
         this.field3.bridge$framebufferClear();
         this.method3();
      }
   }

   protected void method3() {
   }

   public void method4(AbstractRenderContext var1, ThreadModuleDump71 var2, Runnable var3) {
      this.method5(var1, var2, var3, true);
   }

   public void method5(AbstractRenderContext var1, ThreadModuleDump71 var2, Runnable var3, boolean var4) {
      this.method6(var1, var2, var3, var4, true);
   }

   public void method6(AbstractRenderContext var1, ThreadModuleDump71 var2, Runnable var3, boolean var4, boolean var5) {
      var1.method14();
      var1.method18();
      var1.method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
      if (var5) {
         this.field3.bridge$framebufferClear();
      }

      ThreadModuleDump63.method3().method1(this.field3, true);
      var1.method8(BridgeType3_3.GL_PROJECTION);
      var1.method36();
      Bridge.method42().bridge$loadIdentity();
      var1.method22(0.0, this.field3.bridge$framebufferTextureWidth(), this.field3.bridge$framebufferTextureHeight(), 0.0, 1000.0, 3000.0);
      var1.method8(BridgeType3_3.GL_MODELVIEW);
      var1.method36();
      Bridge.method42().bridge$loadIdentity();
      var1.translate(0.0, 0.0, -2000.0);
      var3.run();
      ThreadModuleDump63.method3().method1(ThreadModuleDump63.method3().bridge$getMainRenderTarget(), true);
      if (var4) {
         this.method7(var1, var2.getScaledWidth(), var2.getScaledHeight(), true);
      }
   }

   public void method7(AbstractRenderContext var1, float var2, float var3, boolean var4) {
      this.method8(var1, var2, var3, this.field3, var4);
   }

   public void method8(AbstractRenderContext var1, float var2, float var3, Bridge3_24 var4, boolean var5) {
      if (ThreadModuleDump63.method43()) {
         if (var5) {
            var1.method14();
            var1.method4(BridgeType2_9.GL_ONE, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ONE);
         } else {
            var1.method15();
            var1.method26();
         }
      }

      byte var6 = -1;
      if (this.field2 == null) {
         this.field2 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field1, new Alert5Handler());
      }

      this.field2.method2((Bridge8Extension)var4.bridge$getColorTexture(true));
      Bridge2_32 var7 = var1.method10(
         !var5 && ThreadModuleDump63.MC_VERSION >= 29 ? LunarRenderTypes.field35.get(this.field1) : LunarRenderTypes.field33.get(this.field1)
      );
      var7.method1();
      var7.method5(0.0F, 0.0F).method10(0.0F, 1.0F).method9(var6).method16();
      var7.method5(0.0F, var3).method10(0.0F, 0.0F).method9(var6).method16();
      var7.method5(var2, var3).method10(1.0F, 0.0F).method9(var6).method16();
      var7.method5(var2, 0.0F).method10(1.0F, 1.0F).method9(var6).method16();
      var7.method17(BufferBuildMode.BATCHED);
   }

   @Generated
   public ResourceLocationBridge method9() {
      return this.field1;
   }

   @Generated
   public Bridge8Extension34 method10() {
      return this.field2;
   }

   @Generated
   public Bridge3_24 method11() {
      return this.field3;
   }

   @Generated
   public boolean method12() {
      return this.field4;
   }

   @Generated
   public void method13(boolean var1) {
      this.field4 = var1;
   }
}
