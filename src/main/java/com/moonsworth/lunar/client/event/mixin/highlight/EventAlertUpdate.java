package com.moonsworth.lunar.client.event.mixin.highlight;

import java.util.concurrent.CompletableFuture;
import lombok.Generated;

public class EventAlertUpdate extends com.moonsworth.lunar.client.event.LunarEvent {
   private final CompletableFuture<Void> future;

   @Generated
   public EventAlertUpdate(CompletableFuture<Void> completablefuture1) {
      this.future = completablefuture1;
   }

   @Generated
   public CompletableFuture<Void> getFuture() {
      return this.future;
   }
}
