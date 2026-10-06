package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

@VersionGate(max = 5)
public class EventRenderEntityItemLegacy extends LunarEvent {
   private final BridgeExtension field1;
   private final ItemStackBridge field2;
   private final AbstractRenderContext field3;

   @Generated
   public EventRenderEntityItemLegacy(BridgeExtension bridge, ItemStackBridge bridgeextension_42, AbstractRenderContext bridgeextension_93) {
      this.field1 = bridge;
      this.field2 = bridgeextension_42;
      this.field3 = bridgeextension_93;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public ItemStackBridge getItem() {
      return this.field2;
   }

   @Generated
   public AbstractRenderContext method2() {
      return this.field3;
   }
}
