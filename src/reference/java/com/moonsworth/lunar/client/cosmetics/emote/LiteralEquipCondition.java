package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.OnFireCondition;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class LiteralEquipCondition extends EquipConditionPredicate<OnFireCondition> {
   public LiteralEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, OnFireCondition var2) {
      return var1 == null ? false : var1.bridge$isOnFire();
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      } else {
         return var1.bridge$isOnFire() ? this.method5().getConditionCase().name() : null;
      }
   }
}
