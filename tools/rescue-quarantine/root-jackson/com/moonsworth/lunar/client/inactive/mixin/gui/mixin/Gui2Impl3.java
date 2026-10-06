package com.moonsworth.lunar.client.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui2Impl3 extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "occasional_anim", required = true)
   public AnimationBuilder field4;
   @Annotation27(value = "anim_delay", required = true)
   public Double field5;
   @Annotation27(value = "occasional_anim_length", required = true)
   public Double field6;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      var2.addAnimationController(new AnimationControllerImpl(var1, "occasional_controller", this.field2, this::method3));
      var2.addAnimationController(new AnimationControllerImpl(var1, "controller", this.field2, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl var1) {
      var1.getController().setAnimation(this.field3);
      return PlayState.CONTINUE;
   }

   private <T extends IAnimatable> PlayState method3(AnimationEventImpl var1) {
      double var2 = ThreadModuleDump63.method14() % ((this.field6 + this.field5) * 1000.0);
      this.method2(var1, this.field4);
      return var2 > this.field5 * 1000.0 ? PlayState.CONTINUE : PlayState.CONTINUE;
   }
}
