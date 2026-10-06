package com.moonsworth.lunar.client.event.mixin.rewindhandlers;

import lombok.Generated;

public class EventPerspectiveChangeLegacy extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private int field1;

   public int method1() {
      int var1 = this.field1 + 1;
      if (var1 > 2) {
         var1 = 0;
      }

      return var1;
   }

   @Generated
   public EventPerspectiveChangeLegacy(int var1) {
      this.field1 = var1;
   }

   @Generated
   public EventPerspectiveChangeLegacy() {
   }

   @Generated
   public int method2() {
      return this.field1;
   }
}
