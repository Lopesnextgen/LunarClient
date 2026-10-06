package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.time.Duration;
import java.util.concurrent.Future;

public class Fpsdebugmod4Iterator2 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
   @Override
   public String name() {
      return "mods-enabled";
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
         StringBuilder var1 = new StringBuilder();

         for (Framework7Extension var3 : ThreadModuleDump63.method4().method40().method1()) {
            this.method5(var1, var3);
            var3.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field5).ifPresent(var2 -> var2.ORCHOHHCOHCORRICRIHCHHRORHHCHH(var2x -> {
               this.method5(var1, var2x);
               return true;
            }));
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("mods-enabled.txt", var1.toString());
      });
   }

   private void method5(StringBuilder var1, Framework7Extension var2) {
      var1.append(this.method6(var2))
         .append(var2.getClass().getName())
         .append(" [id=")
         .append(var2.getId())
         .append("]")
         .append(" enabled=")
         .append(var2.isEnabled())
         .append(" dynamic=")
         .append(var2.method2(Framework.field19))
         .append(" eventsRegistered=")
         .append(var2.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field12).map(Framework10Extension::method12).orElse(false))
         .append("\n");
   }

   private String method6(Framework7Extension var1) {
      int var2 = this.method7(var1);
      if (var2 == 0) {
         return "";
      } else {
         return var2 == 1 ? " - " : "  " + "|-".repeat(Math.max(0, var2 - 1)) + " ";
      }
   }

   private int method7(Framework7Extension var1) {
      return var1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field16).map(Framework4::method1).map(var1x -> this.method7(var1x) + 1).orElse(0);
   }
}
