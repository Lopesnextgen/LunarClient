package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4_3;
import java.util.List;

public interface Itemcounter4Extension extends Itemcounter4_3 {
   void method1(double var1, double var3, double var5, double var7, int var9);

   void setCancelEntry(boolean var1);

   void setCancelExit(boolean var1);

   void method4(boolean var1);

   void setColor(int var1);

   void method6(AxisAlignedBBBridge var1);

   boolean shouldRender();

   boolean isCancelEntry();

   boolean contains(double var1, double var3);

   boolean isCancelExit();

   List<AxisAlignedBBBridge> method8();

   int getColor();

   String getWorld();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method8(double var1, double var3, long var5, long var7) {
   }

   void method9(double var1, double var3);

   double method19();

   double method20();

   void method12(double var1);
}
