package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.IdleMoveController;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.SingleAnimationController;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.OccasionalAnimationController;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms.MolangResourceModel;
import java.util.Map;
import java.util.Objects;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public abstract class AnimationControllerDefinition {
   public static final Map<String, Class<? extends AnimationControllerDefinition>> field1 = Map.of(
      "simple_movement",
      IdleMoveController.class,
      "single",
      SingleAnimationController.class,
      "attacking",
      com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.AttackingAnimationController.class,
      "occasional_blended",
      OccasionalAnimationController.class,
      "layered",
      com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.LayeredAnimationController.class,
      "molang",
      com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.MolangAnimationController.class
   );
   @Annotation27("transition_tick_length")
   public float field2 = 10.0F;

   public AnimationControllerDefinition() {
   }

   public abstract void method1(IAnimatable ianimatable1, AnimationData animationdata2);

   protected final void method2(AnimationEventImpl animationeventimpl1, AnimationBuilder animationbuilder2) {
      Gui2Iterator gui2iterator3 = (Gui2Iterator)animationeventimpl1.getAnimatable();
      if (!gui2iterator3.method5().isEmpty()) {
         MolangResourceModel holograms4 = (MolangResourceModel)gui2iterator3.method5().get();
         boolean flag5 = animationbuilder2.getRawAnimationList().stream().allMatch(arg2x -> Objects.nonNull(holograms4.getAnimation(arg2x.animationName, gui2iterator3)));
         if (flag5) {
            animationeventimpl1.getController().setAnimation(animationbuilder2);
         }
      }
   }
}
