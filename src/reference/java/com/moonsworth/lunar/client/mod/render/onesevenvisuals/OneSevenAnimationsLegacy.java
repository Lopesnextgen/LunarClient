package com.moonsworth.lunar.client.mod.render.onesevenvisuals;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.onesevenvisuals.ConsumerImpl;
import com.moonsworth.lunar.client.framework.feature.onesevenvisuals.Onesevenvisuals;
import com.moonsworth.lunar.client.event.render.EventEyeHeight;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(1)
public class OneSevenAnimationsLegacy extends AbstractFeature {
   private final ToggleOption healthAnimation = OneSevenVisuals.toggleOption("healthAnimation");
   private final ToggleOption glintAnimation = OneSevenVisuals.toggleOption("glintAnimation");
   private final ToggleOption hurtCameraShake = OneSevenVisuals.toggleOption("hurtCameraShake");
   private final ToggleOption sneakAnimation = OneSevenVisuals.toggleOption("sneakAnimation");
   private final ToggleOption sneakEyeHeight = OneSevenVisuals.toggleOption("sneakEyeHeight");
   private final FloatOption sneakSpeed = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("sneakSpeed")
            .method4(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   private final Onesevenvisuals animationState = new Onesevenvisuals(this.sneakAnimation, this.sneakEyeHeight);

   public OneSevenAnimationsLegacy(OneSevenVisuals onesevenvisuals1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(onesevenvisuals1));
      this.handle(EventEyeHeight.class, this.animationState::method3);
      this.method14(EventTick.class, this.animationState::method1);
      this.handle(EventRenderItemGlint.class, new ConsumerImpl(this.glintAnimation));
   }

   public String getId() {
      return "ONE_SEVEN_ANIMATIONS_LEGACY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method12("glintColorizerPriority"))
         .method4(true)
         .method2(() -> !Ref.method4().method40().method26().isEnabled());
      lightingextension231.method9(new ClientOption[]{this.glintAnimation});
      ((SettingsSectionImpl)lightingextension231.method14()).method2(() -> !Ref.method4().method40().method26().isEnabled());
      lightingextension231.method9(new ClientOption[]{this.healthAnimation, this.hurtCameraShake});
      ((SettingsSectionImpl)lightingextension231.method12("sneaking")).method4(true).method2(() -> !(Boolean)this.sneakAnimation.get());
      lightingextension231.method9(new ClientOption[]{this.sneakAnimation});
      ((SettingsSectionImpl)lightingextension231.method14()).method2(() -> !(Boolean)this.sneakAnimation.get());
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.sneakEyeHeight}))
         .method2(() -> !(Boolean)this.sneakAnimation.get())
         .HOCIIROHCHHIORICCRHIIRIIRCRCOR();
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.sneakSpeed})).method2(() -> !(Boolean)this.sneakAnimation.get());
      this.sneakSpeed.CICORRHIOIIOORRRICCORIOIOCIHII(this.animationState::method4);
   }

   public boolean isHealthAnimationEnabled() {
      return this.isEnabled() && (Boolean)this.healthAnimation.get();
   }

   public boolean method14() {
      return this.isEnabled() && (Boolean)this.hurtCameraShake.get();
   }
}
