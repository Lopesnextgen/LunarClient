package com.moonsworth.lunar.client.render.texture;

@FunctionalInterface
public interface TextureProcessor {
   void process(TextureProcessor.TextureProvider extension1);

   interface TextureProvider {
      String name();

      String method1();

      int method2();

      int method3();

      int method4(int number1, int number2);

      void method5(int number1, int number2, int number3);
   }
}
