package com.moonsworth.lunar.client.mod.render.particlechanger;

import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.render.Gui2Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Random;
import lombok.Generated;

public class ParticleStyle extends AbstractFeature {
   private final ParticleChanger particleChanger;
   private final HorsestatsType2 particleType;
   private final ToggleOption overlayColor = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("overlayColor")
      .method31();
   private final EnumOption<Gui2Extension> colorMode = (EnumOption<Gui2Extension>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "colorMode", Gui2Extension.OVERLAY
      )
      .method31();
   private final ColorOption color = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "color"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final FloatOption scale = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "scale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 2.0F))
      .method31();
   private final FloatOption particleMultiplier = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "particleMultiplier"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 10.0F))
      .method31();
   private final ToggleOption hideParticle = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideParticle")
      .method31();

   protected ParticleStyle(ParticleChanger var1, HorsestatsType2 var2) {
      super(false);
      this.particleChanger = var1;
      this.particleType = var2;
      this.method14(Framework.field16, Framework4.method3(var1));
   }

   @Override
   public String getId() {
      throw new IllegalStateException("ParticleStyle must be created using ParticleStyle.create()!");
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method4(this.particleType::getDisplayName).method11(this);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.scale, this.particleMultiplier})).method2(this::method13);
      ((SettingsSectionImpl)var1.method12("disallowedParticle")).method4(true).method2(() -> !this.method13());
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hideParticle});
      ((SettingsSectionImpl)var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.overlayColor, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.color, this.colorMode})
         ))
         .method2(this::method13);
   }

   private boolean method13() {
      return ThreadModuleDump63.method3().bridge$getCurrentServerData() == null
         ? false
         : this.particleType == HorsestatsType2.BLOCK || this.particleType == HorsestatsType2.BUBBLE || this.particleType == HorsestatsType2.BUBBLE_POP;
   }

   public boolean isActive() {
      return this.isEnabled() && !this.method13();
   }

   public boolean method14() {
      return this.isEnabled() && this.hideParticle.get();
   }

   public int getParticleCount(Random var1) {
      if (!this.isActive()) {
         return 1;
      }

      float var2 = this.particleMultiplier.get();
      int var3 = (int)var2;
      float var4 = var2 - var3;
      if (var1.nextFloat() <= var4) {
         var3++;
      }

      return var3;
   }

   private float applyColorMode(float var1, float var2) {
      if (!this.overlayColor.get()) {
         return var1;
      } else {
         return this.colorMode.get() == Gui2Extension.OVERLAY ? var1 * var2 : var2;
      }
   }

   public float applyRed(float var1) {
      return this.applyColorMode(var1, ThreadModuleDump23.method9(this.color.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F)));
   }

   public float applyGreen(float var1) {
      return this.applyColorMode(var1, ThreadModuleDump23.method9(this.color.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F)));
   }

   public float applyBlue(float var1) {
      return this.applyColorMode(var1, ThreadModuleDump23.method9(this.color.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F)));
   }

   public float applyAlpha(float var1) {
      return this.overlayColor.get() ? ThreadModuleDump23.method9(this.color.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F)) : var1;
   }

   public float applyScale(float var1) {
      return var1 * this.scale.get();
   }

   public double jitter(Random var1, double var2) {
      return var2 + var1.nextFloat(-0.2F, 0.2F);
   }

   public double method13(Random var1, double var2) {
      return var2 * var1.nextFloat(0.6F, 1.3F);
   }

   public static ParticleStyle method14(ParticleChanger var0, HorsestatsType2 var1) {
      String var2 = var1.getAlias().toUpperCase();
      final String var3 = "PARTICLE_CHANGER_" + var2 + "_CHILD";
      return new ParticleStyle(var0, var1) {
         @Override
         public String getId() {
            return var3;
         }
      };
   }

   @Generated
   public ParticleChanger getParticleChanger() {
      return this.particleChanger;
   }
}
