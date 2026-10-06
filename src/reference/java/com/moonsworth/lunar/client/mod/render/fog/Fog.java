package com.moonsworth.lunar.client.mod.render.fog;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.render.EventFog.EventFogColor;
import com.moonsworth.lunar.client.event.render.EventFog.EventFogRange;
import com.moonsworth.lunar.client.event.render.EventFog.FogKind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.util.math.Easing;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Fog extends AbstractFeature {
   private final FloatOption field8 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("waterFogDensity")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.8F))
      .method31();
   private final FloatOption field9 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "renderDistanceFogDensity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.95F))
      .method31();
   private final FloatOption field10 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "atmosphericFogDensity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.95F))
      .method31();
   private final FloatOption field11 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("dimensionFogDensity")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.95F))
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("renderDistanceFogColorToggle")
      .method31();
   private final ToggleOption field13 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("atmosphericFogColorToggle")
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dimensionFogColorToggle")
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "renderDistanceFogColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(12638463))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "atmosphericFogColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(12636415))
      .method31();
   private final ColorOption field17 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dimensionFogColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(12636415))
      .method31();

   public Fog() {
      super(false);
      if (Ref.MC_VERSION <= 25) {
         this.handle(com.moonsworth.lunar.client.event.render.EventFog.FogRender.class, this::method2);
      } else {
         this.handle(EventFogRange.class, this::method3);
      }

      this.handle(EventFogColor.class, this::method1);
   }

   public String getId() {
      return "FOG";
   }

   private void method1(EventFogColor data21) {
      ColorOption lightingextension42222 = this.method5(data21.HROHCHROIIOCOIOHIRCCOHCRIIHROC());
      if (lightingextension42222 != null) {
         float value3 = (float)data21.method2();
         data21.method1(
            ColorUtils.method9(lightingextension42222.IROHICIOOHIRCOCHOOCROHROIIRRIC(value3)),
            ColorUtils.method9(lightingextension42222.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(value3)),
            ColorUtils.method9(lightingextension42222.IHIRROIOORHHCOOCCOOHHHCHOCCORR(value3))
         );
      }
   }

   @VersionGate(max = 25)
   private void method2(com.moonsworth.lunar.client.event.render.EventFog.FogRender data1) {
      float value2 = this.method4(data1.HROHCHROIIOCOIOHIRCCOHCRIIHROC());
      if (value2 != 1.0F && this.method13()) {
         switch (data1.method4()) {
            case DENSITY:
               if (Ref.MC_VERSION <= 7) {
                  data1.method1(data1.method5() * (value2 * 2.0F));
               }
               break;
            case START:
               data1.method1(this.method7(value2, data1.method5()));
               break;
            case END:
               data1.method1(this.method8(value2, data1.method5()));
         }
      }
   }

   @VersionGate(min = 26)
   private void method3(EventFogRange data31) {
      float value2 = this.method4(data31.HROHCHROIIOCOIOHIRCCOHCRIIHROC());
      if (value2 != 1.0F && this.method13()) {
         data31.method1(this.method7(value2, data31.method4()), this.method8(value2, data31.method5()));
      }
   }

   private float method4(FogKind type21) {
      return switch (type21) {
         case WATER -> this.field8.get();
         case RENDER_DISTANCE -> this.field9.get();
         case ATMOSPHERIC -> this.field10.get();
         case DIMENSION -> this.field11.get();
         default -> 1.0F;
      };
   }

   @Nullable
   private ColorOption method5(FogKind type21) {
      return switch (type21) {
         case RENDER_DISTANCE -> this.field12.get() ? this.field15 : null;
         case ATMOSPHERIC -> this.field13.get() ? this.field16 : null;
         case DIMENSION -> this.field14.get() ? this.field17 : null;
         default -> null;
      };
   }

   private boolean method13() {
      return Bridge.method5().map(arg0 -> !arg0.getConfig().isFogOff()).orElse(true);
   }

   private float method7(float value1, float value2) {
      if (Ref.MC_VERSION <= 29) {
         return value1 == 0.0F ? (value2 == 0.0F ? 50.0F : value2 * 5.0F) : (value2 == 0.0F ? 0.1F : value2) * Math.max(2.0F - value1, 0.01F);
      }

      float value3 = Easing.field14.method3(0.001F, 0.001F, 1.0F, 1.0F, value1);
      return value2 == 0.0F ? 0.1F : value2 * value3;
   }

   private float method8(float value1, float value2) {
      return value1 == 0.0F ? value2 * 5.0F : value2 * Math.max(2.0F - value1, 0.01F);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1("fogDensity", arg1x -> {
         arg1x.method9(new ClientOption[]{this.field8});
         arg1x.method9(new ClientOption[]{this.field9}).IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(29);
         arg1x.method9(new ClientOption[]{this.field10, this.field11}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(30);
      });
      lightingextension231.method1(
         "fogColor",
         arg1x -> {
            arg1x.method7(this.field12, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}))
               .IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(29);
            arg1x.method7(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field16}))
               .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(30);
            arg1x.method7(this.field14, arg1xx -> arg1xx.method9(new ClientOption[]{this.field17}))
               .RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(30);
         }
      );
      if (Ref.MC_VERSION >= 8) {
         this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> Ref.method4().method89().clear());
      }
   }

   @Generated
   public FloatOption method14() {
      return this.field9;
   }
}
