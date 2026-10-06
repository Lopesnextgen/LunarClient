package com.moonsworth.lunar.client.cosmetics.inactive.gui;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;

public class ConditionalAnimationSelector implements AnimationSelector {
   private ConditionalAnimationSelector.ConditionalAnimationState[] field1;

   @Override
   public @Nullable GuiHandler method1(RenderContext fov101) {
      Evaluator evaluator2 = fov101.getEvaluator();

      for (ConditionalAnimationSelector.ConditionalAnimationState data26 : this.field1) {
         data26.field5.evaluate(evaluator2);
         if (data26.field5.evaluate(evaluator2) == 1.0) {
            return data26;
         }
      }

      return null;
   }

   @Generated
   public ConditionalAnimationSelector(ConditionalAnimationSelector.ConditionalAnimationState[] items1) {
      this.field1 = items1;
   }

   public static class ConditionalAnimationState extends GuiHandler {
      @Annotation27(value = "condition", required = true)
      private Evaluatable field5;

      public ConditionalAnimationState(AnimationBuilder animationbuilder1, @Nullable Evaluatable evaluatable2, Double @Nullable [] items3, EasingType easingtype4, Evaluatable evaluatable5) {
         super(animationbuilder1, evaluatable2, items3, easingtype4);
         this.field5 = evaluatable5;
      }

      @Generated
      public ConditionalAnimationState() {
      }
   }
}
