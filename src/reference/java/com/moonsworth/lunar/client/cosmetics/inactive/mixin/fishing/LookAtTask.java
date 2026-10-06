package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;

public class LookAtTask extends AbstractTask {
   @JsonProperty("yaw")
   private Evaluatable field2;

   public LookAtTask() {
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      EvaluatorImpl evaluatorimpl3 = inactive31.method30().method9();
      inactive31.method6((float)this.field2.evaluate(evaluatorimpl3));
   }

   @Override
   public String toString() {
      return "LookAtTask";
   }
}
