package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class IdleMoveController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "move_anim", required = true)
   public AnimationBuilder field4;

   public IdleMoveController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      animationdata2.addAnimationController(new AnimationControllerImpl(ianimatable1, "controller", this.OHICCHIROCCCIICHOHIROIRHIHOIHR, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl animationeventimpl1) {
      if (animationeventimpl1.method1().method1().map(arg0 -> arg0.RRIIIHHCORIRHOIIORRRRROCRROROR() > 0.01).orElse(false)) {
         this.method1(animationeventimpl1, this.field4);
      } else {
         this.method1(animationeventimpl1, this.field3);
      }

      return PlayState.CONTINUE;
   }
}
