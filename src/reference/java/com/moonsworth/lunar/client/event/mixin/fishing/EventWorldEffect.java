package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventWorldEffect extends LunarEvent {
   private final Bridge6_10 field1;
   private final int field2;
   private final Horsestats20Extension2 field3;
   private final int field4;

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public int getType() {
      return this.field2;
   }

   @Generated
   public Horsestats20Extension2 method2() {
      return this.field3;
   }

   @Generated
   public int getData() {
      return this.field4;
   }

   @Generated
   public EventWorldEffect(Bridge6_10 bridge6_101, int value, Horsestats20Extension2 horsestats20Extension2, int value2) {
      this.field1 = bridge6_101;
      this.field2 = value;
      this.field3 = horsestats20Extension2;
      this.field4 = value2;
   }
}
