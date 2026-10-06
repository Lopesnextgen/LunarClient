package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.client.highlight.Highlight;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class ProjectileBaseEvent extends Highlight {
   private BridgeExtension2_3 field1;

   @Generated
   public BridgeExtension2_3 method1() {
      return this.field1;
   }

   @Generated
   private ProjectileBaseEvent(BridgeExtension2_3 var1) {
      this.field1 = var1;
   }

   public static class EventProjectileLaunch extends ProjectileBaseEvent {
      @Nullable
      private final BridgeExtension field2;

      public EventProjectileLaunch(BridgeExtension2_3 var1, @Nullable BridgeExtension var2) {
         super(var1);
         this.field2 = var2;
      }

      @Nullable
      @Generated
      public BridgeExtension method2() {
         return this.field2;
      }
   }

   public static class EventProjectileImpact extends ProjectileBaseEvent {
      private final BridgeExtension field2;

      public EventProjectileImpact(BridgeExtension2_3 var1, BridgeExtension var2) {
         super(var1);
         this.field2 = var2;
      }

      @Generated
      public BridgeExtension method2() {
         return this.field2;
      }
   }

   public static class EventProjectileRemoval extends ProjectileBaseEvent {
      public EventProjectileRemoval(BridgeExtension2_3 var1) {
         super(var1);
      }
   }
}
