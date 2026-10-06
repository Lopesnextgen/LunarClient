package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.framework.Ref;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventServerPing extends LunarEvent {
   @Nullable
   private ServerDataBridge field1 = Ref.method3().bridge$getCurrentServerData();
   private final String field2;
   private final int field3;

   @Nullable
   @Generated
   public ServerDataBridge method1() {
      return this.field1;
   }

   @Generated
   public String method2() {
      return this.field2;
   }

   @Generated
   public int getPort() {
      return this.field3;
   }

   @Generated
   public EventServerPing(String text, int value) {
      this.field2 = text;
      this.field3 = value;
   }

   @Generated
   public void method3(@Nullable ServerDataBridge bridge3_191) {
      this.field1 = bridge3_191;
   }
}
