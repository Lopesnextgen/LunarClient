package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public abstract class EventRenderContainerSlot extends CancellableEvent {
   private final com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 field1;
   private final float field2;
   private final GuiScreenBridge field3;
   private final AbstractRenderContext field4;
   private final MixinHelper_4 field5;

   @Generated
   private EventRenderContainerSlot(
      com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data41, float value2, GuiScreenBridge bridge5extension63, AbstractRenderContext bridgeextension_94, MixinHelper_4 mixinhelper_45
   ) {
      this.field1 = data41;
      this.field2 = value2;
      this.field3 = bridge5extension63;
      this.field4 = bridgeextension_94;
      this.field5 = mixinhelper_45;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public GuiScreenBridge method3() {
      return this.field3;
   }

   @Generated
   public AbstractRenderContext method4() {
      return this.field4;
   }

   @Generated
   public MixinHelper_4 method5() {
      return this.field5;
   }

   public static class ItemRender extends EventRenderContainerSlot {
      public ItemRender(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data41, float value2, GuiScreenBridge bridge5extension63, AbstractRenderContext bridgeextension_94, MixinHelper_4 mixinhelper_45) {
         super(data41, value2, bridge5extension63, bridgeextension_94, mixinhelper_45);
      }
   }

   public static class EventRenderContainerSlotAfterItems extends EventRenderContainerSlot {
      public EventRenderContainerSlotAfterItems(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data41, float value2, GuiScreenBridge bridge5extension63, AbstractRenderContext bridgeextension_94, MixinHelper_4 mixinhelper_45) {
         super(data41, value2, bridge5extension63, bridgeextension_94, mixinhelper_45);
      }
   }

   public static class EventRenderContainerSlotPost extends EventRenderContainerSlot {
      public EventRenderContainerSlotPost(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data41, float value2, GuiScreenBridge bridge5extension63, AbstractRenderContext bridgeextension_94, MixinHelper_4 mixinhelper_45) {
         super(data41, value2, bridge5extension63, bridgeextension_94, mixinhelper_45);
      }
   }

   public static class EventRenderContainerSlotPre extends EventRenderContainerSlot {
      public EventRenderContainerSlotPre(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data41, float value2, GuiScreenBridge bridge5extension63, AbstractRenderContext bridgeextension_94, MixinHelper_4 mixinhelper_45) {
         super(data41, value2, bridge5extension63, bridgeextension_94, mixinhelper_45);
      }
   }
}
