package com.moonsworth.lunar.client.util.collection;

import java.util.List;
import lombok.Generated;

public final class ListUtils {
   public static <T> T method1(List<T> list, int index1) {
      int index2 = list.size();
      Object obj3 = list.get(index1);
      list.set(index1, list.get(index2 - 1));
      list.remove(index2 - 1);
      return (T)obj3;
   }

   @Generated
   private ListUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
