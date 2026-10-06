package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import lombok.Generated;

public class EventPlayerSleep extends EventPlayerState {
   private final Bridge6_10 field1;
   private EventPlayerSleep.SleepStatus field2;
   private final Horsestats20Extension2 field3;

   public EventPlayerSleep(Bridge6_10 bridge6_101, Horsestats20Extension2 horsestats20Extension2) {
      this.field1 = bridge6_101;
      this.field3 = horsestats20Extension2;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public EventPlayerSleep.SleepStatus method2() {
      return this.field2;
   }

   @Generated
   public Horsestats20Extension2 method3() {
      return this.field3;
   }

   @Generated
   public void method4(EventPlayerSleep.SleepStatus sleepStatus) {
      this.field2 = sleepStatus;
   }

   public enum SleepStatus {
      OK,
      NOT_POSSIBLE_HERE,
      NOT_POSSIBLE_NOW,
      TOO_FAR_AWAY,
      OTHER_PROBLEM,
      NOT_SAFE;

      SleepStatus() {
      }
   }
}
