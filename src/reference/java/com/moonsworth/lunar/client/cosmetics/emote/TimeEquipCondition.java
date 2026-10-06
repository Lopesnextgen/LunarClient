package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.TimeCondition;
import com.lunarclient.websocket.cosmetic.v2.TimeCondition.Time;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class TimeEquipCondition extends EquipConditionPredicate<TimeCondition> {
   public TimeEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, TimeCondition var2) {
      return var1 != null && var1.bridge$getWorld() != null ? this.method4(var1) == var2.getTime() : false;
   }

   @Override
   public String method3(Bridge6_10 var1) {
      return var1 != null && var1.bridge$getWorld() != null ? this.method4(var1).name() : null;
   }

   private Time method4(Bridge6_10 var1) {
      long var2 = var1.bridge$getWorld().bridge$getWorldTime() % 24000L;
      return var2 >= 0L && var2 < 13000L ? Time.TIME_DAY : Time.TIME_NIGHT;
   }
}
