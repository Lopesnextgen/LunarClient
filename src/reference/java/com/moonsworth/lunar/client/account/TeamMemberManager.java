package com.moonsworth.lunar.client.account;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldLoaded;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.memory.Memory;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TeamMemberManager extends com.moonsworth.lunar.client.framework.ItemMapHandler<UUID, Memory> implements EventRegistrar {
   public TeamMemberManager() {
      this.handle(ServerChangeEvent.class, this::method1);
      this.handle(EventWorldLoaded.class, this::method2);
   }

   private void method1(ServerChangeEvent var1) {
      this.clear();
   }

   private void method2(EventWorldLoaded var1) {
      for (Memory var3 : this.method3().values()) {
         var3.method2();
      }
   }

   @Override
   protected Map<UUID, Memory> method3() {
      return new ConcurrentHashMap<>();
   }

   public void method4(Memory var1) {
      if (!this.method3().containsKey(var1.method10())) {
         this.method3().put(var1.method10(), var1);
         var1.method41(System.currentTimeMillis());
      }
   }

   public void method5(Memory var1) {
      this.method3().remove(var1.method10());
   }

   public boolean method6(Memory var1) {
      String var2 = var1.method23();
      return var2 == null || Client.method109().method16(var2);
   }
}
