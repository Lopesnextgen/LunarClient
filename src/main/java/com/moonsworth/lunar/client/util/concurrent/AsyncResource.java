package com.moonsworth.lunar.client.util.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.TextureQuality;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public interface AsyncResource<T> {
   ExecutorService field1 = Executors.newFixedThreadPool(method1(), new ThreadFactoryBuilder().setNameFormat("lunar-async-resource").setDaemon(true).build());

   private static int method1() {
      int number0 = Runtime.getRuntime().availableProcessors();
      if (number0 >= 16) {
         return 6;
      } else if (number0 >= 8) {
         return 4;
      } else {
         return number0 <= 4 ? 2 : 3;
      }
   }

   void method2(boolean flag1);

   boolean method4();

   T get();

   Optional<T> method4(TextureQuality bridgetype2_51);

   void method5(Consumer<T> consumer1);

   void method6(Consumer<T> consumer1);

   void method7(Bridge11_2 bridge11_21, TextureQuality bridgetype2_52);

   TextureQuality method8();
}
