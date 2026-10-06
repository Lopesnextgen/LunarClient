package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class SingleAnimationController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "anim", required = true)
   public AnimationBuilder field3;

   public SingleAnimationController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      animationdata2.addAnimationController(new AnimationControllerImpl(ianimatable1, "controller", this.OHICCHIROCCCIICHOHIROIRHIHOIHR, this::method2));
   }

   private PlayState method2(AnimationEventImpl animationeventimpl1) {
      this.method1(animationeventimpl1, this.field3);
      return PlayState.CONTINUE;
   }
}
