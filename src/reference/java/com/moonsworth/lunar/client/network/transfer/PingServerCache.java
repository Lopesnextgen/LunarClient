package com.moonsworth.lunar.client.network.transfer;

import com.google.common.collect.Maps;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Map;
import java.util.UUID;

public class PingServerCache extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, UUID> implements EventBusAccess {
   public PingServerCache() {
      if (Ref.MC_VERSION == 0) {
         this.handle(HighlightImpl_3.class, this::method2);
         this.handle(EventServerChange.class, this::method3);
      }
   }

   @Override
   protected Map<String, UUID> method3() {
      return Maps.newHashMap();
   }

   private void method2(HighlightImpl_3 highlightimpl_31) {
   }

   private void method3(EventServerChange event) {
      this.clear();
   }
}
