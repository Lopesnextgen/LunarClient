package com.moonsworth.lunar.client.event.mixin.fishing;

import lombok.Generated;

public class EventWorldTimeUpdate extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final long field1;
   private final long field2;

   @Generated
   public EventWorldTimeUpdate(long value, long value2) {
      this.field1 = value;
      this.field2 = value2;
   }

   @Generated
   public long getWorldTime() {
      return this.field1;
   }

   @Generated
   public long method1() {
      return this.field2;
   }
}
