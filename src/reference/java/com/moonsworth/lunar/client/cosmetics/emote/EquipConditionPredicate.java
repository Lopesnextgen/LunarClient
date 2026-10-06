package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.EquipCondition;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.emote.ConditionEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.LiteralEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.NamedEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.TemperatureEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.LiquidEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.TimeEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.StateEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.StringEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.BooleanEquipCondition;
import com.moonsworth.lunar.client.cosmetics.emote.PatternEquipCondition;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public abstract class EquipConditionPredicate<T> {
   private final EquipCondition field1;

   public boolean method1(@Nullable Bridge6_10 bridge6_101) {
      return bridge6_101 == null ? false : this.method2(bridge6_101, (T)this.method5());
   }

   public abstract boolean method2(Bridge6_10 bridge6_101, T value2);

   public abstract String method3(Bridge6_10 bridge6_101);

   public static EquipConditionPredicate<?> method4(EquipCondition equipcondition0) {
      return switch (equipcondition0.getConditionCase()) {
         case BIOME_TEMPERATURE -> new TemperatureEquipCondition(equipcondition0);
         case DIMENSION -> new NamedEquipCondition(equipcondition0);
         case IN_LIQUID -> new LiquidEquipCondition(equipcondition0);
         case MOVEMENT_STATE -> new StateEquipCondition(equipcondition0);
         case ON_FIRE -> new LiteralEquipCondition(equipcondition0);
         case PASSENGER_OF_ENTITY -> new StringEquipCondition(equipcondition0);
         case RADIO_PLAYING -> new BooleanEquipCondition(equipcondition0);
         case SERVER -> new PatternEquipCondition(equipcondition0);
         case TIME -> new TimeEquipCondition(equipcondition0);
         case WEATHER -> new ConditionEquipCondition(equipcondition0);
         default -> throw new IllegalArgumentException("Unknown EquipCondition: " + equipcondition0.getConditionCase());
      };
   }

   @Generated
   public EquipConditionPredicate(EquipCondition equipcondition1) {
      this.field1 = equipcondition1;
   }

   @Generated
   public EquipCondition method5() {
      return this.field1;
   }
}
