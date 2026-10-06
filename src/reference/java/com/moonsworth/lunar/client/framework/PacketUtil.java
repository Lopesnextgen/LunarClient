package com.moonsworth.lunar.client.framework;

import com.google.protobuf.RpcCallback;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import java.util.function.Consumer;

public class PacketUtil {
   public PacketUtil() {
   }

   public static <T> void method1(T value0, Consumer<T> consumer1) {
      Ref.method3().bridge$submit(() -> {
         try {
            consumer1.accept(value0);
         } catch (Exception exception3) {
            CrashReporter.method5(exception3, "PacketUtil");
         }
      });
   }

   public static <T> void method2(T value0, RpcCallback<T> rpccallback1) {
      Ref.method3().bridge$submit(() -> {
         try {
            rpccallback1.run(value0);
         } catch (Exception exception3) {
            CrashReporter.method5(exception3, "PacketUtil");
         }
      });
   }
}
