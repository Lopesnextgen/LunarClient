package com.moonsworth.lunar.client.highlight;

import lombok.Generated;

public class HighlightImpl extends Highlight {
   private boolean cancelled;

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
