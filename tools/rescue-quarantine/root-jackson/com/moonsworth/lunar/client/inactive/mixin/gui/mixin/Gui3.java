package com.moonsworth.lunar.client.inactive.mixin.gui.mixin;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.GuiImpl;
import java.util.List;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui3 extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @Annotation27(value = "controllers", required = true)
   private com.moonsworth.lunar.client.inactive.mixin.gui.Gui[] field3;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      for (com.moonsworth.lunar.client.inactive.mixin.gui.Gui var6 : this.field3) {
         AnimationControllerImpl var7 = new AnimationControllerImpl(var1, var6.getName(), var6.method2(), var2x -> this.method2(var6, var2x));
         var2.addAnimationController(var7);
         var7.registerCustomInstructionListener(var0 -> var0.molang.evaluate(var0.getExecutionContext().getEvaluator()));
      }
   }

   private <T extends IAnimatable> PlayState method2(com.moonsworth.lunar.client.inactive.mixin.gui.Gui var1, AnimationEventImpl var2) {
      Evaluator var3 = var2.method1().getEvaluator();
      var2.getController().easingType = EasingType.NONE;
      var2.getController().easingArgs = null;
      var2.getController().transitionLengthTicks = var1.method2();

      for (GuiImpl var7 : var1.method1()) {
         if (var7.method1().evaluate(var3) == 1.0) {
            if (var2.getController().getAnimationState() == AnimationState.Transitioning) {
               var2.getController().easingType = var7.method5();
            }

            var2.getController().transitionLengthTicks = var7.IORHICCIHOOIHRROHOHHCRORHIHCOO() == null
               ? var1.method2()
               : var7.IORHICCIHOOIHRROHOHHCRORHIHCOO().evaluate(var3);
            if (var7.method4() != null) {
               var2.getController().easingArgs = List.of(var7.method4());
            }

            this.method4(var2, var7.method2());
            return PlayState.CONTINUE;
         }
      }

      return PlayState.STOP;
   }
}
