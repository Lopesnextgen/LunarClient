package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public abstract class AbstractTask implements InactiveTask {
   @JsonProperty("continuous")
   private boolean field1 = false;

   public AbstractTask() {
   }

   @Override
   public boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return true;
   }

   @Override
   public boolean isCancellable() {
      return this.field1;
   }

   @Override
   public boolean method1(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return !this.field1;
   }

   @Override
   public void method3(EmoteDefinition inactive31, PathFilter holograms3handler2) {
   }
}
