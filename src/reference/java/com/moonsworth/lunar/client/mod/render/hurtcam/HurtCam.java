package com.moonsworth.lunar.client.mod.render.hurtcam;

import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HurtCam extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("disableHurtCam")
      .method31();
   private final FloatOption field9 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("hurtShakingIntensity")
            .method4(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   private final ToggleOption field10 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("oldCameraTilt")
      .method31();

   public HurtCam() {
      super(false);
   }

   public String getId() {
      return "HURT_CAM";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field9})).method2(this.field8::get);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field10})).method6(16);
   }

   protected ModDetails method20() {
      return ModDetails.method7().method2(new String[]{"nohurtcam", "nohitshake", "nohurtshake", "nohitcam"}).method11(this);
   }

   public float method13() {
      float value1 = 1.0F;
      if (Ref.method4().method40().method81().isEnabled()) {
         if ((Boolean)Ref.method4().method40().method81().method14().get()) {
            value1 = 0.0F;
         } else {
            value1 = (Float)Ref.method4().method40().method81().method15().get();
         }
      }

      return value1;
   }

   @Generated
   public ToggleOption method14() {
      return this.field8;
   }

   @Generated
   public FloatOption method15() {
      return this.field9;
   }

   @Generated
   public ToggleOption method16() {
      return this.field10;
   }
}
