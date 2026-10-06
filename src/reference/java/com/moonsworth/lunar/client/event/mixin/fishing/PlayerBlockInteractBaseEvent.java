package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class PlayerBlockInteractBaseEvent extends Highlight {
   private final Vector3iBridge field1;
   private final HitcolorExtension field2;

   @Generated
   public Vector3iBridge method1() {
      return this.field1;
   }

   @Generated
   public HitcolorExtension method2() {
      return this.field2;
   }

   @Generated
   public PlayerBlockInteractBaseEvent(Vector3iBridge var1, HitcolorExtension var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static class EventBlockInteract extends PlayerBlockInteractBaseEvent {
      public EventBlockInteract(Vector3iBridge var1, HitcolorExtension var2) {
         super(var1, var2);
      }
   }

   public static class EventBlockInteractExtended extends PlayerBlockInteractBaseEvent {
      @Nullable
      private final HitcolorExtension field3;

      public EventBlockInteractExtended(Vector3iBridge var1, HitcolorExtension var2, HitcolorExtension hitcolor) {
         super(var1, var2);
         this.field3 = hitcolor;
      }

      @Nullable
      @Generated
      public HitcolorExtension method3() {
         return this.field3;
      }
   }
}
