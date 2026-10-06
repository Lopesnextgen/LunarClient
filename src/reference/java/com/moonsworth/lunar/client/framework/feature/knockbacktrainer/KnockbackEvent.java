package com.moonsworth.lunar.client.framework.feature.knockbacktrainer;

import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.KnockbackListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@TriggeredBy(KnockbackListener.class)
public class KnockbackEvent extends LunarEvent implements DynamicListenerEvent {
   private final boolean field1;
   @Nullable
   private final DamageSourceBridge field2;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public DamageSourceBridge method2() {
      return this.field2;
   }

   @Generated
   public KnockbackEvent(boolean flag, @Nullable DamageSourceBridge horsestats192) {
      this.field1 = flag;
      this.field2 = horsestats192;
   }
}
