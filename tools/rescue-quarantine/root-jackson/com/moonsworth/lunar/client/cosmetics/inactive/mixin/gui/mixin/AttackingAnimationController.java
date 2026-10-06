package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.framework.combat.PlayerCombatState;
import com.moonsworth.lunar.client.framework.combat.PlayerCombatState.Data;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.util.Ref;
import java.util.Optional;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class AttackingAnimationController extends com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "entering_attack_anim", required = true)
   public AnimationBuilder field4;
   @Annotation27(value = "leaving_attack_anim", required = true)
   public AnimationBuilder field5;
   @Annotation27(value = "attack_anim_length", required = true)
   public Double field6;

   public AttackingAnimationController() {
   }

   @Override
   public void method1(IAnimatable ianimatable1, AnimationData animationdata2) {
      animationdata2.addAnimationController(new AnimationControllerImpl(ianimatable1, "controller", this.OHICCHIROCCCIICHOHIROIRHIHOIHR, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl animationeventimpl1) {
      Optional optional2 = animationeventimpl1.method1().method1();
      if (optional2.isEmpty()) {
         this.method1(animationeventimpl1, this.field3);
         return PlayState.CONTINUE;
      }

      Bridge5_11 bridge5_113 = (Bridge5_11)optional2.get();
      Data data4 = PlayerCombatState.method1(bridge5_113);
      long number5 = Math.min(Ref.method14() - data4.method7(), Ref.method14() - bridge5_113.bridge$getLastAttackedMillis());
      if (number5 > this.field6 * 1000.0) {
         this.method1(animationeventimpl1, this.field5);
      } else {
         this.method1(animationeventimpl1, this.field4);
      }

      return PlayState.CONTINUE;
   }
}
