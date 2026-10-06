package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class LayeredAnimationController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "anims", required = true)
   public AnimationBuilder[] field3;

   public LayeredAnimationController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      for (int index3 = 0; index3 < this.field3.length; index3++) {
         int index4 = index3;
         animationdata2.addAnimationController(
            new AnimationControllerImpl(ianimatable1, "controller" + index3, this.OHICCHIROCCCIICHOHIROIRHIHOIHR, arg2x -> this.method2(this.field3[index4], arg2x))
         );
      }
   }

   private <T extends IAnimatable> PlayState method2(AnimationBuilder animationbuilder1, AnimationEventImpl animationeventimpl2) {
      this.method1(animationeventimpl2, animationbuilder1);
      return PlayState.CONTINUE;
   }
}
