package com.moonsworth.lunar.client.framework.feature.killsounds;

import java.time.Instant;

public class KillEvent {
   private final KillEvent.Type field1;
   private final Instant field2;

   public KillEvent(KillEvent.Type type1) {
      this(type1, Instant.now());
   }

   public KillEvent(KillEvent.Type type1, Instant instant2) {
      this.field1 = type1;
      this.field2 = instant2;
   }

   public KillEvent.Type method1() {
      return this.field1;
   }

   public Instant method2() {
      return this.field2;
   }

   public enum Type {
      MELEE,
      ARROW,
      ROD,
      THROWABLE;

      Type() {
      }
   }
}
