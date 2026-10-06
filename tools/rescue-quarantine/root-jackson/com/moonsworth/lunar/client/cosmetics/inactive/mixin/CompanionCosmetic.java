package com.moonsworth.lunar.client.cosmetics.inactive.mixin;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.cosmetics.inactive.Inactive;
import com.moonsworth.lunar.client.inactive.Inactive3;
import com.moonsworth.lunar.client.cosmetics.inactive.gui.AnimationSelector;
import com.moonsworth.lunar.client.cosmetics.inactive.gui.GuiHandler;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationStateConfig;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms.MolangResourceModel;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.manager.AnimationData;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;

public class CompanionCosmetic extends Gui2Handler {
   @Annotation27(value = "attributes", required = true)
   private Inactive field20;
   @Annotation27("default_transition_ticks")
   private int field21 = 10;
   @Annotation27(value = "default_anim", required = true)
   private AnimationSelector field22;
   @Annotation27(value = "states", required = true)
   private AnimationStateConfig[] field23;

   public CompanionCosmetic() {
   }

   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      AnimationControllerImpl animationcontrollerimpl3 = new AnimationControllerImpl(ianimatable1, "companion", this.field21, this::method2);
      animationdata2.addAnimationController(animationcontrollerimpl3);
      animationcontrollerimpl3.registerCustomInstructionListener(arg0 -> arg0.molang.evaluate(arg0.getExecutionContext().getEvaluator()));
   }

   private PlayState method2(AnimationEventImpl animationeventimpl1) {
      RenderContext fov102 = animationeventimpl1.method1();
      Evaluator evaluator3 = fov102.getEvaluator();
      animationeventimpl1.getController().easingType = EasingType.NONE;
      animationeventimpl1.getController().easingArgs = null;
      animationeventimpl1.getController().transitionLengthTicks = this.field21;
      Inactive3 inactive34 = fov102.method14();
      if (inactive34 != null && inactive34.method45() != null && inactive34.method43() > 0) {
         AnimationStateConfig gui35 = inactive34.method45();
         if (gui35.method3() != null) {
            GuiHandler guihandler6 = gui35.method3().method1(fov102);
            if (guihandler6 != null) {
               this.method3(animationeventimpl1, evaluator3, guihandler6);
               return PlayState.CONTINUE;
            }
         }
      }

      GuiHandler guihandler7 = this.field22.method1(fov102);
      if (guihandler7 != null) {
         this.method3(animationeventimpl1, evaluator3, guihandler7);
         return PlayState.CONTINUE;
      } else {
         return PlayState.STOP;
      }
   }

   private void method3(AnimationEventImpl animationeventimpl1, Evaluator evaluator2, GuiHandler guihandler3) {
      if (animationeventimpl1.getController().getAnimationState() == AnimationState.Transitioning) {
         animationeventimpl1.getController().easingType = guihandler3.method5();
      }

      animationeventimpl1.getController().transitionLengthTicks = guihandler3.method3() == null ? this.field21 : guihandler3.method3().evaluate(evaluator2);
      if (guihandler3.method4() != null) {
         animationeventimpl1.getController().easingArgs = List.of(guihandler3.method4());
      }

      this.method4(animationeventimpl1, guihandler3.method2());
   }

   private void method4(AnimationEventImpl animationeventimpl1, AnimationBuilder animationbuilder2) {
      Gui2Iterator gui2iterator3 = (Gui2Iterator)animationeventimpl1.getAnimatable();
      if (!gui2iterator3.method5().isEmpty()) {
         MolangResourceModel holograms4 = (MolangResourceModel)gui2iterator3.method5().get();
         boolean flag5 = animationbuilder2.getRawAnimationList().stream().allMatch(arg2x -> Objects.nonNull(holograms4.getAnimation(arg2x.animationName, gui2iterator3)));
         if (flag5) {
            animationeventimpl1.getController().setAnimation(animationbuilder2);
         }
      }
   }

   @Generated
   public Inactive method20() {
      return this.field20;
   }

   @Generated
   public int method21() {
      return this.field21;
   }

   @Generated
   public AnimationSelector method22() {
      return this.field22;
   }

   @Generated
   public AnimationStateConfig[] method23() {
      return this.field23;
   }

   @Generated
   public void method9(Inactive inactive1) {
      this.field20 = inactive1;
   }

   @Generated
   public void method10(int number1) {
      this.field21 = number1;
   }

   @Generated
   public void method11(AnimationSelector gui1) {
      this.field22 = gui1;
   }

   @Generated
   public void method12(AnimationStateConfig[] items1) {
      this.field23 = items1;
   }
}
