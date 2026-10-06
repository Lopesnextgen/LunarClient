package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class LookAtTargetTask extends AbstractTask {
   @Override
   public void method6(EmoteDefinition inactive3, PathFilter handler) {
      if (inactive3.method38() != null) {
         inactive3.method22(inactive3.method38());
      }
   }

   @Override
   public String toString() {
      return "LookAtTargetTask";
   }
}
