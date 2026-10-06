package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometry;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.Color;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.processor.AnimationProcessor;

public class ModelRenderConfig {
   private AbstractRenderContext field1;
   private AnimationProcessor<?> field2;
   private ResourceLocationBridge texture;
   private BedrockGeometry field3;
   private boolean field4;
   private boolean field5;
   @NotNull
   private RenderTypeLookup renderType;
   @NotNull
   private RenderPass field6;
   @NotNull
   private Color color;
   @Nullable
   private Integer field7;
   private int field8;

   @VersionGate(min = 6)
   public int method1() {
      if (this.field6 == RenderPass.EMISSIVE) {
         return Bridge.method8().method92();
      } else {
         return this.field7 != null ? this.field7 : this.method7().method30().method46().orElse(Bridge.method8().method92());
      }
   }

   @Generated
   private static RenderTypeLookup method2() {
      return LunarRenderTypes.field47;
   }

   @Generated
   private static RenderPass method3() {
      return RenderPass.NORMAL;
   }

   @Generated
   private static Color $default$color() {
      return Color.WHITE;
   }

   @Generated
   private static Integer method4() {
      return null;
   }

   @Generated
   private static int method5() {
      return 0;
   }

   @Generated
   public static ModelRenderConfig.Builder method6() {
      return new ModelRenderConfig.Builder();
   }

   @Generated
   public AbstractRenderContext method7() {
      return this.field1;
   }

   @Generated
   public AnimationProcessor<?> method8() {
      return this.field2;
   }

   @Generated
   public ResourceLocationBridge getTexture() {
      return this.texture;
   }

   @Generated
   public BedrockGeometry method10() {
      return this.field3;
   }

   @Generated
   public boolean method11() {
      return this.field4;
   }

   @Generated
   public boolean method12() {
      return this.field5;
   }

   @NotNull
   @Generated
   public RenderTypeLookup getRenderType() {
      return this.renderType;
   }

   @NotNull
   @Generated
   public RenderPass method14() {
      return this.field6;
   }

   @NotNull
   @Generated
   public Color getColor() {
      return this.color;
   }

   @Nullable
   @Generated
   public Integer method16() {
      return this.field7;
   }

   @Generated
   public int method17() {
      return this.field8;
   }

   @Generated
   public ModelRenderConfig method18(AbstractRenderContext bridgeextension_91) {
      this.field1 = bridgeextension_91;
      return this;
   }

   @Generated
   public ModelRenderConfig method19(AnimationProcessor<?> animationprocessor1) {
      this.field2 = animationprocessor1;
      return this;
   }

   @Generated
   public ModelRenderConfig method20(ResourceLocationBridge horsestats141) {
      this.texture = horsestats141;
      return this;
   }

   @Generated
   public ModelRenderConfig method21(BedrockGeometry rewindhandlers2_21) {
      this.field3 = rewindhandlers2_21;
      return this;
   }

   @Generated
   public ModelRenderConfig method22(boolean flag1) {
      this.field4 = flag1;
      return this;
   }

   @Generated
   public ModelRenderConfig method23(boolean flag1) {
      this.field5 = flag1;
      return this;
   }

   @Generated
   public ModelRenderConfig method24(@NotNull RenderTypeLookup mixinhelper6_31) {
      if (mixinhelper6_31 == null) {
         throw new NullPointerException("renderType is marked non-null but is null");
      }

      this.renderType = mixinhelper6_31;
      return this;
   }

   @Generated
   public ModelRenderConfig method25(@NotNull RenderPass fovtype31) {
      if (fovtype31 == null) {
         throw new NullPointerException("pass is marked non-null but is null");
      }

      this.field6 = fovtype31;
      return this;
   }

   @Generated
   public ModelRenderConfig method26(@NotNull Color color1) {
      if (color1 == null) {
         throw new NullPointerException("color is marked non-null but is null");
      }

      this.color = color1;
      return this;
   }

   @Generated
   public ModelRenderConfig method27(@Nullable Integer number1) {
      this.field7 = number1;
      return this;
   }

   @Generated
   public ModelRenderConfig method28(int number1) {
      this.field8 = number1;
      return this;
   }

