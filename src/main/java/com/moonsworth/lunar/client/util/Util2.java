package com.moonsworth.lunar.client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public interface Util2<T> {
   ExecutorService field1 = Executors.newFixedThreadPool(method1(), new ThreadFactoryBuilder().setNameFormat("lunar-async-resource").setDaemon(true).build());

   private static int method1() {
      int var0 = Runtime.getRuntime().availableProcessors();
      if (var0 >= 16) {
         return 6;
      } else if (var0 >= 8) {
         return 4;
      } else {
         return var0 <= 4 ? 2 : 3;
      }
   }

   void method2(boolean var1);

   boolean method4();

   T get();

   Optional<T> method4(BridgeType2_5 var1);

   void method5(Consumer<T> var1);

   void method6(Consumer<T> var1);

   void method7(Bridge11_2 var1, BridgeType2_5 var2);

   BridgeType2_5 method8();
}
