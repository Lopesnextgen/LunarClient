package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class LookAtTargetTask extends AbstractTask {
   public LookAtTargetTask() {
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter handler) {
      if (inactive31.method38() != null) {
         inactive31.method22(inactive31.method38());
      }
   }

   @Override
   public String toString() {
      return "LookAtTargetTask";
   }
}