   @Generated
   public ModelRenderConfig(
      AbstractRenderContext bridgeextension_91,
      AnimationProcessor<?> animationprocessor2,
      ResourceLocationBridge horsestats143,
      BedrockGeometry rewindhandlers2_24,
      boolean flag5,
      boolean flag6,
      @NotNull RenderTypeLookup mixinhelper6_37,
      @NotNull RenderPass fovtype38,
      @NotNull Color color9,
      @Nullable Integer number10,
      int number11
   ) {
      if (mixinhelper6_37 == null) {
         throw new NullPointerException("renderType is marked non-null but is null");
      }

      if (fovtype38 == null) {
         throw new NullPointerException("pass is marked non-null but is null");
      }

      if (color9 == null) {
         throw new NullPointerException("color is marked non-null but is null");
      }

      this.field1 = bridgeextension_91;
      this.field2 = animationprocessor2;
      this.texture = horsestats143;
      this.field3 = rewindhandlers2_24;
      this.field4 = flag5;
      this.field5 = flag6;
      this.renderType = mixinhelper6_37;
      this.field6 = fovtype38;
      this.color = color9;
      this.field7 = number10;
      this.field8 = number11;
   }

   @Generated
   public static class Builder {
      @Generated
      private AbstractRenderContext field1;
      @Generated
      private AnimationProcessor<?> field2;
      @Generated
      private ResourceLocationBridge texture;
      @Generated
      private BedrockGeometry field3;
      @Generated
      private boolean field4;
      @Generated
      private boolean field5;
      @Generated
      private boolean field6;
      @Generated
      private RenderTypeLookup field7;
      @Generated
      private boolean field8;
      @Generated
      private RenderPass field9;
      @Generated
      private boolean color$set;
      @Generated
      private Color color$value;
      @Generated
      private boolean field10;
      @Generated
      private Integer field11;
      @Generated
      private boolean field12;
      @Generated
      private int field13;

      @Generated
      Builder() {
      }

      @Generated
      public ModelRenderConfig.Builder method1(AbstractRenderContext bridgeextension_91) {
         this.field1 = bridgeextension_91;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method2(AnimationProcessor<?> animationprocessor1) {
         this.field2 = animationprocessor1;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method3(ResourceLocationBridge horsestats141) {
         this.texture = horsestats141;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method4(BedrockGeometry rewindhandlers2_21) {
         this.field3 = rewindhandlers2_21;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method5(boolean flag1) {
         this.field4 = flag1;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method6(boolean flag1) {
         this.field5 = flag1;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method7(@NotNull RenderTypeLookup mixinhelper6_31) {
         if (mixinhelper6_31 == null) {
            throw new NullPointerException("renderType is marked non-null but is null");
         }

         this.field7 = mixinhelper6_31;
         this.field6 = true;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method8(@NotNull RenderPass fovtype31) {
         if (fovtype31 == null) {
            throw new NullPointerException("pass is marked non-null but is null");
         }

         this.field9 = fovtype31;
         this.field8 = true;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method9(@NotNull Color color1) {
         if (color1 == null) {
            throw new NullPointerException("color is marked non-null but is null");
         }

         this.color$value = color1;
         this.color$set = true;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method10(@Nullable Integer number1) {
         this.field11 = number1;
         this.field10 = true;
         return this;
      }

      @Generated
      public ModelRenderConfig.Builder method11(int number1) {
         this.field13 = number1;
         this.field12 = true;
         return this;
      }

      @Generated
      public ModelRenderConfig method12() {
         RenderTypeLookup mixinhelper6_31 = this.field7;
         if (!this.field6) {
            mixinhelper6_31 = ModelRenderConfig.method2();
         }

         RenderPass fovtype32 = this.field9;
         if (!this.field8) {
            fovtype32 = ModelRenderConfig.method3();
         }

         Color color3 = this.color$value;
         if (!this.color$set) {
            color3 = ModelRenderConfig.$default$color();
         }

         Integer number4 = this.field11;
         if (!this.field10) {
            number4 = ModelRenderConfig.method4();
         }

         int number5 = this.field13;
         if (!this.field12) {
            number5 = ModelRenderConfig.method5();
         }

         return new ModelRenderConfig(this.field1, this.field2, this.texture, this.field3, this.field4, this.field5, mixinhelper6_31, fovtype32, color3, number4, number5);
      }

      @Generated
      @Override
      public String toString() {
         return "GeckolibCosmeticRenderState.GeckolibCosmeticRenderStateBuilder(renderContext="
            + this.field1
            + ", animationProcessor="
            + this.field2
            + ", texture="
            + this.texture
            + ", geoModel="
            + this.field3
            + ", translucent="
            + this.field4
            + ", flippedWinding="
            + this.field5
            + ", renderType$value="
            + this.field7
            + ", pass$value="
            + this.field9
            + ", color$value="
            + this.color$value
            + ", light$value="
            + this.field11
            + ", instance$value="
            + this.field13
            + ")";
      }
   }
}
