package com.moonsworth.lunar.client.util.collection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;

public class ExpiringWeightedCache<K, V> {
   private final Map<K, ExpiringWeightedCache.Data<V>> field1 = new LinkedHashMap<>();
   private final Function<V, Long> field2;
   private final BiConsumer<K, V> field3;
   private ExpiringWeightedCache.Data<V> field4;
   private final long field5;
   private final long field6;
   private long field7;
   private final long field8;

   public ExpiringWeightedCache(Function<V, Long> function1, BiConsumer<K, V> biconsumer2, long number3, long number5, long number7) {
      this.field2 = function1;
      this.field3 = biconsumer2;
      this.field5 = number3;
      this.field6 = number5;
      this.field8 = number7;
      this.method1();
   }

   public void method1() {
      BackgroundExecutor.method5().schedule(this::method1, 1L, TimeUnit.SECONDS);
      BackgroundExecutor.method7(() -> {
         long number1 = System.nanoTime();
         this.method2(number1);
      });
   }

   public V put(K value1, V value2) {
      synchronized (this.field1) {
         long number4 = System.nanoTime();
         ExpiringWeightedCache.Data data6 = new ExpiringWeightedCache.Data<>(number4, this.field2.apply((V)value2), value2);
         ExpiringWeightedCache.Data data7 = this.field1.remove(value1);
         this.field1.put((K)value1, data6);
         if (this.field1.size() == 1) {
            this.field4 = data6;
         }

         this.field7 = this.field7 + data6.field2;
         if (data7 == null) {
            this.method2(number4);
            return null;
         }

         this.field7 = this.field7 - data7.field2;
         if (data7 == this.field4) {
            this.field4 = this.field1.values().iterator().next();
         }

         this.field3.accept((K)value1, data7.value);
         this.method2(number4);
         return data7.value;
      }
   }

   public V get(K value1) {
      synchronized (this.field1) {
         ExpiringWeightedCache.Data data3 = this.field1.remove(value1);
         if (data3 == null) {
            return null;
         }

         this.field7 = this.field7 - data3.field2;
         this.field7 = this.field7 + (data3.field2 = this.field2.apply(data3.value));
         this.field1.put((K)value1, data3);
         if (this.field1.size() > 1 && data3 == this.field4) {
            this.field4 = this.field1.values().iterator().next();
         }

         data3.field1 = System.nanoTime();
         this.method2(data3.field1);
         return data3.value;
      }
   }

   public V remove(K value1) {
      synchronized (this.field1) {
         ExpiringWeightedCache.Data data3 = this.field1.remove(value1);
         if (data3 == null) {
            return null;
         }

         this.field7 = this.field7 - data3.field2;
         this.field3.accept((K)value1, data3.value);
         if (!this.field1.isEmpty()) {
            this.field4 = this.field1.values().iterator().next();
         } else {
            this.field4 = null;
         }

         return data3.value;
      }
   }

   private void method2(long number1) {
      synchronized (this.field1) {
         if (!this.field1.isEmpty()) {
            if (number1 - this.field4.field1 >= this.field5) {
               long number4 = this.method3();
               boolean flag6 = number1 - this.field4.field1 > this.field6 || number4 > this.field8;
               if (flag6) {
                  this.method4(number1);
                  number4 = this.method3();
                  if (number4 > this.field8) {
                     this.method5(number1);
                  }

                  if (!this.field1.isEmpty()) {
                     this.field4 = this.field1.values().iterator().next();
                  } else {
                     this.field4 = null;
                  }
               }
            }
         }
      }
   }

   public long method3() {
      synchronized (this.field1) {
         return this.field7;
      }
   }

   private void method4(long number1) {
      LinkedList list3 = new LinkedList();

      for (Entry entry5 : this.field1.entrySet()) {
         ExpiringWeightedCache.Data data6 = (ExpiringWeightedCache.Data)entry5.getValue();
         if (number1 - data6.field1 <= this.field6) {
            break;
         }

         this.field7 = this.field7 - data6.field2;
         list3.add(entry5.getKey());
      }

      for (Object obj8 : list3) {
         ExpiringWeightedCache.Data data9 = this.field1.remove(obj8);
         this.field3.accept((K)obj8, data9.value);
      }
   }

   private void method5(long number1) {
      LinkedList list3 = new LinkedList();

      for (Entry entry5 : this.field1.entrySet()) {
         ExpiringWeightedCache.Data data6 = (ExpiringWeightedCache.Data)entry5.getValue();
         if (number1 - data6.field1 <= this.field5) {
            break;
         }

         this.field7 = this.field7 - data6.field2;
         list3.add(entry5.getKey());
         if (this.field7 <= this.field8) {
            break;
         }
      }

      for (Object obj8 : list3) {
         ExpiringWeightedCache.Data data9 = this.field1.remove(obj8);
         this.field3.accept((K)obj8, data9.value);
      }
   }

   public void method6(Consumer<V> consumer1) {
      synchronized (this.field1) {
         for (ExpiringWeightedCache.Data data4 : this.field1.values()) {
            consumer1.accept(data4.value);
         }
      }
   }

   public void clear() {
      synchronized (this.field1) {
         for (Entry entry3 : new ArrayList<>(this.field1.entrySet())) {
            this.field3.accept((K)entry3.getKey(), ((ExpiringWeightedCache.Data)entry3.getValue()).value);
         }

         this.field7 = 0L;
         this.field1.clear();
         this.field4 = null;
      }
   }

   public int method7() {
      synchronized (this.field1) {
         return this.field1.size();
      }
   }

   private static class Data<V> {
      long field1;
      long field2;
      V value;

      public Data(long number1, long number3, V value5) {
         this.field1 = number1;
         this.field2 = number3;
         this.value = (V)value5;
      }
   }
}
