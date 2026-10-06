package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class HudTimerTicker implements EventBusAccess {
   public static final Set<HudTimer> field1 = Collections.newSetFromMap(new WeakHashMap<>());

   public HudTimerTicker() {
      this.handle(EventServerTick.class, this::method1);
   }

   private void method1(EventServerTick event) {
      field1.forEach(HudTimer::method3);
   }
}
