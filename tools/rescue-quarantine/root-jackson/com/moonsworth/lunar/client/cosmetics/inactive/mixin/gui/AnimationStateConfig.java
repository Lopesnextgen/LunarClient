package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing.InactiveTask;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class AnimationStateConfig {
   @Annotation27(value = "id", required = true)
   @NotNull
   private String id;
   @Annotation27("condition")
   private Evaluatable field1 = new ConstantEvaluatable(1.0);
   @Annotation27("tasks")
   private InactiveTask @Nullable [] field2;
   @Annotation27("animation")
   private @Nullable AnimationDefinition field3;
   @Annotation27("cooldown")
   private int field4 = 0;

   public AnimationStateConfig() {
   }

   @NotNull
   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public Evaluatable method1() {
      return this.field1;
   }

   @Generated
   public InactiveTask @Nullable [] method2() {
      return this.field2;
   }

   @Generated
   public @Nullable AnimationDefinition method3() {
      return this.field3;
   }

   @Generated
   public int method4() {
      return this.field4;
   }
}
