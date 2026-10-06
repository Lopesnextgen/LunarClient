package com.moonsworth.lunar.client.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui2Impl2 extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @Annotation27(value = "anim", required = true)
   public AnimationBuilder field3;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      var2.addAnimationController(new AnimationControllerImpl(var1, "controller", this.field2, this::method2));
   }

   private PlayState method2(AnimationEventImpl var1) {
      this.method1(var1, this.field3);
      return PlayState.CONTINUE;
   }
}
