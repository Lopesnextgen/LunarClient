package com.moonsworth.lunar.client.event.resourcepack;

import com.moonsworth.lunar.bridge.IResourcePackBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ResourcePackUpdateEvent extends Highlight {
   private final IResourcePackBridge field1;

   @Generated
   public IResourcePackBridge method1() {
      return this.field1;
   }

   @Generated
   public ResourcePackUpdateEvent(IResourcePackBridge iResourcePackBridge) {
      this.field1 = iResourcePackBridge;
   }
}
