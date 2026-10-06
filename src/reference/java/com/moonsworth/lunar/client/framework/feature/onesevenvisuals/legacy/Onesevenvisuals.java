package com.moonsworth.lunar.client.framework.feature.onesevenvisuals.legacy;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Ref;

class Onesevenvisuals {
   Onesevenvisuals() {
   }

   static void method1(AbstractRenderContext bridgeextension_90) {
      bridgeextension_90.translate(0.588F, 0.365F, -0.795F);
      bridgeextension_90.translate(0.0, -0.3F, 0.0);
      bridgeextension_90.scale(1.5F, 1.5F, 1.5F);
      bridgeextension_90.method4(50.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4(335.0F, 0.0F, 0.0F, 1.0F);
      bridgeextension_90.translate(-0.9375, -0.0625, 0.0);
      bridgeextension_90.scale(-1.0F, 1.0F, -1.0F);
   }

   static void method2(AbstractRenderContext bridgeextension_90) {
      bridgeextension_90.scale(1.1764705F, 1.1764705F, 1.1764705F);
      bridgeextension_90.method4(-25.0F, 0.0F, 0.0F, 1.0F);
      bridgeextension_90.method4(135.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.translate(0.0, -0.25, -0.125);
      bridgeextension_90.scale(0.5F, 0.5F, 0.5F);
   }

   static void method3(AbstractRenderContext bridgeextension_90, float value1, float value2) {
      bridgeextension_90.translate(0.56F, -0.52F - (1.0F - value1) * 0.6F, -0.71999997F);
      bridgeextension_90.method4(45.0F, 0.0F, 1.0F, 0.0F);
      float value3 = (float)Math.sin(value2 * value2 * (float) Math.PI);
      float value4 = (float)Math.sin(Math.sqrt(value2) * (float) Math.PI);
      bridgeextension_90.method4(-value3 * 20.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4(-value4 * 20.0F, 0.0F, 0.0F, 1.0F);
      bridgeextension_90.method4(-value4 * 80.0F, 1.0F, 0.0F, 0.0F);
      bridgeextension_90.scale(0.4F, 0.4F, 0.4F);
   }

   static void method4(AbstractRenderContext bridgeextension_90, float value1) {
      float value2 = (float)Math.sin(value1 * (float) Math.PI);
      float value3 = (float)Math.sin(Math.sqrt(value1) * (float) Math.PI);
      bridgeextension_90.translate(-value3 * 0.4F, (float)Math.sin(Math.sqrt(value1) * (float) Math.PI * 2.0) * 0.2F, -value2 * 0.2F);
   }

   static void method5(AbstractRenderContext bridgeextension_90) {
      bridgeextension_90.translate(-0.5, 0.2F, 0.0);
      bridgeextension_90.method4(30.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4(-80.0F, 1.0F, 0.0F, 0.0F);
      bridgeextension_90.method4(60.0F, 0.0F, 1.0F, 0.0F);
   }

   static void method6(AbstractRenderContext bridgeextension_90, int number1, int number2, float value3) {
      float value4 = number1 - value3 + 1.0F;
      float value5 = 1.0F - value4 / number2;
      float value6 = 1.0F - value5;
      value6 = value6 * value6 * value6;
      value6 = value6 * value6 * value6;
      value6 = value6 * value6 * value6;
      float value7 = 1.0F - value6;
      bridgeextension_90.translate(0.0, (float)Math.abs(Math.cos(value4 / 4.0F * (float) Math.PI) * 0.1F) * (value5 > 0.2 ? 1 : 0), 0.0);
      if (!Ref.method4().method40().method83().method14().method16()) {
         bridgeextension_90.translate(value7 * 0.6F, -value7 * 0.5F, 0.0);
         bridgeextension_90.method4(value7 * 90.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_90.method4(value7 * 10.0F, 1.0F, 0.0F, 0.0F);
         bridgeextension_90.method4(value7 * 30.0F, 0.0F, 0.0F, 1.0F);
      }
   }

   static void method7(AbstractRenderContext bridgeextension_90, int number1, int number2, float value3) {
      bridgeextension_90.method4(-18.0F, 0.0F, 0.0F, 1.0F);
      bridgeextension_90.method4(-12.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4(-8.0F, 1.0F, 0.0F, 0.0F);
      bridgeextension_90.translate(-0.9F, 0.2F, 0.0);
      float value4 = number2 - (number1 - value3 + 1.0F);
      float value5 = value4 / 20.0F;
      value5 = (value5 * value5 + value5 * 2.0F) / 3.0F;
      if (value5 > 1.0F) {
         value5 = 1.0F;
      }

      if (value5 > 0.1F) {
         bridgeextension_90.translate(0.0, (float)Math.sin((value4 - 0.1F) * 1.3F) * 0.01F * (value5 - 0.1F), 0.0);
      }

      bridgeextension_90.translate(0.0, 0.0, value5 * 0.1F);
      bridgeextension_90.method4(-335.0F, 0.0F, 0.0F, 1.0F);
      bridgeextension_90.method4(-50.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.translate(0.0, 0.5, 0.0);
      bridgeextension_90.scale(1.0F, 1.0F, 1.0F + value5 * 0.2F);
      bridgeextension_90.translate(0.0, -0.5, 0.0);
      bridgeextension_90.method4(50.0F, 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4(335.0F, 0.0F, 0.0F, 1.0F);
   }
}
