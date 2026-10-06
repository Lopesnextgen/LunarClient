package com.moonsworth.lunar.client.framework.feature.rewind.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump18;

public class Fishing3 {
   public static <T> T method1(ThreadModuleDump18 var0, T var1, T var2, T var3, T var4, float var5) {
      Class var6 = var2.getClass();
      if (var6 == Double.class) {
         return (T)var0.interpolate((Double)var1, (Double)var2, (Double)var3, (Double)var4, var5);
      } else if (var6 == Float.class) {
         return (T)var0.interpolate((Float)var1, (Float)var2, (Float)var3, (Float)var4, var5);
      } else if (var6 == Integer.class) {
         return (T)(int)var0.interpolate(((Integer)var1).intValue(), ((Integer)var2).intValue(), ((Integer)var3).intValue(), ((Integer)var4).intValue(), var5);
      } else if (var6 == Long.class) {
         return (T)(long)var0.interpolate(
            (float)((Long)var1).longValue(), (float)((Long)var2).longValue(), (float)((Long)var3).longValue(), (float)((Long)var4).longValue(), var5
         );
      } else {
         throw new IllegalArgumentException("Unsupported number type: " + var6.getSimpleName());
      }
   }
}
