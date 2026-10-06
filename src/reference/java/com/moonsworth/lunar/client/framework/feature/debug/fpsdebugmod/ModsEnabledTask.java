package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import java.time.Duration;
import java.util.concurrent.Future;

public class ModsEnabledTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public ModsEnabledTask() {
   }

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
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PRE_COLLECT;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return BackgroundExecutor.method12(() -> {
         StringBuilder builder1 = new StringBuilder();

         for (Framework7Extension framework7extension3 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            this.method5(builder1, framework7extension3);
            framework7extension3.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg2 -> arg2.ORCHOHHCOHCORRICRIHCHHRORHHCHH(arg2x -> {
               this.method5(builder1, arg2x);
               return true;
            }));
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("mods-enabled.txt", builder1.toString());
      });
   }

   private void method5(StringBuilder builder1, Framework7Extension framework7extension2) {
      builder1.append(this.method6(framework7extension2))
         .append(framework7extension2.getClass().getName())
         .append(" [id=")
         .append(framework7extension2.getId())
         .append("]")
         .append(" enabled=")
         .append(framework7extension2.isEnabled())
         .append(" dynamic=")
         .append(framework7extension2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field19))
         .append(" eventsRegistered=")
         .append(framework7extension2.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field12).map(ModLifecycle::method12).orElse(false))
         .append("\n");
   }

   private String method6(Framework7Extension framework7extension1) {
      int number2 = this.method7(framework7extension1);
      if (number2 == 0) {
         return "";
      } else {
         return number2 == 1 ? " - " : "  " + "|-".repeat(Math.max(0, number2 - 1)) + " ";
      }
   }

   private int method7(Framework7Extension framework7extension1) {
      return framework7extension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field16).map(ChildModBinding::method1).map(arg1x -> this.method7(arg1x) + 1).orElse(0);
   }
}
