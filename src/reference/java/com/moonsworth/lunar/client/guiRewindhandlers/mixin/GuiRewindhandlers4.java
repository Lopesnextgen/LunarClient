package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class GuiRewindhandlers4 implements EventRegistrar {
   public static final Set<ThreadModuleDump45> field1 = Collections.newSetFromMap(new WeakHashMap<>());

   public GuiRewindhandlers4() {
      this.handle(ServerTickEvent.class, this::method1);
   }

   private void method1(ServerTickEvent highlightImpl9) {
      field1.forEach(ThreadModuleDump45::method3);
   }
}
