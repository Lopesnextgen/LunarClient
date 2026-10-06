package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import java.time.Instant;

public class Killsounds2 {
   private final Killsounds2.Type field1;
   private final Instant field2;

   public Killsounds2(Killsounds2.Type var1) {
      this(var1, Instant.now());
   }

   public Killsounds2(Killsounds2.Type var1, Instant instant) {
      this.field1 = var1;
      this.field2 = instant;
   }

   public Killsounds2.Type method1() {
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
   }
}
