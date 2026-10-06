package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.hitcolor.Hitcolor;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.awt.Color;
import java.util.List;

public interface Bridge3_34 {
   void bridge$renderBeacon(Itemcounter6 var1, double var2, double var4, double var6, double var8, double var10, Color var12, double var13);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   void bridge$renderBeacon(Itemcounter6 var1, double var2, double var4, double var6, double var8, double var10, List<Hitcolor> var12, double var13);
}
