package com.moonsworth.lunar.client.cosmetics.inactive;

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
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.colorsaturation.mixin.AnimationKeyframeParser;

public class MolangDeserializer extends MixinHelper102_4<Evaluatable> {
   public MolangDeserializer() {
   }

   public Evaluatable method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
      MixinHelper9_9 mixinhelper9_93 = mixinhelper531.method98();
      if (mixinhelper9_93 instanceof MixinHelper3234 mixinhelper32344) {
         return AnimationKeyframeParser.method10(mixinhelper32344.method39());
      } else if (mixinhelper9_93 instanceof MixinHelper32366 mixinhelper323665) {
         return new ConstantEvaluatable(mixinhelper323665.intValue());
      } else if (mixinhelper9_93 instanceof MixinHelper32368 mixinhelper323686) {
         return new ConstantEvaluatable(mixinhelper323686.floatValue());
      } else if (mixinhelper9_93 instanceof MixinHelper32365 mixinhelper323657) {
         return new ConstantEvaluatable(mixinhelper323657.doubleValue());
      } else {
         return mixinhelper9_93 instanceof MixinHelper3232 mixinhelper32328 ? new ConstantEvaluatable(MolangUtils.booleanToFloat(mixinhelper32328.method35())) : null;
      }
   }
}
