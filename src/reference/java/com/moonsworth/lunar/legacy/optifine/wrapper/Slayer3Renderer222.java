package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.slayer.Slayer5;
import net.optifine.CustomColors;

public class Slayer3Renderer222 implements Slayer5 {
   public int getDurabilityColor(int var1) {
      return CustomColors.getDurabilityColor(1.0F, var1);
   }

   public int getTextColor(int var1, int var2) {
      return CustomColors.getTextColor(var1, var2);
   }
}
