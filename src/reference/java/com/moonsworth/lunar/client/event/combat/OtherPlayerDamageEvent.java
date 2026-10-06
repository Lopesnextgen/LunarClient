package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import lombok.Generated;

public class OtherPlayerDamageEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final BridgeExtension2_5 field1;
   private DamageSourceQuery field2;
   private float field3;

   @Generated
   public BridgeExtension2_5 method1() {
      return this.field1;
   }

   @Generated
   public DamageSourceQuery method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public OtherPlayerDamageEvent(BridgeExtension2_5 bridgeExtension2_5, DamageSourceQuery damageSourceQuery, float value) {
      this.field1 = bridgeExtension2_5;
      this.field2 = damageSourceQuery;
      this.field3 = value;
   }
}
