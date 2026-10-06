package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.BooleanOptionBuilder;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.config.option.SettingsComposer;
import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;
import org.jetbrains.annotations.Contract;

public class LightingExtension443 extends com.moonsworth.lunar.client.config.option.ToggleOption {
   private static final float field9 = 3.0F;
   private static final int field10 = 10;
   private final FloatOption field11;
   private final SoundOption field12;
   private final ComparableImpl.Type field13;

   protected LightingExtension443(
      String var1, Codec<Boolean> var2, Boolean var3, FloatOption.Data var4, SoundOption.Data var5, ComparableImpl.Type var6
   ) {
      super(var1, var2, var3);
      this.field11 = (FloatOption)var4.method31();
      this.field12 = (SoundOption)var5.method31();
      this.field13 = var6;
   }

   public void method1(SettingsComposer<?, RootSettingsAssembler.Data> var1) {
      var1.method2(this, var1x -> var1x.method9(new ClientOption[]{this.field11, this.field12}));
   }

   @Contract("_->new")
   public static LightingExtension443.Data method10(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new LightingExtension443.Data(var0);
   }

   @Generated
   public FloatOption method10() {
      return this.field11;
   }

   @Generated
   public SoundOption method11() {
      return this.field12;
   }

   @Generated
   public ComparableImpl.Type method12() {
      return this.field13;
   }

   public static class Data extends BooleanOptionBuilder<LightingExtension443.Data, LightingExtension443> {
      private final FloatOption.Data field15;
      private final SoundOption.Data field16;
      private ComparableImpl.Type field17 = ComparableImpl.Type.NORMAL;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
         this.field15 = (FloatOption.Data)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(var1 + "Duration")
                  .OIRHORRROCHOIRCRHHORHRCIIRHROO("alertDuration"))
               .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 3.0F))
            .HHCRCHCOIHHORCHIRHIRHCRIIHOIIR(10);
         this.field16 = (SoundOption.Data)OptionFactory.method13(var1 + "Sound").OIRHORRROCHOIRCRHHORHRCIIRHROO("alertSound");
      }

      @Override
      protected boolean method8() {
         return true;
      }

      @Contract("_->this")
      public LightingExtension443.Data method2(float var1) {
         this.field15.CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(var1);
         return this;
      }

      @Contract("_->this")
      public LightingExtension443.Data method3(ComparableImpl.Type var1) {
         this.field17 = var1;
         return this;
      }

      protected LightingExtension443 method11() {
         return new LightingExtension443(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7, this.field15, this.field16, this.field17
         );
      }
   }
}
