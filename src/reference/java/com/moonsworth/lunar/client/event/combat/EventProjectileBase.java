package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.client.event.LunarEvent;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class EventProjectileBase extends LunarEvent {
   private BridgeExtension2_3 field1;

   @Generated
   public BridgeExtension2_3 method1() {
      return this.field1;
   }

   @Generated
   private EventProjectileBase(BridgeExtension2_3 bridgeextension2_31) {
      this.field1 = bridgeextension2_31;
   }

   public static class EventProjectileSpawn extends EventProjectileBase {
      @Nullable
      private final BridgeExtension field2;

      public EventProjectileSpawn(BridgeExtension2_3 bridgeextension2_31, @Nullable BridgeExtension bridgeextension2) {
         super(bridgeextension2_31);
         this.field2 = bridgeextension2;
      }

      @Nullable
      @Generated
      public BridgeExtension method2() {
         return this.field2;
      }
   }

   public static class EventProjectileHit extends EventProjectileBase {
      private final BridgeExtension field2;

      public EventProjectileHit(BridgeExtension2_3 bridgeextension2_31, BridgeExtension bridgeextension2) {
         super(bridgeextension2_31);
         this.field2 = bridgeextension2;
      }

      @Generated
      public BridgeExtension method2() {
         return this.field2;
      }
   }

   public static class EventProjectileRemove extends EventProjectileBase {
      public EventProjectileRemove(BridgeExtension2_3 bridgeextension2_31) {
         super(bridgeextension2_31);
      }
   }
}
