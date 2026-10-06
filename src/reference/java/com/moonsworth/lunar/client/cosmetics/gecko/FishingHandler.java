package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.render.turbo.TurboPathFollower;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.WanderPositionResolver;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class FishingHandler implements InactiveTask {
   @JsonProperty("minRadius")
   private int field1 = 3;
   @JsonProperty("maxRadius")
   private int field2 = 6;
   @JsonProperty("accuracy")
   private int field3 = 1;
   @JsonProperty("follow_range")
   private int field4 = 16;

   @Override
   public boolean method1(EmoteDefinition var1, PathFilter var2) {
      return var2.method5().isDone();
   }

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 == null) {
         return false;
      }

      TurboPathFollower var4 = var2.method5();

      for (int var5 = 0; var5 < 6; var5++) {
         Horsestats20Extension2 var6 = WanderPositionResolver.method1(var1, var2, this.field1, this.field2, 1);
         if (var6 != null && var4.method14(var4.method8(var6, this.field3, this.field4))) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void method3(EmoteDefinition var1, PathFilter var2) {
      var2.method5().stop();
   }

   @Override
   public void method6(EmoteDefinition var1, PathFilter var2) {
      var2.method5().tick();
   }

   @Override
   public String toString() {
      return "RandomWanderTask{minRadius=" + this.field1 + ",maxRadius=" + this.field2 + ",accuracy=" + this.field3 + ",followRange=" + this.field4 + "}";
   }
}
