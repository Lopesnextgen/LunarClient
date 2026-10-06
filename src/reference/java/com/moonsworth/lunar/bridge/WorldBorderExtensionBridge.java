package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.world.WorldBorderBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;

public interface WorldBorderExtensionBridge extends WorldBorderBridge {
   void method1(double value1, double value3, double value5, double value7, int number9);

   void setCancelEntry(boolean flag1);

   void setCancelExit(boolean flag1);

   void method4(boolean flag1);

   void setColor(int number1);

   void method6(AxisAlignedBBBridge horsestats121);

   boolean shouldRender();

   boolean isCancelEntry();

   boolean contains(double value1, double value3);

   boolean isCancelExit();

   List<AxisAlignedBBBridge> method8();

   int getColor();

   String getWorld();

   @VersionGate(min = 6)
   default void method8(double value1, double value3, long value, long value2) {
   }

   void method9(double value1, double value3);

   double method19();

   double method20();

   void method12(double value1);
}
