package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.PickaxeTier;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.files.ValuePair;
import java.util.concurrent.TimeUnit;

public class RouteRequirements {
   private final Cache<DungeonRoute, Boolean> field1 = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.SECONDS).build();
   private final DungeonMapListener field2;
   private PickaxeTier field3 = PickaxeTier.NONE;

   public RouteRequirements(DungeonMapListener guirewindhandlershandler2_21) {
      this.field2 = guirewindhandlershandler2_21;
   }

   public void method1(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41.bridge$getItem() != null && this.field3 != PickaxeTier.DUNGEONBREAKER) {
         PickaxeTier nameplatetype22 = PickaxeTier.from(bridgeextension_41);
         if (nameplatetype22 == PickaxeTier.DUNGEONBREAKER) {
            this.field3 = nameplatetype22;
         }
      }
   }

   public boolean method2(DungeonRoute holograms21) {
      try {
         return this.field2.method5().isEmpty() ? false : Boolean.TRUE.equals(this.field1.get(holograms21, () -> {
            for (RouteSection holograms73x : holograms21.getSections()) {
               if (!this.method4(holograms73x)) {
                  return false;
               }
            }

            return true;
         }));
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   public boolean method3(DungeonRoute holograms21, RouteSection holograms72) {
      if (this.field2.method5().isEmpty()) {
         return false;
      }

      boolean flag3 = false;

      for (RouteSection holograms75 : holograms21.getSections()) {
         if (holograms75 == holograms72 || flag3) {
            flag3 = true;
            if (!this.method4(holograms75)) {
               return false;
            }
         }
      }

      return true;
   }

   private boolean method4(RouteSection holograms71) {
      if (this.field3 == PickaxeTier.DUNGEONBREAKER) {
         return true;
      }

      for (ValuePair files6_23 : holograms71.method9()) {
         if (files6_23.field1 == NameplateType.BREAK_BLOCK) {
            return false;
         }
      }

      return true;
   }
}
