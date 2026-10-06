package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public interface WorldeditSelection {
   static WorldeditSelection method1(WorldeditcuiType world) {
      return world.create();
   }

   WorldeditSelection method2(double value1, double value3, double value5);

   WorldeditSelection method3(int number1, double value2, double value4, double value6);

   WorldeditSelection method4(double value1, double value3);
}
