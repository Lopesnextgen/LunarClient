package com.moonsworth.lunar.client.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui2Impl extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "move_anim", required = true)
   public AnimationBuilder field4;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      var2.addAnimationController(new AnimationControllerImpl(var1, "controller", this.field2, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl var1) {
      if (var1.method1().method1().map(var0 -> var0.RRIIIHHCORIRHOIIORRRRROCRROROR() > 0.01).orElse(false)) {
         this.method1(var1, this.field4);
      } else {
         this.method1(var1, this.field3);
      }

      return PlayState.CONTINUE;
   }
}
