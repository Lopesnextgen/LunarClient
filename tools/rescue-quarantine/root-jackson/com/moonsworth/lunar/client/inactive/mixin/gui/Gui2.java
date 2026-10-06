package com.moonsworth.lunar.client.inactive.mixin.gui;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui2Impl;
import com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui2Impl2;
import com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui2Impl3;
import com.moonsworth.lunar.client.inactive.mixin.holograms.Holograms;
import java.util.Map;
import java.util.Objects;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

public abstract class Gui2 {
   public static final Map<String, Class<? extends Gui2>> field1 = Map.of(
      "simple_movement",
      Gui2Impl.class,
      "single",
      Gui2Impl2.class,
      "attacking",
      com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui2.class,
      "occasional_blended",
      Gui2Impl3.class,
      "layered",
      com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui.class,
      "molang",
      com.moonsworth.lunar.client.inactive.mixin.gui.mixin.Gui3.class
   );
   @Annotation27("transition_tick_length")
   public float field2 = 10.0F;

   public abstract void method1(IAnimatable var1, AnimationData var2);

   protected final void method2(AnimationEventImpl var1, AnimationBuilder var2) {
      Gui2Iterator var3 = (Gui2Iterator)var1.getAnimatable();
      if (!var3.method5().isEmpty()) {
         Holograms var4 = (Holograms)var3.method5().get();
         boolean var5 = var2.getRawAnimationList().stream().allMatch(var2x -> Objects.nonNull(var4.getAnimation(var2x.animationName, var3)));
         if (var5) {
            var1.getController().setAnimation(var2);
         }
      }
   }
}
