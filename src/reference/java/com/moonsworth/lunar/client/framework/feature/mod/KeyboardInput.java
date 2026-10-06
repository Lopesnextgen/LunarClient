package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VersionGate(min = 1)
public class KeyboardInput extends GuiComponent {
   private Map<String, List<KeyEventBridge>> previousKeyEvents = new HashMap<>();
   private Map<String, List<KeyEventBridge>> keyEvents = new HashMap<>();

   public KeyboardInput(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void start() {
      this.previousKeyEvents = this.keyEvents;
      this.keyEvents = new HashMap<>();
   }

   public boolean isShiftDown() {
      return LcuiScreen.isShiftKeyDown();
   }

   public boolean addKeyEvent(String text, KeyEventBridge bridge_72) {
      if (!this.keyEvents.containsKey(text)) {
         this.keyEvents.put(text, new ArrayList<>());
      }

      this.keyEvents.get(text).add(bridge_72);
      return this.method7().method5(text);
   }

   public List<KeyEventBridge> getPreviousKeyEvents() {
      return !this.previousKeyEvents.containsKey(this.method15()) ? new ArrayList<>() : this.previousKeyEvents.get(this.method15());
   }
}
