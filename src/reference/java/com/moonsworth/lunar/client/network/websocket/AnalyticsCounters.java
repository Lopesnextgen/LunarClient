package com.moonsworth.lunar.client.network.websocket;

import com.lunarclient.websocket.analytics.v1.CounterUpdate;
import com.lunarclient.websocket.analytics.v1.RecordCountersRequest;
import com.lunarclient.websocket.analytics.v1.RecordCountersRequest.Builder;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventLocalDeath;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntMap.FastEntrySet;
import java.util.Map;
import lombok.Generated;

public class AnalyticsCounters extends com.moonsworth.lunar.client.framework.ItemMapHandler<String, Integer> implements LoadableHandler, EventRegistrar {
   private static final int field2 = 60;
   private int field3 = 0;

   public AnalyticsCounters() {
      this.handle(EventLocalDeath.class, var1 -> this.method2("stat:minecraft.custom:minecraft.deaths", 1));
      this.handle(EventEverySecond.class, var1 -> this.method3(false));
      this.handle(ServerChangeEvent.class, var1 -> this.method3(true));
   }

   @Override
   protected Map<String, Integer> method3() {
      Object2IntOpenHashMap var1 = new Object2IntOpenHashMap();
      var1.defaultReturnValue(0);
      return var1;
   }

   public void method2(String var1, int var2) {
      ThreadModuleDump63.method3().bridge$submit(() -> {
         Object2IntOpenHashMap var3 = (Object2IntOpenHashMap)this.method2();
         var3.put(var1, var3.getInt(var1) + var2);
      });
   }

   private void method3(boolean var1) {
      if (!var1 && this.field3 < 60) {
         this.field3++;
      } else {
         this.field3 = 0;
         if (!this.method2().isEmpty()) {
            ThreadModuleDump63.method5().ifPresent(var1x -> {
               Builder var2 = RecordCountersRequest.newBuilder();
               FastEntrySet var3 = ((Object2IntOpenHashMap)this.method2()).object2IntEntrySet();
               ObjectIterator var4 = var3.fastIterator();

               while (var4.hasNext()) {
                  Entry var5 = (Entry)var4.next();
                  int var6 = var5.getIntValue();
                  if (var6 != 0) {
                     var2.addUpdates(CounterUpdate.newBuilder().setCounter((String)var5.getKey()).setDelta(var6).build());
                  }
               }

               var1x.method100().recordCounters(null, var2.build(), var0 -> {});
               this.clear();
            });
         }
      }
   }

   @Override
   public void close() {
      this.method3(true);
   }

   @Generated
   public int method4() {
      return this.field3;
   }
}
