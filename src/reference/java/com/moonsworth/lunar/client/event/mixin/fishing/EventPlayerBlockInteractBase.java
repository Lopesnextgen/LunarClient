package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class EventPlayerBlockInteractBase extends LunarEvent {
   private final Vec3iBridge field1;
   private final BlockEntityBridge field2;

   @Generated
   public Vec3iBridge method1() {
      return this.field1;
   }

   @Generated
   public BlockEntityBridge method2() {
      return this.field2;
   }

   @Generated
   public EventPlayerBlockInteractBase(Vec3iBridge horsestats201, BlockEntityBridge hitcolorextension2) {
      this.field1 = horsestats201;
      this.field2 = hitcolorextension2;
   }

   public static class EventPlayerBlockInteract extends EventPlayerBlockInteractBase {
      public EventPlayerBlockInteract(Vec3iBridge horsestats201, BlockEntityBridge hitcolorextension2) {
         super(horsestats201, hitcolorextension2);
      }
   }

   public static class EventPlayerBlockInteractExtended extends EventPlayerBlockInteractBase {
      @Nullable
      private final BlockEntityBridge field3;

      public EventPlayerBlockInteractExtended(Vec3iBridge horsestats201, BlockEntityBridge hitcolorextension2, BlockEntityBridge entity) {
         super(horsestats201, hitcolorextension2);
         this.field3 = entity;
      }

      @Nullable
      @Generated
      public BlockEntityBridge method3() {
         return this.field3;
      }
   }
}
