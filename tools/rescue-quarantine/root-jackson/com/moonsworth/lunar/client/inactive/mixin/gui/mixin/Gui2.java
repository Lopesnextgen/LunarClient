package com.moonsworth.lunar.client.inactive.mixin.gui.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.hitcolor.FogHandler;
import com.moonsworth.lunar.client.hitcolor.FogHandler.Data;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationControllerImpl;
import com.moonsworth.lunar.client.inactive.mixin.gui.AnimationEventImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Gui2 extends com.moonsworth.lunar.client.inactive.mixin.gui.Gui2 {
   @Annotation27(value = "idle_anim", required = true)
   public AnimationBuilder field3;
   @Annotation27(value = "entering_attack_anim", required = true)
   public AnimationBuilder field4;
   @Annotation27(value = "leaving_attack_anim", required = true)
   public AnimationBuilder field5;
   @Annotation27(value = "attack_anim_length", required = true)
   public Double field6;

   @Override
   public void method1(IAnimatable var1, AnimationData var2) {
      var2.addAnimationController(new AnimationControllerImpl(var1, "controller", this.field2, this::method2));
   }

   private <T extends IAnimatable> PlayState method2(AnimationEventImpl var1) {
      Optional var2 = var1.method1().method1();
      if (var2.isEmpty()) {
         this.method1(var1, this.field3);
         return PlayState.CONTINUE;
      }

      Bridge5_11 var3 = (Bridge5_11)var2.get();
      Data var4 = FogHandler.method1(var3);
      long var5 = Math.min(ThreadModuleDump63.method14() - var4.method7(), ThreadModuleDump63.method14() - var3.bridge$getLastAttackedMillis());
      if (var5 > this.field6 * 1000.0) {
         this.method1(var1, this.field5);
      } else {
         this.method1(var1, this.field4);
      }

      return PlayState.CONTINUE;
   }
}
