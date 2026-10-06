package com.moonsworth.lunar.client.mod.render;

import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.kyori.adventure.text.Component;

public class NametagOverrideStore extends com.moonsworth.lunar.client.framework.ItemMapHandler<UUID, List<Component>> implements EventRegistrar {
   public NametagOverrideStore() {
      this.handle(ServerChangeEvent.class, this::method1);
   }

   private void method1(ServerChangeEvent highlightImpl10) {
      this.clear();
   }

   @Override
   protected Map<UUID, List<Component>> method3() {
      return new ConcurrentHashMap<>();
   }
}
