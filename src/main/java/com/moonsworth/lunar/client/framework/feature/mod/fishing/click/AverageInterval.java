package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.floats.FloatListIterator;
import lombok.Generated;

public final class AverageInterval {
   public static float averageInterval(FloatList list) {
      float value1 = -1.0F;
      float value2 = 0.0F;
      int index3 = 0;
      FloatListIterator floatlistiterator4 = list.iterator();

      while (floatlistiterator4.hasNext()) {
         Float value5 = (Float)floatlistiterator4.next();
         if (index3 >= 8) {
            break;
         }

         if (value1 != -1.0F) {
            value2 += value5 - value1;
            index3++;
         }

         value1 = value5;
      }

      return value2 / index3;
   }

   @Generated
   private AverageInterval() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
