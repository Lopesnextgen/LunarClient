package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.WeatherCondition;
import com.lunarclient.websocket.cosmetic.v2.WeatherCondition.Weather;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class ConditionEquipCondition extends EquipConditionPredicate<WeatherCondition> {
   public ConditionEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, WeatherCondition var2) {
      if (var1 == null) {
         return false;
      }

      Weather var3 = this.method4(var1);
      return var3 != Weather.WEATHER_UNSPECIFIED && var3 == var2.getWeather();
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      }

      Weather var2 = this.method4(var1);
      return var2 == Weather.WEATHER_UNSPECIFIED ? null : var2.name();
   }

   private Weather method4(Bridge6_10 var1) {
      Itemcounter6 var2 = var1.bridge$getWorld();
      if (var2 == null) {
         return Weather.WEATHER_UNSPECIFIED;
      }

      if (var2.bridge$isThundering()) {
         return Weather.WEATHER_THUNDERSTORM;
      }

      if (var2.bridge$isRaining()) {
         float var3 = this.method5(var1);
         if (var3 >= 2.0F) {
            return Weather.WEATHER_CLEAR;
         } else {
            return var3 < 0.15F ? Weather.WEATHER_SNOW : Weather.WEATHER_RAIN;
         }
      } else {
         return Weather.WEATHER_CLEAR;
      }
   }

   private float method5(Bridge6_10 var1) {
      Itemcounter6 var2 = var1.bridge$getWorld();
      if (var2 == null) {
         return 0.2F;
      }

      int var3 = (int)Math.floor(var1.bridge$getPosX());
      int var4 = (int)Math.floor(var1.bridge$getPosY());
      int var5 = (int)Math.floor(var1.bridge$getPosZ());
      Itemcounter_3 var6 = var2.bridge$getBiome(var3, var4, var5);
      return var6 == null ? 0.2F : var6.bridge$getTemperature(Bridge.method8().method4(var3, var4, var5));
   }
}
