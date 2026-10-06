package com.moonsworth.lunar.client.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ThreadModuleDump24<K, V> {
   private final Map<K, ThreadModuleDump24.Data<V>> field1 = new LinkedHashMap<>();
   private final Function<V, Long> field2;
   private final BiConsumer<K, V> field3;
   private ThreadModuleDump24.Data<V> field4;
   private final long field5;
   private final long field6;
   private long field7;
   private final long field8;

   public ThreadModuleDump24(Function<V, Long> var1, BiConsumer<K, V> var2, long var3, long var5, long var7) {
      this.field2 = var1;
      this.field3 = var2;
      this.field5 = var3;
      this.field6 = var5;
      this.field8 = var7;
      this.method1();
   }

   public void method1() {
      ThreadModuleDump37.method5().schedule(this::method1, 1L, TimeUnit.SECONDS);
      ThreadModuleDump37.method7(() -> {
         long var1 = System.nanoTime();
         this.method2(var1);
      });
   }

   public V put(K var1, V var2) {
      synchronized (this.field1) {
         long var4 = System.nanoTime();
         ThreadModuleDump24.Data var6 = new ThreadModuleDump24.Data<>(var4, this.field2.apply((V)var2), var2);
         ThreadModuleDump24.Data var7 = this.field1.remove(var1);
         this.field1.put((K)var1, var6);
         if (this.field1.size() == 1) {
            this.field4 = var6;
         }

         this.field7 = this.field7 + var6.field2;
         if (var7 == null) {
            this.method2(var4);
            return null;
         }

         this.field7 = this.field7 - var7.field2;
         if (var7 == this.field4) {
            this.field4 = this.field1.values().iterator().next();
         }

         this.field3.accept((K)var1, var7.value);
         this.method2(var4);
         return var7.value;
      }
   }

   public V get(K var1) {
      synchronized (this.field1) {
         ThreadModuleDump24.Data var3 = this.field1.remove(var1);
         if (var3 == null) {
            return null;
         }

         this.field7 = this.field7 - var3.field2;
         this.field7 = this.field7 + (var3.field2 = this.field2.apply(var3.value));
         this.field1.put((K)var1, var3);
         if (this.field1.size() > 1 && var3 == this.field4) {
            this.field4 = this.field1.values().iterator().next();
         }

         var3.field1 = System.nanoTime();
         this.method2(var3.field1);
         return var3.value;
      }
   }

   public V remove(K var1) {
      synchronized (this.field1) {
         ThreadModuleDump24.Data var3 = this.field1.remove(var1);
         if (var3 == null) {
            return null;
         }

         this.field7 = this.field7 - var3.field2;
         this.field3.accept((K)var1, var3.value);
         if (!this.field1.isEmpty()) {
            this.field4 = this.field1.values().iterator().next();
         } else {
            this.field4 = null;
         }

         return var3.value;
      }
   }

   private void method2(long var1) {
      synchronized (this.field1) {
         if (!this.field1.isEmpty()) {
            if (var1 - this.field4.field1 >= this.field5) {
               long var4 = this.method3();
               boolean var6 = var1 - this.field4.field1 > this.field6 || var4 > this.field8;
               if (var6) {
                  this.method4(var1);
                  var4 = this.method3();
                  if (var4 > this.field8) {
                     this.method5(var1);
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

   private void method4(long var1) {
      LinkedList var3 = new LinkedList();

      for (Entry var5 : this.field1.entrySet()) {
         ThreadModuleDump24.Data var6 = (ThreadModuleDump24.Data)var5.getValue();
         if (var1 - var6.field1 <= this.field6) {
            break;
         }

         this.field7 = this.field7 - var6.field2;
         var3.add(var5.getKey());
      }

      for (Object var8 : var3) {
         ThreadModuleDump24.Data var9 = this.field1.remove(var8);
         this.field3.accept((K)var8, var9.value);
      }
   }

   private void method5(long var1) {
      LinkedList var3 = new LinkedList();

      for (Entry var5 : this.field1.entrySet()) {
         ThreadModuleDump24.Data var6 = (ThreadModuleDump24.Data)var5.getValue();
         if (var1 - var6.field1 <= this.field5) {
            break;
         }

         this.field7 = this.field7 - var6.field2;
         var3.add(var5.getKey());
         if (this.field7 <= this.field8) {
            break;
         }
      }

      for (Object var8 : var3) {
         ThreadModuleDump24.Data var9 = this.field1.remove(var8);
         this.field3.accept((K)var8, var9.value);
      }
   }

   public void method6(Consumer<V> var1) {
      synchronized (this.field1) {
         for (ThreadModuleDump24.Data var4 : this.field1.values()) {
            var1.accept(var4.value);
         }
      }
   }

   public void clear() {
      synchronized (this.field1) {
         for (Entry var3 : new ArrayList<>(this.field1.entrySet())) {
            this.field3.accept((K)var3.getKey(), ((ThreadModuleDump24.Data)var3.getValue()).value);
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

      public Data(long var1, long var3, V var5) {
         this.field1 = var1;
         this.field2 = var3;
         this.value = (V)var5;
      }
   }
}
