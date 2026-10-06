package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ConfigPanelPosition;

public interface PanelPosition {
   float method1();

   void method2(float value1);

   float method3();

   void method4(float value1);

   int method5();

   void method6(int number1);

   boolean isReset();

   void setReset(boolean flag1);

   static PanelPosition method9() {
      return new ConfigPanelPosition();
   }
}
