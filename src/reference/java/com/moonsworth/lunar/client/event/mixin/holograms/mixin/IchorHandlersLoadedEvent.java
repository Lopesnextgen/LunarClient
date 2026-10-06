package com.moonsworth.lunar.client.event.mixin.holograms.mixin;

import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import java.util.Map;
import lombok.Generated;

public class IchorHandlersLoadedEvent extends Highlight {
   private final Map<String, Ichor5Handler_2> field1;

   @Generated
   public Map<String, Ichor5Handler_2> method1() {
      return this.field1;
   }

   @Generated
   public IchorHandlersLoadedEvent(Map<String, Ichor5Handler_2> map) {
      this.field1 = map;
   }
}
