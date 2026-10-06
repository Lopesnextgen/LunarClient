package com.moonsworth.lunar.client.mod.misc.debug;

import com.lunarclient.websocket.cosmetic.v2.BiomeTemperatureCondition;
import com.lunarclient.websocket.cosmetic.v2.DimensionCondition;
import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.InLiquidCondition;
import com.lunarclient.websocket.cosmetic.v2.MovementStateCondition;
import com.lunarclient.websocket.cosmetic.v2.OnFireCondition;
import com.lunarclient.websocket.cosmetic.v2.PassengerOfEntityCondition;
import com.lunarclient.websocket.cosmetic.v2.RadioPlayingCondition;
import com.lunarclient.websocket.cosmetic.v2.ServerCondition;
import com.lunarclient.websocket.cosmetic.v2.TimeCondition;
import com.lunarclient.websocket.cosmetic.v2.WeatherCondition;
import com.lunarclient.websocket.cosmetic.v2.BiomeTemperatureCondition.Temperature;
import com.lunarclient.websocket.cosmetic.v2.InLiquidCondition.Liquid;
import com.lunarclient.websocket.cosmetic.v2.MovementStateCondition.State;
import com.lunarclient.websocket.cosmetic.v2.TimeCondition.Time;
import com.lunarclient.websocket.cosmetic.v2.WeatherCondition.Weather;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class CosmeticDebugMod extends AbstractFeature {
   public CosmeticDebugMod() {
      super(false);
      this.method45(ModTraits.field1, new CosmeticDebugMod.Data());
   }

   public String getId() {
      return "COSMETIC_DEBUG_MOD";
   }

   protected String method18() {
      return "Cosmetic Debug";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   private static class Data extends TypedHudRenderer<List<TextComponent>> {
      private final ColorOption field31 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "enabledColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16736512))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      private final ColorOption field32 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "disabledColor"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-6356992))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      private final List<EquipConditionPredicate<?>> field33 = List.of(
         EquipConditionPredicate.method4(
            EquipCondition.newBuilder().setMovementState(MovementStateCondition.newBuilder().setState(State.STATE_SWIMMING).build()).build()
         ),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setInLiquid(InLiquidCondition.newBuilder().setLiquid(Liquid.LIQUID_WATER).build()).build()),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setOnFire(OnFireCondition.newBuilder().build()).build()),
         EquipConditionPredicate.method4(
            EquipCondition.newBuilder().setPassengerOfEntity(PassengerOfEntityCondition.newBuilder().setEntityId("minecraft:pig").build()).build()
         ),
         EquipConditionPredicate.method4(
            EquipCondition.newBuilder().setBiomeTemperature(BiomeTemperatureCondition.newBuilder().setTemperature(Temperature.TEMPERATURE_HOT).build()).build()
         ),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setRadioPlaying(RadioPlayingCondition.newBuilder().build()).build()),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setWeather(WeatherCondition.newBuilder().setWeather(Weather.WEATHER_RAIN).build()).build()),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setTime(TimeCondition.newBuilder().setTime(Time.TIME_DAY).build()).build()),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setServer(ServerCondition.newBuilder().setServerIp("example-server").build()).build()),
         EquipConditionPredicate.method4(EquipCondition.newBuilder().setDimension(DimensionCondition.newBuilder().setDimensionId("minecraft:overworld").build()).build())
      );

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method1(RootSettingsBuilder lightingextension231) {
         super.method1(lightingextension231);
         lightingextension231.method9(new ClientOption[]{this.field31, this.field32});
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method2(false).method2(false).method8();
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      public HudSize method15() {
         return HudSize.method1(18, 18, 700, 100, 200, 320);
      }

      @Nullable
      public List<TextComponent> method8(boolean flag1) {
         ArrayList list2 = new ArrayList();

         for (EquipConditionPredicate rewindhandlers4 : this.field33) {
            String text5 = rewindhandlers4.method3(Ref.method7());
            if (text5 != null) {
               list2.add(
                  (TextComponent)Component.text(rewindhandlers4.method5().getConditionCase().name() + ": ")
                     .append(Component.text(text5, TextColor.color(this.field31.method14(0.0F))))
               );
            }
         }

         return list2;
      }
   }
}
