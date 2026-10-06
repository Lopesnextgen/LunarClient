package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.util.math.Easing;

public class NumberInterpolator {
   public NumberInterpolator() {
   }

   public static <T> T method1(Easing threadmoduledump180, T value1, T value2, T value3, T value4, float value5) {
      Class clazz6 = value2.getClass();
      if (clazz6 == Double.class) {
         return (T)threadmoduledump180.method4((Double)value1, (Double)value2, (Double)value3, (Double)value4, value5);
      } else if (clazz6 == Float.class) {
         return (T)threadmoduledump180.method3((Float)value1, (Float)value2, (Float)value3, (Float)value4, value5);
      } else if (clazz6 == Integer.class) {
         return (T)(int)threadmoduledump180.method3(((Integer)value1).intValue(), ((Integer)value2).intValue(), ((Integer)value3).intValue(), ((Integer)value4).intValue(), value5);
      } else if (clazz6 == Long.class) {
         return (T)(long)threadmoduledump180.method3(
            (float)((Long)value1).longValue(), (float)((Long)value2).longValue(), (float)((Long)value3).longValue(), (float)((Long)value4).longValue(), value5
         );
      } else {
         throw new IllegalArgumentException("Unsupported number type: " + clazz6.getSimpleName());
      }
   }
}
