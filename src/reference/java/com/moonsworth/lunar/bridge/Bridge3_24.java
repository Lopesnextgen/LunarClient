package com.moonsworth.lunar.bridge;

public interface Bridge3_24 {
   static Bridge3_24 method1(int number0, int number1, boolean flag2) {
      return Bridge.method8().method56(number0, number1, flag2);
   }

   static Bridge3_24 method2(int number0, int number1, boolean flag2) {
      return Bridge_52.method2().method1(number0, number1).method7(flag2).method3();
   }

   static Bridge3_24 method3(int number0, int number1, Bridge8Extension bridge8, boolean flag3) {
      return Bridge_52.method2().method1(number0, number1).method4(bridge8).method7(flag3).method3();
   }

   static Bridge3_24 method4(int number0, int number1) {
      return Bridge.method8().method58(number0, number1);
   }

   int bridge$framebufferWidth();

   int bridge$framebufferHeight();

   int bridge$framebufferTextureWidth();

   int bridge$framebufferTextureHeight();

   int bridge$getFramebufferTexture();

   void bridge$unbindFrameBuffer();

   void bridge$frameBufferRender(int number1, int number2);

   default void bridge$frameBufferRender(int number1, int number2, boolean flag3) {
      this.bridge$frameBufferRender(number1, number2);
   }

   void bridge$createBindFramebuffer(int number1, int number2);

   void bridge$framebufferClear();

   default void bridge$framebufferClear(boolean flag1) {
      this.bridge$framebufferClear();
   }

   void bridge$delete();

   void bridge$setClearColor(float value1, float value2, float value3, float value4);

   void bridge$blitToScreen(int number1, int number2, int number3, int number4, boolean flag5);

   void bridge$blitToRenderTarget(Bridge3_24 bridge3_241, int number2, int number3, int number4, int number5, int number6, int number7, int number8, int number9, boolean flag10);

   default void method5(Bridge3_24 bridge3_241, boolean flag2) {
      this.bridge$blitToRenderTarget(
         bridge3_241,
         0,
         0,
         this.bridge$framebufferWidth(),
         this.bridge$framebufferHeight(),
         0,
         0,
         bridge3_241.bridge$framebufferWidth(),
         bridge3_241.bridge$framebufferHeight(),
         flag2
      );
   }

   Bridge8_7 bridge$getColorTexture(boolean flag1);

   Bridge8Extension bridge$getDepthTexture();

   void bridge$present();

   default boolean method6(Bridge3_24 bridge3_241) {
      return this.bridge$framebufferWidth() == bridge3_241.bridge$framebufferWidth() && this.bridge$framebufferHeight() == bridge3_241.bridge$framebufferHeight();
   }
}
