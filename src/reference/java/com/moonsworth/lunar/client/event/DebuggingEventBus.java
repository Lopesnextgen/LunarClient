package com.moonsworth.lunar.client.event;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Deque;
import java.util.Map.Entry;

public class DebuggingEventBus extends LunarEventBus {
   private static final boolean field10 = false;
   private static final boolean field11 = false;
   private static final boolean field12 = false;
   protected final Deque<Class<? extends LunarEvent>> field13 = null;

   public DebuggingEventBus() {
      if (LunarBuildData.field4) {
         throw new IllegalAccessError("DebuggingEventBus is being used in production environment!");
      }
   }

   public Object2IntMap<String> method1() {
      Object2IntOpenHashMap object2intopenhashmap1 = new Object2IntOpenHashMap();

      for (Entry entry3 : this.CCRIRCRRRHRHOIICIRRIOOICCCHCOR.entrySet()) {
         int number4 = ((EventListener[])entry3.getValue()).length;
         if (number4 > 1) {
            object2intopenhashmap1.put(this.method6((Class<?>)entry3.getKey()), number4);
         }
      }

      return object2intopenhashmap1;
   }

   @Override
   protected void method21(LunarEventBus.EventBusOperation<?> data21) {
      this.OHRRRORIOCCROHIRICRRCROCHHOCRC.add(data21);
   }

   @Override
   protected void method18(Class<? extends LunarEvent> clazz1) {
      super.method18(clazz1);
   }

   @Override
   protected void method19() {
      super.method19();
   }

   @Override
   protected void method20() {
      if (!this.OHRRRORIOCCROHIRICRRCROCHHOCRC.isEmpty()) {
         for (LunarEventBus.EventBusOperation data22 : this.OHRRRORIOCCROHIRICRRCROCHHOCRC) {
            if (data22.method1()) {
               this.method17(data22.method2(), data22.method3(), data22.priority());
            } else {
               this.method6(data22.method2(), data22.method3());
            }
         }

         this.OHRRRORIOCCROHIRICRRCROCHHOCRC.clear();
      }
   }

   private String method6(Class<?> clazz1) {
      String text2 = clazz1.getSimpleName();
      int number3 = text2.length();
      if (number3 > 8 && text2.contains("Event")) {
         return text2;
      }

      String text4 = clazz1.getName();
      int index5 = text4.lastIndexOf(46);
      return index5 != -1 && text4.length() - index5 >= 8 ? text4.substring(index5 + 1) : text4;
   }
}
