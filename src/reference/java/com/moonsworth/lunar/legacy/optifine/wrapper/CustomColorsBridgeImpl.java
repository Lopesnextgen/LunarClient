package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.CustomColorsBridge;
import net.optifine.CustomColors;

public class CustomColorsBridgeImpl implements CustomColorsBridge {
   public CustomColorsBridgeImpl() {
   }

   public int getDurabilityColor(int number1) {
      return CustomColors.getDurabilityColor(1.0F, number1);
   }

   public int getTextColor(int number1, int number2) {
      return CustomColors.getTextColor(number1, number2);
   }
}
