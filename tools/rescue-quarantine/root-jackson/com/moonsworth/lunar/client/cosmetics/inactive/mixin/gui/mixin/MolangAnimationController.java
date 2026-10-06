package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.GuiImpl;
import java.util.List;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.manager.AnimationData;

public class MolangAnimationController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "controllers", required = true)
   private com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationDefinition[] field3;

   public MolangAnimationController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      for (com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationDefinition gui6 : this.field3) {
         AnimationControllerImpl animationcontrollerimpl7 = new AnimationControllerImpl(ianimatable1, gui6.getName(), gui6.method2(), arg2x -> this.method2(gui6, arg2x));
         animationdata2.addAnimationController(animationcontrollerimpl7);
         animationcontrollerimpl7.registerCustomInstructionListener(arg0 -> arg0.molang.evaluate(arg0.getExecutionContext().getEvaluator()));
      }
   }

   private <T extends IAnimatable> PlayState method2(com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationDefinition gui1, AnimationEventImpl animationeventimpl2) {
      Evaluator evaluator3 = animationeventimpl2.method1().getEvaluator();
      animationeventimpl2.getController().easingType = EasingType.NONE;
      animationeventimpl2.getController().easingArgs = null;
      animationeventimpl2.getController().transitionLengthTicks = gui1.method2();

      for (GuiImpl guiimpl7 : gui1.method1()) {
         if (guiimpl7.method1().evaluate(evaluator3) == 1.0) {
            if (animationeventimpl2.getController().getAnimationState() == AnimationState.Transitioning) {
               animationeventimpl2.getController().easingType = guiimpl7.HHRRHOHRRIORRCRRRIHHHIIIOHCIRH();
            }

            animationeventimpl2.getController().transitionLengthTicks = guiimpl7.IORHICCIHOOIHRROHOHHCRORHIHCOO() == null
               ? gui1.method2()
               : guiimpl7.IORHICCIHOOIHRROHOHHCRORHIHCOO().evaluate(evaluator3);
            if (guiimpl7.RICHOCROHCIHIIRCIRIRCHOOOHOIRI() != null) {
               animationeventimpl2.getController().easingArgs = List.of(guiimpl7.RICHOCROHCIHIIRCIRIRCHOOOHOIRI());
            }

            this.method4(animationeventimpl2, guiimpl7.IIIRRCHCCICCHICIHHHIIRROCICIHO());
            return PlayState.CONTINUE;
         }
      }

      return PlayState.STOP;
   }
}
