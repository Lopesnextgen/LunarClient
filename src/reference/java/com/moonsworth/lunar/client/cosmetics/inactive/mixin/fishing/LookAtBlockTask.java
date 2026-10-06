package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;

public class LookAtBlockTask extends AbstractTask {
   @JsonProperty(value = "x", required = true)
   private Evaluatable field2;
   @JsonProperty(value = "z", required = true)
   private Evaluatable field3;

   public LookAtBlockTask() {
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      EvaluatorImpl evaluatorimpl3 = inactive31.method30().method9();
      inactive31.method23(this.field2.evaluate(evaluatorimpl3), this.field3.evaluate(evaluatorimpl3));
   }

   @Override
   public String toString() {
      return "LookAtBlockTask";
   }
}
