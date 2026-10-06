package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

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

   public MoveToOwnerTask() {
   }

   @Override
   public boolean method1(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return holograms3handler2.method5().isDone();
   }

   @Override
   public boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      if (holograms3handler2.method5().method13(inactive31.method29(), this.distance)) {
         holograms3handler2.method6().put("lastBlockPos", inactive31.method29().bridge$getBlockPos());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method3(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      holograms3handler2.method5().stop();
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      TurboPathFollower holograms2_43 = holograms3handler2.method5();
      if (holograms3handler2.method6().get("lastBlockPos") instanceof Horsestats20Extension2 horsestats20extension24) {
         int number6 = Math.max(2, this.distance);
         if (inactive31.method29().bridge$getBlockPos().ORICHRORRORHORHOIHCRHOORCRRHOI(horsestats20extension24) >= number6 * number6) {
            if (this.field2 != null && this.field2.evaluate(inactive31.method30().method9()) != 1.0) {
               holograms2_43.stop();
            } else {
               holograms2_43.method34(inactive31.method29().bridge$getBlockPos());
               holograms2_43.method3();
            }
         }
      }

      holograms2_43.tick();
   }

   @Override
   public String toString() {
      return "MoveToOwnerTask{distance=" + this.distance + "}";
   }
}
