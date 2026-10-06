package com.moonsworth.lunar.client.util;

import java.util.List;
import lombok.Generated;

public final class ThreadModuleDump53 {
   public static <T> T method1(List<T> list, int value) {
      int var2 = list.size();
      Object var3 = list.get(value);
      list.set(value, list.get(var2 - 1));
      list.remove(var2 - 1);
      return (T)var3;
   }

   @Generated
   private ThreadModuleDump53() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
