package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class AnimatedModelController extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @JsonProperty(value = "anims", required = true)
   public AnimationBuilder[] field3;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      for (int var3 = 0; var3 < this.field3.length; var3++) {
         int var4 = var3;
         var2.addAnimationController(
            new AnimationControllerImpl(var1, "controller" + var3, this.field2, var2x -> this.method2(this.field3[var4], var2x))
         );
      }
   }

   private <T extends IAnimatable> PlayState method2(AnimationBuilder var1, AnimationEventImpl var2) {
      this.method1(var2, var1);
      return PlayState.CONTINUE;
   }
}
