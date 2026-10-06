package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.framework.feature.f3display.F3display_3;
import com.moonsworth.lunar.client.framework.feature.f3display.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.hud.f3display.F3Display;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class Fpsdebugmod4 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return CompletableFuture.supplyAsync(() -> {
         StringBuilder var1 = new StringBuilder();
         Future var2 = ThreadModuleDump37.method15(() -> {
            var1.append("--- ").append(EventClientTick.field1).append(" ---").append("\n");

            for (Gui2Extension var5 : Gui2Extension.values()) {
               var1.append(var5.getDisplay()).append(":").append("\n");
               ((BiConsumer)F3Display.extensionRenderers.get(var5)).accept(new F3display_3() {
                  @Override
                  public F3display_3 method3(boolean var1x, String... var2x) {
                     var1.append(String.join(" ", var2x)).append("\n");
                     return this;
                  }

                  @Override
                  public F3display_3 method4(boolean var1x, List<ProfilerResultBridge> var2x) {
                     var1.append("<pieChart>").append("\n");
                     return this;
                  }

                  @Override
                  public F3display_3 method5(F3display var1x) {
                     var1.append("<chart>").append("\n");
                     return this;
                  }

                  @Override
                  public F3display_3 method6(String var1x, String var2x) {
                     return this.HCHHRHHCRIIORRRICOOCCOCHIRRRRR(var1x, F3display_3.method11(var2x));
                  }

                  @Override
                  public boolean method7() {
                     return false;
                  }
               }, null);
               var1.append("\n");
            }

            com.moonsworth.lunar.client.framework.feature.f3display.F3display.field1.clear();
         }, 0, 1);

         try {
            Thread.sleep(20000L);
         } catch (InterruptedException var4) {
            throw new RuntimeException(var4);
         }

         ThreadModuleDump37.method16(var2);
         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("f3-data.txt", var1.toString());
      });
   }
}
