package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommission;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.CommissionListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class CommissionEvent extends LunarEvent implements DynamicListenerEvent {
   private final SkyBlockCommission field1;

   @Generated
   public SkyBlockCommission method1() {
      return this.field1;
   }

   @Generated
   public CommissionEvent(SkyBlockCommission fishing31) {
      this.field1 = fishing31;
   }

   @TriggeredBy(CommissionListener.class)
   public static class Data extends CommissionEvent {
      public Data(SkyBlockCommission fishing31) {
         super(fishing31);
      }
   }

   @TriggeredBy(CommissionListener.class)
   public static class CommissionStartEvent extends CommissionEvent {
      public CommissionStartEvent(SkyBlockCommission fishing31) {
         super(fishing31);
      }
   }

   @TriggeredBy(CommissionListener.class)
   public static class CommissionRemoveEvent extends CommissionEvent {
      public CommissionRemoveEvent(SkyBlockCommission fishing31) {
         super(fishing31);
      }
   }
}
