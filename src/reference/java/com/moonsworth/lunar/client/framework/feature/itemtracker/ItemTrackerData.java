package com.moonsworth.lunar.client.framework.feature.itemtracker;

import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Generated;

class ItemTrackerData {
   private final Object2ObjectMap<String, List<ItemTrackerData.Data>> field1 = new Object2ObjectOpenHashMap();

   public ItemTrackerData(ItemTrackerData itemtracker1) {
      this.field1.putAll(itemtracker1.field1);
   }

   public Set<String> keySet() {
      return this.field1.keySet();
   }

   public int method1(String text1) {
      List list2 = (List)this.field1.get(text1);
      if (list2 == null) {
         return 0;
      }

      int number3 = 0;

      for (ItemTrackerData.Data data5 : list2) {
         number3 += data5.field2;
      }

      return number3;
   }

   public int method2(String text1, Int2BooleanMap int2booleanmap2) {
      List list3 = (List)this.field1.get(text1);
      if (list3 == null) {
         return 0;
      }

      int number4 = 0;

      for (ItemTrackerData.Data data6 : list3) {
         if (!int2booleanmap2.get(data6.field1)) {
            number4 += data6.field2;
         }
      }

      return number4;
   }

   public void method3(String text1, int number2, int number3) {
      ((List)this.field1.computeIfAbsent(text1, arg0 -> new ArrayList())).add(new ItemTrackerData.Data(number2, number3));
   }

   public void remove(String text1) {
      this.field1.remove(text1);
   }

   @Generated
   public ItemTrackerData() {
   }

   private class Data {
      private final int field1;
      private final int field2;

      private Data(int number1, int number2) {
         this.field1 = number1;
         this.field2 = number2;
      }

      public int method1() {
         return this.field1;
      }

      public int count() {
         return this.field2;
      }
   }
}
