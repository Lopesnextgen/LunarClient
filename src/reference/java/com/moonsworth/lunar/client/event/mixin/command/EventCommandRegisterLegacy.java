package com.moonsworth.lunar.client.event.mixin.command;

import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import java.util.List;

public class EventCommandRegisterLegacy extends Highlight {
   private final List<MixinNameplate2> field1;

   public EventCommandRegisterLegacy(List<MixinNameplate2> var1) {
      this.field1 = var1;
   }

   public void method1(MixinNameplate2 var1) {
      this.field1.add(var1);
   }
}
