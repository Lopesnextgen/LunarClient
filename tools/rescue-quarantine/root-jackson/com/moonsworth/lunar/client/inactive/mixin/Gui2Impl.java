package com.moonsworth.lunar.client.inactive.mixin;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.Fov10;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.inactive.Inactive;
import com.moonsworth.lunar.client.inactive.Inactive3;
import com.moonsworth.lunar.client.inactive.gui.Gui;
import com.moonsworth.lunar.client.inactive.gui.GuiHandler;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.Gui3;
import com.moonsworth.lunar.client.inactive.mixin.holograms.Holograms;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui2Impl extends Gui2Handler {
   @Annotation27(value = "attributes", required = true)
   private Inactive field20;
   @Annotation27("default_transition_ticks")
   private int field21 = 10;
   @Annotation27(value = "default_anim", required = true)
   private Gui field22;
   @Annotation27(value = "states", required = true)
   private Gui3[] field23;

   public void method1(IAnimatable var1, AnimationData var2) {
      AnimationControllerImpl var3 = new AnimationControllerImpl(var1, "companion", this.field21, this::method2);
      var2.addAnimationController(var3);
      var3.registerCustomInstructionListener(var0 -> var0.molang.evaluate(var0.getExecutionContext().getEvaluator()));
   }

   private PlayState method2(AnimationEventImpl var1) {
      Fov10 var2 = var1.method1();
      Evaluator var3 = var2.getEvaluator();
      var1.getController().easingType = EasingType.NONE;
      var1.getController().easingArgs = null;
      var1.getController().transitionLengthTicks = this.field21;
      Inactive3 var4 = var2.method14();
      if (var4 != null && var4.method45() != null && var4.method43() > 0) {
         Gui3 var5 = var4.method45();
         if (var5.method3() != null) {
            GuiHandler var6 = var5.method3().method1(var2);
            if (var6 != null) {
               this.method3(var1, var3, var6);
               return PlayState.CONTINUE;
            }
         }
      }

      GuiHandler var7 = this.field22.method1(var2);
      if (var7 != null) {
         this.method3(var1, var3, var7);
         return PlayState.CONTINUE;
      } else {
         return PlayState.STOP;
      }
   }

   private void method3(AnimationEventImpl var1, Evaluator var2, GuiHandler var3) {
      if (var1.getController().getAnimationState() == AnimationState.Transitioning) {
         var1.getController().easingType = var3.method5();
      }

      var1.getController().transitionLengthTicks = var3.method3() == null ? this.field21 : var3.method3().evaluate(var2);
      if (var3.method4() != null) {
         var1.getController().easingArgs = List.of(var3.method4());
      }

      this.method4(var1, var3.method2());
   }

   private void method4(AnimationEventImpl var1, AnimationBuilder var2) {
      Gui2Iterator var3 = (Gui2Iterator)var1.getAnimatable();
      if (!var3.method5().isEmpty()) {
         Holograms var4 = (Holograms)var3.method5().get();
         boolean var5 = var2.getRawAnimationList().stream().allMatch(var2x -> Objects.nonNull(var4.getAnimation(var2x.animationName, var3)));
         if (var5) {
            var1.getController().setAnimation(var2);
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
   public Gui method22() {
      return this.field22;
   }

   @Generated
   public Gui3[] method23() {
      return this.field23;
   }

   @Generated
   public void method9(Inactive var1) {
      this.field20 = var1;
   }

   @Generated
   public void method10(int var1) {
      this.field21 = var1;
   }

   @Generated
   public void method11(Gui var1) {
      this.field22 = var1;
   }

   @Generated
   public void method12(Gui3[] var1) {
      this.field23 = var1;
   }
}
