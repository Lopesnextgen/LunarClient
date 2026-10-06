package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

@Annotation2(max = 5)
public class RenderEntityItemLegacyEvent extends Highlight {
   private final BridgeExtension field1;
   private final ItemStackBridge field2;
   private final AbstractRenderContext field3;

   @Generated
   public RenderEntityItemLegacyEvent(BridgeExtension bridge, ItemStackBridge itemStackBridge, AbstractRenderContext abstractRenderContext) {
      this.field1 = bridge;
      this.field2 = itemStackBridge;
      this.field3 = abstractRenderContext;
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
