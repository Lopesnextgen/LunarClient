package com.moonsworth.lunar.client.mod.render.motionblur;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8_7;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.client.render.shader.LunarPostEffect;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.motionblur.MotionBlurShader;
import com.moonsworth.lunar.client.event.mixin.highlight.EventPostProcess;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class MotionBlur extends AbstractFeature {
   private final IntegerOption blurAmount = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("value")
            .method4(5))
         .method7(1, 10))
      .method31();
   private final EnumOption<MotionBlur.Type> blurType = (EnumOption<MotionBlur.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "type", MotionBlur.Type.V3
      )
      .method31();
   private Bridge3_24 previousFrameBuffer = null;
   private LunarPostEffect motionBlurShader = null;

   public MotionBlur() {
      super(false);
      this.handle(EventPostProcess.class, arg1 -> {
         if (this.motionBlurShader == null) {
            this.motionBlurShader = Ref.method4().method99().method2("motion_blur", new MotionBlurShader());
         }

         arg1.method1();
         Bridge3_24 bridge3_242 = arg1.method3();
         Bridge3_24 bridge3_243 = this.previousFrameBuffer;
         if (this.previousFrameBuffer == null) {
            this.previousFrameBuffer = Bridge_52.method2().method1(bridge3_242.bridge$framebufferWidth(), bridge3_242.bridge$framebufferHeight()).method9(true).method3();
            bridge3_243 = bridge3_242;
         } else if (!this.previousFrameBuffer.method6(bridge3_242)) {
            this.previousFrameBuffer.bridge$createBindFramebuffer(bridge3_242.bridge$framebufferWidth(), bridge3_242.bridge$framebufferHeight());
         }

         Bridge8_7 bridge8_74 = bridge3_243.bridge$getColorTexture(true);
         this.motionBlurShader.method2(arg1.method2(), bridge3_242, arg2x -> {
            arg2x.bridge$getShaderUniform("Phosphor").bridge$set(this.getAccumulationValue(), ((MotionBlur.Type)this.blurType.get()).id, 0.0F);
            if (bridge8_74 instanceof Bridge8Extension bridge8extension3x) {
               arg2x.bridge$bindSampler("PrevSampler", bridge8extension3x);
            }
         });
         Ref.method4().method99().method5(arg1.method2(), bridge3_242, this.previousFrameBuffer);
      });
   }

   private float getAccumulationValue() {
      float value1 = ((Integer)this.blurAmount.get()).intValue() / 10.0F;
      if (this.blurType.get() == MotionBlur.Type.V1) {
         value1 = 0.7F + ((Integer)this.getBlurAmount().get()).intValue() / 100.0F * 3.0F - 0.01F;
      } else if (this.blurType.get() == MotionBlur.Type.V2) {
         if (value1 >= 1.0F) {
            value1 = 0.99F;
         }

         value1 = 1.0F - value1;
      }

      return value1;
   }

   public String getId() {
      return "MOTION_BLUR";
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         if (this.motionBlurShader != null) {
            this.motionBlurShader.delete();
            this.motionBlurShader = null;
         }

         if (this.previousFrameBuffer != null) {
            this.previousFrameBuffer.bridge$delete();
            this.previousFrameBuffer = null;
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.blurType, this.blurAmount});
   }

   @Generated
   public IntegerOption getBlurAmount() {
      return this.blurAmount;
   }

   @Generated
   public EnumOption<MotionBlur.Type> getBlurTypeOption() {
      return this.blurType;
   }

   public enum Type implements OptionEnumValue {
      V1("v1", 0),
      V2("v2", 1),
      V3("v3", 2);

      private final String name;
      private final int id;

      public String id() {
         return this.name;
      }

      @Generated
      Type(String text3, int number4) {
         this.name = text3;
         this.id = number4;
      }
   }
}
