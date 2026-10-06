package com.moonsworth.lunar.client.util.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import lombok.Generated;

public final class ArrayUtils {
   public static boolean method1(Object obj0, Object object) {
      int number2 = Array.getLength(obj0);
      if (number2 != Array.getLength(object)) {
         return false;
      } else if (obj0 instanceof boolean[] items6) {
         return object instanceof boolean[] items7 ? Arrays.equals(items6, items7) : false;
      } else if (obj0 instanceof char[] items4) {
         return object instanceof char[] items5 ? Arrays.equals(items4, items5) : false;
      } else {
         if (number2 == 0) {
            return obj0.equals(object);
         }

         for (int index3 = 0; index3 < number2; index3++) {
            if (!Objects.equals(Array.get(obj0, index3), Array.get(object, index3))) {
               return false;
            }
         }

         return true;
      }
   }

   public static String method2(Object obj0) {
      StringBuilder builder1 = new StringBuilder("[");
      int number2 = Array.getLength(obj0);

      for (int index3 = 0; index3 < number2; index3++) {
         builder1.append(Array.get(obj0, index3).toString());
         if (index3 != number2 - 1) {
            builder1.append(", ");
         }
      }

      return builder1.append("]").toString();
   }

   @Generated
   private ArrayUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
