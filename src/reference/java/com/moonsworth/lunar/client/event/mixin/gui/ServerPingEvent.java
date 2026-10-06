package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import javax.annotation.Nullable;
import lombok.Generated;

public class ServerPingEvent extends Highlight {
   @Nullable
   private Bridge3_19 field1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
   private final String field2;
   private final int field3;

   @Nullable
   @Generated
   public Bridge3_19 method1() {
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
   public ServerPingEvent(String var1, int value) {
      this.field2 = var1;
      this.field3 = value;
   }

   @Generated
   public void method3(@Nullable Bridge3_19 var1) {
      this.field1 = var1;
   }
}
