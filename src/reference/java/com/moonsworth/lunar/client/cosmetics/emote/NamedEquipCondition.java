package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.DimensionCondition;
import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class NamedEquipCondition extends EquipConditionPredicate<DimensionCondition> {
   public NamedEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, DimensionCondition var2) {
      if (var1 == null) {
         return false;
      }

      String var3 = this.method4(var1);
      return var3 != null && var3.equals(var2.getDimensionId());
   }

   @Override
   public String method3(Bridge6_10 var1) {
      return var1 == null ? null : this.method4(var1);
   }

   private String method4(Bridge6_10 var1) {
      Itemcounter6 var2 = var1.bridge$getWorld();
      return var2 == null ? null : var2.bridge$getDimensionKey();
   }
}
