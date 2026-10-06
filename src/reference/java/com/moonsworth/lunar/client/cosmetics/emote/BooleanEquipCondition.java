package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.RadioPlayingCondition;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;
import com.moonsworth.lunar.client.mod.misc.radio.Radio;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class BooleanEquipCondition extends EquipConditionPredicate<RadioPlayingCondition> {
   public BooleanEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, RadioPlayingCondition var2) {
      return var1 == null ? false : this.method4(var1);
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      } else {
         return this.method4(var1) ? this.method5().getConditionCase().name() : null;
      }
   }

   private boolean method4(Bridge6_10 var1) {
      if (var1 == null) {
         return false;
      }

      if (!var1.bridge$isSelf()) {
         return ThreadModuleDump63.method4().method53().method63().getOrDefault(var1.bridge$getUniqueID(), null) != null
            && ThreadModuleDump63.method4().method53().method63().get(var1.bridge$getUniqueID()).method10();
      }

      Radio var2 = ThreadModuleDump63.method4().method40().method62();
      return var2 != null && var2.method13();
   }
}
