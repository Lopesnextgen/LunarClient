package com.moonsworth.lunar.client.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper3232;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper32365;
import com.moonsworth.lunar.MixinHelper32366;
import com.moonsworth.lunar.MixinHelper32368;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import com.moonsworth.lunar.client.fps.mixin.EvaluatableImpl;
import com.moonsworth.lunar.client.inactive.mixin.colorsaturation.mixin.Colorsaturation;

public class MixinHelper1022 extends MixinHelper102_4<Evaluatable> {
   public Evaluatable method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      MixinHelper9_9 var3 = var1.method98();
      if (var3 instanceof MixinHelper3234 var4) {
         return Colorsaturation.method10(var4.method39());
      } else if (var3 instanceof MixinHelper32366 var5) {
         return new EvaluatableImpl(var5.intValue());
      } else if (var3 instanceof MixinHelper32368 var6) {
         return new EvaluatableImpl(var6.floatValue());
      } else if (var3 instanceof MixinHelper32365 var7) {
         return new EvaluatableImpl(var7.doubleValue());
      } else {
         return var3 instanceof MixinHelper3232 var8 ? new EvaluatableImpl(MolangUtils.booleanToFloat(var8.method35())) : null;
      }
   }
}
