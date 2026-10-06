package com.moonsworth.lunar.client.mod.render.colorsaturation;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.client.render.shader.LunarPostEffect;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.colorsaturation.ColorSaturationShader;
import com.moonsworth.lunar.client.event.mixin.highlight.EventPostProcess;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.render.saturation.Saturation;

public class ColorSaturation extends AbstractFeature {
   private final FloatOption field8 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "colorSaturationSaturation"
            )
            .method4(5.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final FloatOption field9 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("colorSaturationHue")
            .method4(0.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final FloatOption field10 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "colorSaturationContrast"
            )
            .method4(5.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final FloatOption field11 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "colorSaturationBrightness"
            )
            .method4(5.0F))
         .method8(0.0F, 10.0F))
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("grayscale")
      .method31();
   private LunarPostEffect field13 = null;

   public ColorSaturation() {
      super(false);
      this.handle(EventPostProcess.class, arg1 -> {
         if (this.field13 == null) {
            this.field13 = Ref.method4().method99().method2("color_saturation", new ColorSaturationShader());
         }

         arg1.method1();
         Bridge3_24 bridge3_242 = arg1.method3();
         this.field13.method2(arg1.method2(), bridge3_242, arg1x -> {
            arg1x.bridge$getShaderUniform("Hue").bridge$set(this.method2(this.field9));
            arg1x.bridge$getShaderUniform("Saturation").bridge$set(this.method2(this.field8));
            arg1x.bridge$getShaderUniform("Brightness").bridge$set(this.method2(this.field11));
            arg1x.bridge$getShaderUniform("Contrast").bridge$set(this.method2(this.field10));
         });
      });
   }

   public String getId() {
      return "COLOR_SATURATION";
   }

   public void method3(boolean flag1) {
      if (!flag1 && this.field13 != null) {
         this.field13.delete();
         this.field13 = null;
      }
   }

   private float method2(FloatOption lightingextension4721) {
      float value2 = lightingextension4721 == this.field8 && this.field12.get() ? 0.0F : (Float)lightingextension4721.get();
      if (value2 <= 0.0F) {
         return 0.0F;
      }

      float value3 = value2 / 10.0F;
      if (value3 >= 1.0F) {
         value3 = 0.99F;
      }

      return value3;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field8, this.field11, this.field10, this.field12});
   }

   @Generated
   public FloatOption method13() {
      return this.field8;
   }

   @Generated
   public FloatOption method14() {
      return this.field9;
   }

   @Generated
   public FloatOption method15() {
      return this.field10;
   }

   @Generated
   public FloatOption method16() {
      return this.field11;
   }
}
