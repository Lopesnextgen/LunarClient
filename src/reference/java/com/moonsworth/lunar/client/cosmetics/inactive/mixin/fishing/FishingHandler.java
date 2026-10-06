package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.render.turbo.TurboPathFollower;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.inactive.WanderPositionResolver;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.framework.Ref;

public class FishingHandler implements InactiveTask {
   @JsonProperty("minRadius")
   private int field1 = 3;
   @JsonProperty("maxRadius")
   private int field2 = 6;
   @JsonProperty("accuracy")
   private int field3 = 1;
   @JsonProperty("follow_range")
   private int field4 = 16;

   public FishingHandler() {
   }

   @Override
   public boolean method1(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return holograms3handler2.method5().isDone();
   }

   @Override
   public boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (itemcounter6extension3 == null) {
         return false;
      }

      TurboPathFollower holograms2_44 = holograms3handler2.method5();

      for (int index5 = 0; index5 < 6; index5++) {
         Horsestats20Extension2 horsestats20extension26 = WanderPositionResolver.method1(inactive31, holograms3handler2, this.field1, this.field2, 1);
         if (horsestats20extension26 != null && holograms2_44.method14(holograms2_44.method8(horsestats20extension26, this.field3, this.field4))) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void method3(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      holograms3handler2.method5().stop();
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      holograms3handler2.method5().tick();
   }

   @Override
   public String toString() {
      return "RandomWanderTask{minRadius=" + this.field1 + ",maxRadius=" + this.field2 + ",accuracy=" + this.field3 + ",followRange=" + this.field4 + "}";
   }
}
