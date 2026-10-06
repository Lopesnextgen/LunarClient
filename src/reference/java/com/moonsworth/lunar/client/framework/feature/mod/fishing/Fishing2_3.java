package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class Fishing2_3 {
   private static final List<Supplier<Fishing3_2>> field1 = new ArrayList<Supplier<Fishing3_2>>() {
      {
         this.add(Fishing3Iterator::new);
      }
   };
   public static final int field2 = field1.size();

   public static void method1(Fishing4 fishing4, JsonDeserializerIterator$Data data, int value) {
      Fishing5 var3 = new Fishing5(fishing4, data);

      for (int var4 = value; var4 < field2; var4++) {
         field1.get(var4).get().method1(var3);
      }
   }
}
