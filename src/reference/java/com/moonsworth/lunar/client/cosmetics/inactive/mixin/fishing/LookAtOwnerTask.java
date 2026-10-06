package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class LookAtOwnerTask extends AbstractTask {
   public LookAtOwnerTask() {
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter handler) {
      Bridge5_11 bridge5_113 = inactive31.method29();
      inactive31.method23(bridge5_113.bridge$getPosX(), bridge5_113.bridge$getPosZ());
   }

   @Override
   public String toString() {
      return "LookAtOwnerTask";
   }
}
