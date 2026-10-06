package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.util.Ref;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class OccasionalAnimationController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "occasional_anim", required = true)
   public AnimationBuilder field4;
   @Annotation27(value = "anim_delay", required = true)
   public Double field5;
   @Annotation27(value = "occasional_anim_length", required = true)
   public Double field6;

   public OccasionalAnimationController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      animationdata2.addAnimationController(new AnimationControllerImpl(ianimatable1, "occasional_controller", this.OHICCHIROCCCIICHOHIROIRHIHOIHR, this::method3));
      animationdata2.addAnimationController(new AnimationControllerImpl(ianimatable1, "controller", this.OHICCHIROCCCIICHOHIROIRHIHOIHR, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl animationeventimpl1) {
      animationeventimpl1.getController().setAnimation(this.field3);
      return PlayState.CONTINUE;
   }

   private <T extends IAnimatable> PlayState method3(AnimationEventImpl animationeventimpl1) {
      double value2 = Ref.method14() % ((this.field6 + this.field5) * 1000.0);
      this.method2(animationeventimpl1, this.field4);
      return value2 > this.field5 * 1000.0 ? PlayState.CONTINUE : PlayState.CONTINUE;
   }
}
