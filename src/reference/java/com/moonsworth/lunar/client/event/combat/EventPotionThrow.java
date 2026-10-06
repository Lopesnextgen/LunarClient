package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventPotionThrow extends LunarEvent {
   private EntityLivingBridge field1;
   private ItemStackBridge field2;

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }

   @Generated
   public ItemStackBridge method2() {
      return this.field2;
   }

   @Generated
   public EventPotionThrow(EntityLivingBridge bridgeextension2_51, ItemStackBridge bridgeextension_42) {
      this.field1 = bridgeextension2_51;
      this.field2 = bridgeextension_42;
   }
}
