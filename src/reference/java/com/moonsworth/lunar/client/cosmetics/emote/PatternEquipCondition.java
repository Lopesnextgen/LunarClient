package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.ServerCondition;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class PatternEquipCondition extends EquipConditionPredicate<ServerCondition> {
   public PatternEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, ServerCondition var2) {
      String var3 = this.method4(var1);
      return var3 == null ? false : var3.equalsIgnoreCase(var2.getServerIp());
   }

   @Override
   public String method3(Bridge6_10 var1) {
      return var1 == null ? null : this.method4(var1);
   }

   private String method4(Bridge6_10 var1) {
      return ThreadModuleDump63.method3() != null && ThreadModuleDump63.method3().bridge$getCurrentServerData() != null
         ? ThreadModuleDump63.method3().bridge$getCurrentServerData().bridge$serverIP()
         : null;
   }
}
