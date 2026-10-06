package com.moonsworth.lunar.client.event.mixin.gui;

import lombok.Generated;

public class ScreenActionEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Runnable field1;

   public void method1() {
      this.field1.run();
   }

   @Generated
   public ScreenActionEvent(Runnable runnable) {
      this.field1 = runnable;
   }
}
