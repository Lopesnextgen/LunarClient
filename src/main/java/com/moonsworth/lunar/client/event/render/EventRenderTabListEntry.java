package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.NetworkPlayerInfoBridge;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventRenderTabListEntry extends LunarEvent {
   private final NetworkPlayerInfoBridge field1;
   private final Component field2;
   private Component component;
   private boolean changed;

   public EventRenderTabListEntry(NetworkPlayerInfoBridge bridge_301, Component component2) {
      this.field1 = bridge_301;
      this.field2 = component2;
      this.component = component2;
   }

   public void method1(Component component1) {
      this.component = component1;
      this.changed = true;
   }

   @Generated
   public NetworkPlayerInfoBridge method2() {
      return this.field1;
   }

   @Generated
   public Component method3() {
      return this.field2;
   }

   @Generated
   public Component getComponent() {
      return this.component;
   }

   @Generated
   public boolean isChanged() {
      return this.changed;
   }
}
