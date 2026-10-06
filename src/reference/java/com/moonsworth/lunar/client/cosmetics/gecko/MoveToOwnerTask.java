package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.render.turbo.TurboPathFollower;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import org.jspecify.annotations.Nullable;

public class MoveToOwnerTask implements InactiveTask {
   private static final String field1 = "lastBlockPos";
   @JsonProperty("distance")
   private int distance = 4;
   @JsonProperty("should_continue")
   private @Nullable Evaluatable field2 = null;

   @Override
   public boolean method1(EmoteDefinition var1, PathFilter var2) {
      return var2.method5().isDone();
   }

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      if (var2.method5().method13(var1.method29(), this.distance)) {
         var2.method6().put("lastBlockPos", var1.method29().bridge$getBlockPos());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method3(EmoteDefinition var1, PathFilter var2) {
      var2.method5().stop();
   }

   @Override
   public void method6(EmoteDefinition var1, PathFilter var2) {
      TurboPathFollower var3 = var2.method5();
      if (var2.method6().get("lastBlockPos") instanceof Horsestats20Extension2 var4) {
         int var6 = Math.max(2, this.distance);
         if (var1.method29().bridge$getBlockPos().ORICHRORRORHORHOIHCRHOORCRRHOI(var4) >= var6 * var6) {
            if (this.field2 != null && this.field2.evaluate(var1.method30().method9()) != 1.0) {
               var3.stop();
            } else {
               var3.method34(var1.method29().bridge$getBlockPos());
               var3.method3();
            }
         }
      }

      var3.tick();
   }

   @Override
   public String toString() {
      return "MoveToOwnerTask{distance=" + this.distance + "}";
   }
}
