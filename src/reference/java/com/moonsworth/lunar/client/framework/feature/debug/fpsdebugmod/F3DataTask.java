package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.feature.f3display.F3DebugWriter;
import com.moonsworth.lunar.client.framework.feature.f3display.F3DebugLine;
import com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class F3DataTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public F3DataTask() {
   }

   @Override
   public String name() {
      return "f3-data";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofSeconds(20L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return CompletableFuture.supplyAsync(() -> {
         StringBuilder builder1 = new StringBuilder();
         Future future2 = BackgroundExecutor.method15(() -> {
            builder1.append("--- ").append(EventTick.field1).append(" ---").append("\n");

            for (F3DebugLine gui2extension5 : F3DebugLine.values()) {
               builder1.append(gui2extension5.getDisplay()).append(":").append("\n");
               ((BiConsumer)F3Display.extensionRenderers.get(gui2extension5)).accept(new F3DebugWriter() {
                  @Override
                  public F3DebugWriter method3(boolean flag1x, String... items2x) {
                     builder1.append(String.join(" ", items2x)).append("\n");
                     return this;
                  }

                  @Override
                  public F3DebugWriter method4(boolean flag1x, List<ProfilerResultBridge> list2x) {
                     builder1.append("<pieChart>").append("\n");
                     return this;
                  }

                  @Override
                  public F3DebugWriter method5(F3Chart f3display1x) {
                     builder1.append("<chart>").append("\n");
                     return this;
                  }

                  @Override
                  public F3DebugWriter method6(String text1x, String text2x) {
                     return this.HCHHRHHCRIIORRRICOOCCOCHIRRRRR(text1x, F3DebugWriter.method11(text2x));
                  }

                  @Override
                  public boolean method7() {
                     return false;
                  }
               }, null);
               builder1.append("\n");
            }

            com.moonsworth.lunar.client.framework.feature.f3display.F3DebugInfo.field1.clear();
         }, 0, 1);

         try {
            Thread.sleep(20000L);
         } catch (InterruptedException interruptedexception4) {
            throw new RuntimeException(interruptedexception4);
         }

         BackgroundExecutor.method16(future2);
         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("f3-data.txt", builder1.toString());
      });
   }
}
