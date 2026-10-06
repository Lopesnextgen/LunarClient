package com.moonsworth.lunar.client.event;

import lombok.Generated;

public class CancellableEvent extends LunarEvent {
   private boolean cancelled;

   public CancellableEvent() {
   }

   public void cancel() {
      this.cancelled = true;
   }

   @Generated
   public boolean isCancelled() {
      return this.cancelled;
   }

   @Generated
   public void setCancelled(boolean flag) {
      this.cancelled = flag;
   }
}
