package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.lunarclient.websocket.cosmetic.v2.MovementStateCondition;
import com.lunarclient.websocket.cosmetic.v2.MovementStateCondition.State;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.EquipConditionPredicate;

public class StateEquipCondition extends EquipConditionPredicate<MovementStateCondition> {
   public StateEquipCondition(EquipCondition var1) {
      super(var1);
   }

   public boolean method1(Bridge6_10 var1, MovementStateCondition var2) {
      if (var1 == null) {
         return false;
      }

      State var3 = this.method4(var1);
      return var3 != State.STATE_UNSPECIFIED && var3 == var2.getState();
   }

   @Override
   public String method3(Bridge6_10 var1) {
      if (var1 == null) {
         return null;
      }

      State var2 = this.method4(var1);
      return var2 == State.STATE_UNSPECIFIED ? null : var2.name();
   }

   private State method4(Bridge6_10 var1) {
      if (var1.bridge$isSwimming()) {
         return State.STATE_SWIMMING;
      } else if (var1.bridge$isElytraFlying()) {
         return State.STATE_ELYTRA_FLYING;
      } else {
         return var1.bridge$isFlying() ? State.STATE_CREATIVE_FLYING : State.STATE_UNSPECIFIED;
      }
   }
}
