package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public abstract class EventRenderScreenOverlay extends LunarEvent {
   private final GuiScreenBridge field1;

   @Generated
   private EventRenderScreenOverlay(GuiScreenBridge bridge5extension61) {
      this.field1 = bridge5extension61;
   }

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   public static class EventScreenInitPre extends EventRenderScreenOverlay {
      public EventScreenInitPre(GuiScreenBridge bridge5extension61) {
         super(bridge5extension61);
      }
   }

   public static class EventScreenInitPost extends EventRenderScreenOverlay {
      public EventScreenInitPost(GuiScreenBridge bridge5extension61) {
         super(bridge5extension61);
      }
   }
}
