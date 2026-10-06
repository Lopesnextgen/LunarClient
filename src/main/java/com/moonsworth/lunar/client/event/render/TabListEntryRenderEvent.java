package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge_30;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.highlight.Highlight;

public class TabListEntryRenderEvent extends Highlight {
   private final Bridge_30 field1;
   private final Component field2;
   private Component component;
   private boolean changed;

   public TabListEntryRenderEvent(Bridge_30 var1, Component component2) {
      this.field1 = var1;
      this.field2 = component2;
      this.component = component2;
   }

   public void method1(Component var1) {
      this.component = var1;
      this.changed = true;
   }

   @Generated
   public Bridge_30 method2() {
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
