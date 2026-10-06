package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.moonsworth.lunar.bridge.Bridge3_24;
import lombok.Generated;

public class Shaderdebugmod4 {
   private final int field1 = 704;
   private final int field2 = 544;
   private boolean field3 = false;
   private Bridge3_24 field4 = null;
   private boolean initialized;

   public void init() {
      if (!this.isInitialized()) {
         this.initialized = true;
         this.load();
      }
   }

   public boolean isValid() {
      return this.field4 != null;
   }

   public void method1(Bridge3_24 var1, boolean var2) {
      if (!this.field3 && var2) {
         this.field4.bridge$setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
         this.field4.bridge$framebufferClear(true);
      }

      this.field3 = var2;
      if (var2) {
         this.method2(var1, 1, 0, 2, 0, 10, 1);
         this.method2(var1, 11, 0, 15, 0, 10, 1);
         this.method2(var1, 0, 1, 0, 2, 1, 16);
         this.method2(var1, 1, 1, 2, 2, 10, 16);
         this.method2(var1, 11, 1, 13, 2, 1, 16);
         this.method2(var1, 12, 1, 15, 2, 10, 16);
      } else {
         var1.bridge$blitToRenderTarget(this.field4, 0, 0, var1.bridge$framebufferWidth(), var1.bridge$framebufferHeight(), 0, 0, 704, 544, false);
      }
   }

   private void method2(Bridge3_24 var1, int var2, int value, int value2, int value3, int value4, int value5) {
      int var8 = this.method3(var1.bridge$framebufferWidth());
      int var9 = this.method4(var1.bridge$framebufferHeight());
      float var10 = this.method5();
      float var11 = this.method6();
      var1.bridge$blitToRenderTarget(
         this.field4,
         var8 * var2,
         var9 * value,
         var8 * (var2 + value4),
         var9 * (value + value5),
         (int)Math.floor(var10 * value2),
         (int)Math.floor(var11 * ++value3),
         (int)Math.floor(var10 * (value2 + value4)),
         (int)Math.floor(var11 * (value3 + value5)),
         false
      );
   }

   private void load() {
      this.field4 = Bridge3_24.method2(704, 544, false);
   }

   private int method3(int var1) {
      return Math.max(1, var1 / 22);
   }

   private int method4(int var1) {
      return Math.max(1, var1 / 17);
   }

   private float method5() {
      return 704.0F / 25.0F;
   }

   private float method6() {
      return 544.0F / 20.0F;
   }

   @Generated
   public int method7() {
      return 704;
   }

   @Generated
   public int method8() {
      return 544;
   }

   @Generated
   public Bridge3_24 method9() {
      return this.field4;
   }

   @Generated
   public boolean isInitialized() {
      return this.initialized;
   }
}
