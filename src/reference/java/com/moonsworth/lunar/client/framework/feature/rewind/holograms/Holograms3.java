package com.moonsworth.lunar.client.framework.feature.rewind.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator22;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_2;
import java.util.HashMap;
import java.util.Map;

public class Holograms3 {
   private static final Map<String, Class<?>> field1 = new HashMap<>();

   private static void method1(RewindIterator<?> var0) {
      field1.put(var0.type(), var0.getClass());
   }

   public static Class<?> getType(String var0) {
      return field1.get(var0);
   }

   static {
      method1(new RewindIterator23(null, null));
      method1(new Rewind_2(null));
      method1(new RewindImpl2(null));
      method1(new RewindImpl(null));
      method1(new RewindIterator22(null));
   }
}
