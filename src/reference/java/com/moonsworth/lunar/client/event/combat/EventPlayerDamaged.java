package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventPlayerDamaged extends Highlight {
   private final Bridge6_10 field1;
   private final DamageSourceQuery field2;

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public DamageSourceQuery method2() {
      return this.field2;
   }

   @Generated
   public EventPlayerDamaged(Bridge6_10 bridge6_10, DamageSourceQuery damageSourceQuery) {
      this.field1 = bridge6_10;
      this.field2 = damageSourceQuery;
   }
}
