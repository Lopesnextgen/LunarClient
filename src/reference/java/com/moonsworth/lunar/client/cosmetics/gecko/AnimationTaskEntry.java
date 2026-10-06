package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.cosmetics.molang.ast.ConstantEvaluatable;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveTask;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class AnimationTaskEntry {
   @JsonProperty(value = "id", required = true)
   @NotNull
   private String id;
   @JsonProperty("condition")
   private Evaluatable field1 = new ConstantEvaluatable(1.0);
   @JsonProperty("tasks")
   private InactiveTask @Nullable [] field2;
   @JsonProperty("animation")
   private @Nullable AnimationStateMachine field3;
   @JsonProperty("cooldown")
   private int field4 = 0;

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
   public @Nullable AnimationStateMachine method3() {
      return this.field3;
   }

   @Generated
   public int method4() {
      return this.field4;
   }
}
