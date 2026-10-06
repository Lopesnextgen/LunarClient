package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ast.Evaluatable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;

public class GuiHandler implements AnimationSelector {
   @JsonProperty("anim")
   private AnimationBuilder field1;
   @JsonProperty("transition_ticks")
   private @Nullable Evaluatable field2 = null;
   @JsonProperty("transition_args")
   private Double @Nullable [] field3 = null;
   @JsonProperty("transition_easing")
   private EasingType field4 = EasingType.NONE;

   public GuiHandler(AnimationBuilder animationbuilder1) {
      this.field1 = animationbuilder1;
   }

   @Override
   public @Nullable GuiHandler method1(RenderContext fov101) {
      return this;
   }

   @Generated
   public AnimationBuilder method2() {
      return this.field1;
   }

   @Generated
   public @Nullable Evaluatable method3() {
      return this.field2;
   }

   @Generated
   public Double @Nullable [] method4() {
      return this.field3;
   }

   @Generated
   public EasingType method5() {
      return this.field4;
   }

   @Generated
   public GuiHandler() {
   }

   @Generated
   public GuiHandler(AnimationBuilder animationbuilder1, @Nullable Evaluatable evaluatable2, Double @Nullable [] items3, EasingType easingtype4) {
      this.field1 = animationbuilder1;
      this.field2 = evaluatable2;
      this.field3 = items3;
      this.field4 = easingtype4;
   }
}
