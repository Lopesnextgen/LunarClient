package com.moonsworth.lunar.client.inactive.mixin.fishing;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fog.holograms.Holograms3Handler;
import com.moonsworth.lunar.client.inactive.Inactive3;
import com.moonsworth.lunar.client.inactive.mixin.holograms.EvaluatorImpl;

public class FishingHandler44 extends FishingHandler4 {
   @Annotation27("yaw")
   private Evaluatable field2;

   @Override
   public void method6(Inactive3 var1, Holograms3Handler var2) {
      EvaluatorImpl var3 = var1.method30().method9();
      var1.method6((float)this.field2.evaluate(var3));
   }

   @Override
   public String toString() {
      return "LookAtTask";
   }
}
