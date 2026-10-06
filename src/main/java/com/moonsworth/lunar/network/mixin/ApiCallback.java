package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public interface ApiCallback<T> {
   void method1(ApiException mixinhelperexception1, int number2, Map<String, List<String>> map3);

   void method2(T value1, int number2, Map<String, List<String>> map3);

   void method3(long number1, long number3, boolean flag5);

   void method4(long number1, long number3, boolean flag5);
}
