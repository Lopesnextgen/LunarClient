package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.mod.misc.EventChest;
import com.moonsworth.lunar.client.mod.misc.EventChestDefinition;
import com.moonsworth.lunar.client.mod.misc.AprilFoolsChest;
import com.moonsworth.lunar.client.mod.misc.AnniversaryChest;
import com.moonsworth.lunar.client.config.FeatureFlag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventChestRegistry implements LoadableHandler {
   private final Map<String, EventChestDefinition> field1 = new HashMap<>();

   public EventChestRegistry() {
      this.method1();
   }

   public void method1() {
      List var1 = this.method2();
      if (!var1.isEmpty()) {
         for (EventChestDefinition var3 : var1) {
            this.field1.put(var3.id(), var3);
         }
      }
   }

   private List<EventChestDefinition> method2() {
      Builder var1 = new Builder();
      if (EventChest.field1 && FeatureFlag.APRIL_FOOLS_ENTITIES.isEnabled()) {
         var1.add(new AprilFoolsChest());
      }

      if (EventChest.field2 && FeatureFlag.ANNIVERSARY_PARTICLES.isEnabled()) {
         var1.add(new AnniversaryChest());
      }

      return var1.build();
   }

   public void method3(String var1, boolean var2) {
      EventChestDefinition var3 = this.field1.get(var1);
      if (var3 != null && (!var2 || var3.method1())) {
         if (var2) {
            var3.method2();
         } else {
            var3.method3();
         }
      }
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
      for (EventChestDefinition var2 : this.field1.values()) {
         if (var2.method1()) {
            var2.method2();
         }
      }
   }
}
