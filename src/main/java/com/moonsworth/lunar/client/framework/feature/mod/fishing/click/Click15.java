package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.floats.FloatListIterator;
import lombok.Generated;

public final class Click15 {
   public static float averageInterval(FloatList list) {
      float var1 = -1.0F;
      float var2 = 0.0F;
      int var3 = 0;
      FloatListIterator var4 = list.iterator();

      while (var4.hasNext()) {
         Float var5 = (Float)var4.next();
         if (var3 >= 8) {
            break;
         }

         if (var1 != -1.0F) {
            var2 += var5 - var1;
            var3++;
         }

         var1 = var5;
      }

      return var2 / var3;
   }

   @Generated
   private Click15() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
