package com.moonsworth.lunar.client.cosmetics.inactive;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper32;
import com.moonsworth.lunar.MixinHelper3222;
import com.moonsworth.lunar.MixinHelper3223;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import software.bernie.geckolib3.core.builder.AnimationBuilder;

class AnimationBuilderDeserializer extends MixinHelper102_4<AnimationBuilder> {
   private AnimationBuilderDeserializer() {
   }

   public AnimationBuilder method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
      MixinHelper9_9 mixinhelper9_93 = mixinhelper531.method98();
      if (mixinhelper9_93 instanceof MixinHelper3234 mixinhelper323410) {
         return new AnimationBuilder().addAnimation(mixinhelper323410.method39(), true);
      } else {
         AnimationBuilder animationbuilder4 = new AnimationBuilder();
         if (mixinhelper9_93 instanceof MixinHelper3223) {
            for (MixinHelper32 mixinhelper328 : (MixinHelper3223)mixinhelper9_93) {
               if (mixinhelper328 instanceof MixinHelper3222 mixinhelper32229) {
                  this.method2(animationbuilder4, mixinhelper32229);
               }
            }
         } else if (mixinhelper9_93 instanceof MixinHelper3222 mixinhelper32226) {
            this.method2(animationbuilder4, mixinhelper32226);
         }

         return animationbuilder4;
      }
   }

   private void method2(AnimationBuilder animationbuilder1, MixinHelper3222 mixinhelper32222) {
      if (mixinhelper32222.has("name") && mixinhelper32222.method8("name").method27()) {
         String text3 = mixinhelper32222.method8("name").method34();
         Boolean flag4 = null;
         if (mixinhelper32222.has("loop") && mixinhelper32222.method8("loop").method28()) {
            flag4 = mixinhelper32222.method8("loop").method35();
         }

         animationbuilder1.addAnimation(text3, flag4);
      }
   }
}
