package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.CustomColorsBridge;
import net.optifine.CustomColors;

public class Slayer3Impl implements CustomColorsBridge {
   public Slayer3Impl() {
   }

   @Override
   public int getDurabilityColor(int number1) {
      return CustomColors.getDurabilityColor(number1);
   }

   @Override
   public int getTextColor(int number1, int value) {
      return CustomColors.getTextColor(number1, value);
   }
}
