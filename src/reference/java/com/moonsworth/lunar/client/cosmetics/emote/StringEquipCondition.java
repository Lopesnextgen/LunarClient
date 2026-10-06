package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.PassengerOfEntityCondition;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap2Impl2;
import java.util.Locale;

public class StringEquipCondition extends EquipConditionPredicate<PassengerOfEntityCondition> {
   public StringEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, PassengerOfEntityCondition var2) {
      if (var1 == null) {
         return false;
      }

      String var3 = this.method4(var1);
      return var3 == null ? false : var3.equals(var2.getEntityId());
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      }

      String var2 = this.method4(var1);
      return var2 == null ? null : var2.toUpperCase(Locale.ROOT);
   }

   private String method4(Bridge6_10 var1) {
      if (!var1.bridge$isRiding()) {
         return null;
      }

      BridgeExtension var2 = var1.bridge$getRidingEntity();
      if (var2 == null) {
         return null;
      }

      String var3 = var2.bridge$getType();
      if (Bridge.getMinecraftVersion().method21() && Minimap2Impl2.field5.containsKey(var3)) {
         var3 = (String)Minimap2Impl2.field5.get(var3);
      }

      return var3;
   }
}
