package com.moonsworth.lunar.client.mod.render.weatherchanger;

import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import lombok.Generated;

public class WeatherChanger extends AbstractFeature {
   private final EnumOption<WeatherChanger.Type> field8 = (EnumOption<WeatherChanger.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "weatherMode", WeatherChanger.Type.CLEAR
      )
      .method31();
   private final FloatOption field9 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("rainStrength")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "rainColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("thunderStorm")
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("playThunderSound")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field13 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("lightningFreq")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 20.0F))
      .method31();
   private final FloatOption field14 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("lightningRadiusXZ")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(128.0F))
         .method8(8.0F, 512.0F))
      .method31();
   private final FloatOption field15 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("lightningOffsetY")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-64.0F, 64.0F))
      .method31();

   public WeatherChanger() {
      super(false);
   }

   public String getId() {
      return "WEATHER_CHANGER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field9, this.field10}))
         .method2(() -> this.field8.get() == WeatherChanger.Type.CLEAR);
      lightingextension231.method7(
         this.field11, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12, this.field13, this.field14, this.field15})
      );
   }

   public float method13() {
      return this.method14().get() == WeatherChanger.Type.CLEAR ? 0.0F : (Float)this.field9.get();
   }

   public boolean method3(float value1) {
      if (!this.isEnabled()) {
         return false;
      }

      return switch ((WeatherChanger.Type)this.field8.get()) {
         case RAIN, SNOW -> true;
         case NATURAL -> value1 > 0.0F;
         case CLEAR -> false;
      };
   }

   @Generated
   public EnumOption<WeatherChanger.Type> method14() {
      return this.field8;
   }

   @Generated
   public FloatOption method15() {
      return this.field9;
   }

   @Generated
   public ColorOption method16() {
      return this.field10;
   }

   @Generated
   public ToggleOption method17() {
      return this.field11;
   }

   @Generated
   public ToggleOption method19() {
      return this.field12;
   }

   @Generated
   public FloatOption method21() {
      return this.field13;
   }

   @Generated
   public FloatOption method22() {
      return this.field14;
   }

   @Generated
   public FloatOption method23() {
      return this.field15;
   }

   public enum Type implements OptionEnumValue {
      NATURAL("natural"),
      CLEAR("clear"),
      RAIN("rain"),
      SNOW("snow");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
