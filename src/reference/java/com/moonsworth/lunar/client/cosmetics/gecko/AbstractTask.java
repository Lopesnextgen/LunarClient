package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public abstract class AbstractTask implements InactiveTask {
   @JsonProperty("continuous")
   private boolean field1 = false;

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      return true;
   }

   @Override
   public boolean isCancellable() {
      return this.field1;
   }

   @Override
   public boolean method1(EmoteDefinition var1, PathFilter var2) {
      return !this.field1;
   }

   @Override
   public void method3(EmoteDefinition var1, PathFilter var2) {
   }
}
