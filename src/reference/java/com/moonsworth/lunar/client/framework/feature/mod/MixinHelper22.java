package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Annotation2(min = 1)
public class MixinHelper22 extends MixinHelper2_3 {
   private Map<String, List<Bridge_7>> previousKeyEvents = new HashMap<>();
   private Map<String, List<Bridge_7>> keyEvents = new HashMap<>();

   public MixinHelper22(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void start() {
      this.previousKeyEvents = this.keyEvents;
      this.keyEvents = new HashMap<>();
   }

   public boolean method17() {
      return LcuiScreen.isShiftKeyDown();
   }

   public boolean addKeyEvent(String var1, Bridge_7 bridge_7) {
      if (!this.keyEvents.containsKey(var1)) {
         this.keyEvents.put(var1, new ArrayList<>());
      }

      this.keyEvents.get(var1).add(bridge_7);
      return this.method7().method5(var1);
   }

   public List<Bridge_7> method18() {
      return !this.previousKeyEvents.containsKey(this.method15()) ? new ArrayList<>() : this.previousKeyEvents.get(this.method15());
   }
}
