package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.moonsworth.lunar.bridge.Bridge3_24;
import lombok.Generated;

public class ShaderPreviewRenderer {
   private final int field1 = 704;
   private final int field2 = 544;
   private boolean field3 = false;
   private Bridge3_24 field4 = null;
   private boolean initialized;

   public ShaderPreviewRenderer() {
   }

   public void init() {
      if (!this.isInitialized()) {
         this.initialized = true;
         this.load();
      }
   }

   public boolean isValid() {
      return this.field4 != null;
   }

   public void method1(Bridge3_24 bridge3_241, boolean flag) {
      if (!this.field3 && flag) {
         this.field4.bridge$setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
         this.field4.bridge$framebufferClear(true);
      }

      this.field3 = flag;
      if (flag) {
         this.method2(bridge3_241, 1, 0, 2, 0, 10, 1);
         this.method2(bridge3_241, 11, 0, 15, 0, 10, 1);
         this.method2(bridge3_241, 0, 1, 0, 2, 1, 16);
         this.method2(bridge3_241, 1, 1, 2, 2, 10, 16);
         this.method2(bridge3_241, 11, 1, 13, 2, 1, 16);
         this.method2(bridge3_241, 12, 1, 15, 2, 10, 16);
      } else {
         bridge3_241.bridge$blitToRenderTarget(this.field4, 0, 0, bridge3_241.bridge$framebufferWidth(), bridge3_241.bridge$framebufferHeight(), 0, 0, 704, 544, false);
      }
   }

   private void method2(Bridge3_24 bridge3_241, int value, int value2, int value3, int value4, int value5, int value6) {
      int number8 = this.method3(bridge3_241.bridge$framebufferWidth());
      int number9 = this.method4(bridge3_241.bridge$framebufferHeight());
      float value10 = this.method5();
      float value11 = this.method6();
      bridge3_241.bridge$blitToRenderTarget(
         this.field4,
         number8 * value,
         number9 * value2,
         number8 * (value + value5),
         number9 * (value2 + value6),
         (int)Math.floor(value10 * value3),
         (int)Math.floor(value11 * ++value4),
         (int)Math.floor(value10 * (value3 + value5)),
         (int)Math.floor(value11 * (value4 + value6)),
         false
      );
   }

   private void load() {
      this.field4 = Bridge3_24.method2(704, 544, false);
   }

   private int method3(int number1) {
      return Math.max(1, number1 / 22);
   }

   private int method4(int number1) {
      return Math.max(1, number1 / 17);
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
