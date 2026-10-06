package com.moonsworth.lunar.client.mod.render;

import com.moonsworth.lunar.bridge.Itemcounter4Extension;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerBorderRegistry extends com.moonsworth.lunar.client.framework.ItemMapHandler<String, Itemcounter4Extension> implements EventRegistrar {
   public ServerBorderRegistry() {
      this.handle(ServerChangeEvent.class, this::method2);
   }

   @Override
   protected Map<String, Itemcounter4Extension> method3() {
      return new ConcurrentHashMap<>();
   }

   private void method2(ServerChangeEvent var1) {
      this.clear();
   }

   public void method3(String var1, Itemcounter4Extension var2) {
      this.method2().put(var1, var2);
   }

   public void method4(String var1) {
      this.method2().remove(var1);
   }

   public void method5(String var1, double var2, double var4, double var6, double var8, int var10) {
      Itemcounter4Extension var11 = (Itemcounter4Extension)this.method2().get(var1);
      var11.method1(var2, var4, var6, var8, var10);
   }

   public void method6(String var1, double var2, double var4, double var6, double var8, int var10, boolean var11, boolean flag, int value) {
      Itemcounter4Extension var14 = (Itemcounter4Extension)this.method2().get(var1);
      var14.method1(var2, var4, var6, var8, var10);
      var14.setCancelEntry(var11);
      var14.setCancelExit(flag);
      var14.setColor(value);
   }
}
