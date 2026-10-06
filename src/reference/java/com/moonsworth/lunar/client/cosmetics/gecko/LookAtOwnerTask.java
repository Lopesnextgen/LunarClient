package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class LookAtOwnerTask extends AbstractTask {
   @Override
   public void method6(EmoteDefinition inactive3, PathFilter handler) {
      Bridge5_11 var3 = inactive3.method29();
      inactive3.method23(var3.bridge$getPosX(), var3.bridge$getPosZ());
   }

   @Override
   public String toString() {
      return "LookAtOwnerTask";
   }
}
