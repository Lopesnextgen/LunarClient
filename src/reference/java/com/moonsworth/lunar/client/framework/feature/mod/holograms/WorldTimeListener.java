package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTime;
import lombok.Generated;

public class WorldTimeListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private long field7;

   public WorldTimeListener() {
      this.handle(EventWorldTime.class, this::method1);
   }

   private void method1(EventWorldTime event) {
      this.field7 = Math.abs(event.method1());
   }

   @Generated
   public long method5() {
      return this.field7;
   }
}
