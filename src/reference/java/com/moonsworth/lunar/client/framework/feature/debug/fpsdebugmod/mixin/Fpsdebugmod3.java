package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import java.time.Duration;
import java.util.concurrent.Future;

public class Fpsdebugmod3 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
   @Override
   public String name() {
      return "dynamic-listeners";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofMillis(100L);
   }

   @Override
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return ThreadModuleDump37.method12(() -> {
         StringBuilder var0 = new StringBuilder();

         for (String var2 : DynamicListener.method14()) {
            var0.append(var2).append("\n");
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("dynamic-listeners.txt", var0.toString());
      });
   }
}
