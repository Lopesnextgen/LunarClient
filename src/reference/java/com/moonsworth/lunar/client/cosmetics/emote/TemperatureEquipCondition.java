package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.BiomeTemperatureCondition;
import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.BiomeTemperatureCondition.Temperature;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;
import org.apache.commons.lang3.Range;

public class TemperatureEquipCondition extends EquipConditionPredicate<BiomeTemperatureCondition> {
   public static final float field2 = 0.2F;
   private static final Range<Float> field3 = Range.between(-1.0F, 0.0F);
   private static final Range<Float> field4 = Range.between(1.0F, 2.0F);

   public TemperatureEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, BiomeTemperatureCondition var2) {
      if (var1 == null) {
         return false;
      }

      float var3 = this.method4(var1);

      return switch (this.method3(var3)) {
         case TEMPERATURE_COLD -> var2.getTemperature() == Temperature.TEMPERATURE_COLD;
         case TEMPERATURE_HOT -> var2.getTemperature() == Temperature.TEMPERATURE_HOT;
         default -> false;
      };
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      }

      float var2 = this.method4(var1);
      Temperature var3 = this.method3(var2);
      return var3 == Temperature.TEMPERATURE_UNSPECIFIED ? null : var3.name();
   }

   private Temperature method3(float var1) {
      if (field3.contains(var1)) {
         return Temperature.TEMPERATURE_COLD;
      } else {
         return field4.contains(var1) ? Temperature.TEMPERATURE_HOT : Temperature.TEMPERATURE_UNSPECIFIED;
      }
   }

   private float method4(Bridge6_10 var1) {
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
