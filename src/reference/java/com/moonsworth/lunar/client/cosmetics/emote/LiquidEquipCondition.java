package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.InLiquidCondition;
import com.lunarclient.websocket.cosmetic.v2.InLiquidCondition.Liquid;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class LiquidEquipCondition extends EquipConditionPredicate<InLiquidCondition> {
   public LiquidEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, InLiquidCondition var2) {
      if (var1 == null) {
         return false;
      }

      return switch (this.method4(var1)) {
         case LIQUID_WATER -> var2.getLiquid() == Liquid.LIQUID_WATER;
         case LIQUID_LAVA -> var2.getLiquid() == Liquid.LIQUID_LAVA;
         default -> false;
      };
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      }

      Liquid var2 = this.method4(var1);
      return var2 == Liquid.LIQUID_UNSPECIFIED ? null : var2.name();
   }

   private Liquid method4(Bridge6_10 var1) {
      if (var1.bridge$isInWater()) {
         return Liquid.LIQUID_WATER;
      } else {
         return var1.bridge$isInLava() ? Liquid.LIQUID_LAVA : Liquid.LIQUID_UNSPECIFIED;
      }
   }
}
