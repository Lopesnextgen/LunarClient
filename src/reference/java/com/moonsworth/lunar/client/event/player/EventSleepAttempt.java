package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import lombok.Generated;

public class EventSleepAttempt extends PlayerStateEvent {
   private final Bridge6_10 field1;
   private EventSleepAttempt.Type field2;
   private final Horsestats20Extension2 field3;

   public EventSleepAttempt(Bridge6_10 var1, Horsestats20Extension2 horsestats20Extension2) {
      this.field1 = var1;
      this.field3 = horsestats20Extension2;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public EventSleepAttempt.Type method2() {
      return this.field2;
   }

   @Generated
   public Horsestats20Extension2 method3() {
      return this.field3;
   }

   @Generated
   public void method4(EventSleepAttempt.Type var1) {
      this.field2 = var1;
   }

   public enum Type {
      OK,
      NOT_POSSIBLE_HERE,
      NOT_POSSIBLE_NOW,
      TOO_FAR_AWAY,
      OTHER_PROBLEM,
      NOT_SAFE;
   }
}
