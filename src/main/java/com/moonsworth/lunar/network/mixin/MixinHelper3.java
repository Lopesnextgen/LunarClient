package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public interface MixinHelper3<T> {
   void method1(MixinHelperException var1, int var2, Map<String, List<String>> var3);

   void method2(T var1, int var2, Map<String, List<String>> var3);

   void method3(long var1, long var3, boolean var5);

   void method4(long var1, long var3, boolean var5);
}
