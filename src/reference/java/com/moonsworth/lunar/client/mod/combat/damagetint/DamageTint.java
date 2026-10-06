package com.moonsworth.lunar.client.mod.combat.damagetint;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_2;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase.EventRenderHudFocused;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;

public class DamageTint extends AbstractFeature {
   private static float field8 = 1.0F;
   private static int field9;
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("textures/misc/vignette.png");
   private static final ResourceLocationBridge field11 = ResourceLocationBridge.create("lunar", "sound/heartbeat.ogg");
   private final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("vignetteColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final FloatOption field13 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "vignetteIntensity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final IntegerOption field14 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "showVignetteBelow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(100))
         .method7(0, 100))
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("heartbeatAudio").method31();
   private final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "heartbeatAudioVolume"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();

   public DamageTint() {
      super(false);
      this.handle(EventTick.class, this::method1);
      this.handle(EventRenderHudFocused.class, this::method2);
   }

   public String getId() {
      return "DAMAGE_TINT";
   }

   private void method1(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         float value3 = bridge5extension_52.bridge$getHealth();
         field8 = value3 / bridge5extension_52.bridge$getMaxHealth();
         if (!this.method4(bridge5extension_52)) {
            if ((Boolean)this.field15.get()) {
               if (this.mc.bridge$isGamePaused()) {
                  return;
               }

               if (field9 <= 0) {
                  field9 = (int)(17.0F + value3 * 3.0F);
                  if (field8 == 0.0F || field8 > 0.5F) {
                     return;
                  }

                  if (field8 >= ((Integer)this.field14.get()).intValue() / 100.0F) {
                     return;
                  }

                  this.mc.bridge$getSoundHandler().bridge$playSound(field11, false, (Float)this.field16.get());
               } else {
                  field9--;
               }
            }
         }
      }
   }

   private void method2(EventRenderHudFocused data41) {
      if (Ref.method7() != null && Ref.method8() != null) {
         if (!this.method4(Ref.method7())) {
            if (field8 != 0.0F) {
               float value2 = ((Integer)this.field14.get()).intValue() / 100.0F;
               if (!(field8 >= value2)) {
                  float value3 = 1.0F - field8 / value2;
                  value3 *= this.field13.get();
                  value3 = ClampUtils.clamp(value3, 0.0F, 1.0F);
                  int number4 = ~ColorUtils.method34(this.field12.method14(0.0F) & 16777215, value3);
                  if (Ref.MC_VERSION >= 30) {
                     Bridge8_2 bridge8_25 = (Bridge8_2)data41.method2().method49().orElseThrow();
                     int number6 = bridge8_25.bridge$guiWidth();
                     int number7 = bridge8_25.bridge$guiHeight();
                     bridge8_25.bridge$blit$v1_21_6(MixinHelper_2.field29, field10, 0, 0, 0.0F, 0.0F, number6, number7, number6, number7, number4);
                  } else {
                     float value10 = data41.method3().method12();
                     float value11 = data41.method3().method13();
                     LcuiScreen.method41(
                        LunarRenderTypes.field11.get(field10),
                        data41.method2().method46().method29(),
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        value10,
                        value11,
                        value10,
                        value11,
                        number4
                     );
                  }
               }
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15, this.field16});
   }

   private boolean method4(Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$getPlayerCapabilities().bridge$isCreativeMode() || bridge5extension_51.bridge$isSpectator();
   }
}
