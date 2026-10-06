package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.inactive.mixin.holograms.EvaluatorImpl;

public class LookAtBlockTask extends AbstractTask {
   @JsonProperty(value = "x", required = true)
   private Evaluatable field2;
   @JsonProperty(value = "z", required = true)
   private Evaluatable field3;

   @Override
   public void method6(EmoteDefinition var1, PathFilter var2) {
      EvaluatorImpl var3 = var1.method30().method9();
      var1.method23(this.field2.evaluate(var3), this.field3.evaluate(var3));
   }

   @Override
   public String toString() {
      return "LookAtBlockTask";
   }
}
