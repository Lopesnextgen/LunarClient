package com.moonsworth.lunar.client.event;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Deque;
import java.util.Map.Entry;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ClientEventBusDebug extends ClientEventBus {
   private static final boolean field10 = false;
   private static final boolean field11 = false;
   private static final boolean field12 = false;
   protected final Deque<Class<? extends Highlight>> field13 = null;

   public ClientEventBusDebug() {
      if (LunarBuildData.field4) {
         throw new IllegalAccessError("DebuggingEventBus is being used in production environment!");
      }
   }

   public Object2IntMap<String> method1() {
      Object2IntOpenHashMap var1 = new Object2IntOpenHashMap();

      for (Entry var3 : this.field8.entrySet()) {
         int var4 = ((ListenerRegistration[])var3.getValue()).length;
         if (var4 > 1) {
            var1.put(this.method6((Class<?>)var3.getKey()), var4);
         }
      }

      return var1;
   }

   @Override
   protected void method21(ClientEventBus.EventListenerChange<?> var1) {
      this.field6.add(var1);
   }

   @Override
   protected void method18(Class<? extends Highlight> var1) {
      super.method18(var1);
   }

   @Override
   protected void method19() {
      super.method19();
   }

   @Override
   protected void method20() {
      if (!this.field6.isEmpty()) {
         for (ClientEventBus.EventListenerChange var2 : this.field6) {
            if (var2.method1()) {
               this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var2.method2(), var2.method3(), var2.priority());
            } else {
               this.HRICOROOOCCOCOROCRHHCRRIRCOICO(var2.method2(), var2.method3());
            }
         }

         this.field6.clear();
      }
   }

   private String method6(Class<?> var1) {
      String var2 = var1.getSimpleName();
      int var3 = var2.length();
      if (var3 > 8 && var2.contains("Event")) {
         return var2;
      }

      String var4 = var1.getName();
      int var5 = var4.lastIndexOf(46);
      return var5 != -1 && var4.length() - var5 >= 8 ? var4.substring(var5 + 1) : var4;
   }
}
