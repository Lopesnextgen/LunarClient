package com.moonsworth.lunar.client.event.mixin.highlight;

import java.util.concurrent.CompletableFuture;
import lombok.Generated;

public class AlertUpdateEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final CompletableFuture<Void> future;

   @Generated
   public AlertUpdateEvent(CompletableFuture<Void> completableFuture) {
      this.future = completableFuture;
   }

   @Generated
   public CompletableFuture<Void> getFuture() {
      return this.future;
   }
}
