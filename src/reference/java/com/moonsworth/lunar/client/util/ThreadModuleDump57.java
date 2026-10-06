package com.moonsworth.lunar.client.util;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public final class ThreadModuleDump57 {
   private static final List<MixinHelper_15> field1 = new ArrayList<>();

   public static MixinHelper_15[] method1(MixinHelper_15[] var0) {
      ArrayList var1 = Lists.newArrayList(var0);
      var1.removeAll(field1);
      var1.addAll(field1);
      return var1.toArray(new MixinHelper_15[0]);
   }

   public static void method2(MixinHelper_15 var0) {
      field1.add(var0);
   }

   @Generated
   private ThreadModuleDump57() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
