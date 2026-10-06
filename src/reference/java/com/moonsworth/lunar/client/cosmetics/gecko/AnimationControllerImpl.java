package com.moonsworth.lunar.client.cosmetics.gecko;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class AnimationControllerImpl extends AnimationController<IAnimatable> {
   public AnimationControllerImpl(IAnimatable ianimatable1, String text, float value, final AnimationControllerImpl.AnimationTest extension4) {
      super(ianimatable1, text, value, new IAnimationPredicate<IAnimatable>() {
         public PlayState test(AnimationEvent animationevent1) {
            return extension4.test((AnimationEventImpl)animationevent1);
         }
      });
   }

   public interface AnimationTest {
      PlayState test(AnimationEventImpl animationeventimpl1);
   }
}
