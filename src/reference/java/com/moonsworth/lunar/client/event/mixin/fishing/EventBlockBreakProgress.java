package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventBlockBreakProgress extends LunarEvent {
   private final int field1;
   private final Horsestats20Extension2 field2;
   private final int field3;

   @Generated
   public EventBlockBreakProgress(int value, Horsestats20Extension2 horsestats20Extension2, int value2) {
      this.field1 = value;
      this.field2 = horsestats20Extension2;
      this.field3 = value2;
   }

   @Generated
   public int method1() {
      return this.field1;
   }

   @Generated
   public Horsestats20Extension2 method2() {
      return this.field2;
   }

   @Generated
   public int getProgress() {
      return this.field3;
   }
}
