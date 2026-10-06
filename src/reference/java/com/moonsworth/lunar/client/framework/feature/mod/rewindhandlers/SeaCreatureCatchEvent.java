package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DoubleHookListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(DoubleHookListener.class)
public class SeaCreatureCatchEvent extends LunarEvent implements DynamicListenerEvent {
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreature field1;
   private boolean field2;

   public int getCount() {
      return this.field2 ? 2 : 1;
   }

   @Generated
   public SeaCreatureCatchEvent(com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreature rewindhandlers1, boolean flag) {
      this.field1 = rewindhandlers1;
      this.field2 = flag;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreature method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }
}
