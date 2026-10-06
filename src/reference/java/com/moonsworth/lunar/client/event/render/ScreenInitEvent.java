package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public abstract class ScreenInitEvent extends Highlight {
   private final Bridge5Extension6 field1;

   @Generated
   private ScreenInitEvent(Bridge5Extension6 var1) {
      this.field1 = var1;
   }

   @Generated
   public Bridge5Extension6 method1() {
      return this.field1;
   }

   public static class ScreenInitPreEvent extends ScreenInitEvent {
      public ScreenInitPreEvent(Bridge5Extension6 var1) {
         super(var1);
      }
   }

   public static class ScreenInitPostEvent extends ScreenInitEvent {
      public ScreenInitPostEvent(Bridge5Extension6 var1) {
         super(var1);
      }
   }
}
