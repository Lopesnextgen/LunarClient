package com.moonsworth.lunar.client.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import lombok.Generated;

public final class ThreadModuleDump19 {
   public static boolean arrayEquals(Object var0, Object var1) {
      int var2 = Array.getLength(var0);
      if (var2 != Array.getLength(var1)) {
         return false;
      } else if (var0 instanceof boolean[] var6) {
         return var1 instanceof boolean[] var7 ? Arrays.equals(var6, var7) : false;
      } else if (var0 instanceof char[] var4) {
         return var1 instanceof char[] var5 ? Arrays.equals(var4, var5) : false;
      } else {
         if (var2 == 0) {
            return var0.equals(var1);
         }

         for (int var3 = 0; var3 < var2; var3++) {
            if (!Objects.equals(Array.get(var0, var3), Array.get(var1, var3))) {
               return false;
            }
         }

         return true;
      }
   }

   public static String arrayToString(Object var0) {
      StringBuilder var1 = new StringBuilder("[");
      int var2 = Array.getLength(var0);

      for (int var3 = 0; var3 < var2; var3++) {
         var1.append(Array.get(var0, var3).toString());
         if (var3 != var2 - 1) {
            var1.append(", ");
         }
      }

      return var1.append("]").toString();
   }

   @Generated
   private ThreadModuleDump19() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
