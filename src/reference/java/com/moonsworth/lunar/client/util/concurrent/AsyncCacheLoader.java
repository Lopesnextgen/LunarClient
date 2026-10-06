package com.moonsworth.lunar.client.util.concurrent;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AsyncCacheLoader<K, V> extends Thread {
   private static final Object field1 = new Object();
   private static final Object field2 = new Object();
   private static final Object field3 = new Object();
   private final Set<K> field4 = Sets.newConcurrentHashSet();
   private final Deque<AsyncCacheLoader.Data<K>> field5 = new ArrayDeque<>();
   private final Map<K, AsyncCacheLoader.Data<K>> field6 = new HashMap<>();
   @Nullable
   private final ExecutorService executorService;
   private final long field7;
   private final int field8;
   private final AsyncCacheLoader.Extension<K, V> field9;
   private final Cache<K, Object> field10;
   @Nullable
   private Cache<K, Object> field11;

   public AsyncCacheLoader(@Nullable ExecutorService executorservice1, long number2, int number4, AsyncCacheLoader.Extension<K, V> extension5, @NotNull Caffeine caffeine6) {
      this.setDaemon(true);
      this.executorService = executorservice1;
      this.field7 = number2;
      this.field8 = number4;
      this.field9 = extension5;
      this.field10 = caffeine6.build();
   }

   @Override
   public synchronized void start() {
      super.start();
      LunarLogger.method3("Starting fetch thread %s", new Object[]{this.getName()});
   }

   public AsyncCacheLoader<K, V> method1(int number1, TimeUnit timeunit2) {
      this.field11 = Caffeine.newBuilder().expireAfterWrite(number1, timeunit2).build();
      return this;
   }

   public V get(K value1) {
      if (value1 == null) {
         return null;
      }

      Object obj2 = this.field10.getIfPresent(value1);
      if (obj2 == field2) {
         return null;
      }

      if (obj2 != null && obj2 != field1) {
         return (V)obj2;
      }

      if (this.field11 != null) {
         this.field11.put(value1, field3);
      }

      this.field4.add((K)value1);
      return null;
   }

   public List<V> method2() {
      return this.field10.asMap().values().stream().filter(arg0 -> arg0 != field1 && arg0 != field2 && arg0 != null).map(arg0 -> (V)arg0).toList();
   }

   @Override
   public void run() {
      while (true) {
         boolean flag1 = false;

         try {
            synchronized (this) {
               this.method4();
               AsyncCacheLoader.Data data3 = this.field5.poll();
               if (data3 != null && (this.field11 == null || this.field11.getIfPresent(data3.field1) != null)) {
                  flag1 = true;
                  if (this.executorService == null) {
                     this.method3(data3);
                  } else {
                     this.executorService.execute(() -> this.method3(data3));
                  }
               }
            }
         } catch (Exception exception7) {
            LunarLogger.method9(exception7, "Fatal fetch thread exception %s", new Object[]{this.getName()});
         }

         try {
            long number2 = flag1 ? Math.max(1L, this.field7) : 10L;
            Thread.sleep(number2);
         } catch (InterruptedException interruptedexception5) {
            LunarLogger.method7("Fetch thread %s interrupt %s", new Object[]{this.getName(), interruptedexception5});
            return;
         }
      }
   }

   private void method3(AsyncCacheLoader.Data<K> data1) {
      Object obj2 = data1.field1;
      boolean flag3 = false;

      try {
         if (this.field10.getIfPresent(obj2) != field1) {
            return;
         }

         Object obj4 = this.field9.fetch((K)obj2);
         flag3 = true;
         synchronized (this) {
            if (this.field6.get(obj2) == data1) {
               this.field10.put(obj2, obj4 == null ? field2 : obj4);
            }
         }
      } catch (Throwable exception10) {
         exception10.printStackTrace();
         LunarLogger.method9(exception10, "Fetch thread task %s %s", new Object[]{this.getName(), obj2});
      }

      boolean flag11 = !flag3 && ++data1.field2 < this.field8;
      synchronized (this) {
         if (this.field6.get(obj2) == data1) {
            if (flag11) {
               this.field5.addFirst(data1);
            } else {
               this.field6.remove(obj2);
               if (!flag3 && this.field10.getIfPresent(obj2) == field1) {
                  this.field10.put(obj2, field2);
               }
            }
         }
      }
   }

   private void method4() {
      if (!this.field4.isEmpty()) {
         Iterator iterator1 = this.field4.iterator();

         while (iterator1.hasNext()) {
            Object obj2 = iterator1.next();
            iterator1.remove();
            Object obj3 = this.field10.getIfPresent(obj2);
            if (obj3 == null) {
               AsyncCacheLoader.Data data4 = new AsyncCacheLoader.Data<>(obj2);
               this.field5.add(data4);
               this.field6.put((K)obj2, data4);
               this.field10.put(obj2, field1);
            } else if (obj3 == field1) {
               AsyncCacheLoader.Data data5 = this.field6.get(obj2);
               if (data5 != null && this.field5.remove(data5)) {
                  this.field5.addFirst(data5);
               }
            }
         }
      }
   }

   public synchronized void invalidateAll() {
      this.method5();
      this.field10.invalidateAll();
      if (this.field11 != null) {
         this.field11.invalidateAll();
      }
   }

   public synchronized void method5() {
      if (!this.field6.isEmpty()) {
         LunarLogger.method3(
            "[" + this.getName() + "] Clearing tasks, keyToTask=%s taskQueue=%s keysToProcess=%s cache=%s]",
            new Object[]{this.field6.size(), this.field5.size(), this.field4.size(), this.field10.estimatedSize()}
         );
         Iterator iterator1 = this.field6.entrySet().iterator();

         while (iterator1.hasNext()) {
            Entry entry2 = (Entry)iterator1.next();
            iterator1.remove();
            Object obj3 = entry2.getKey();
            this.field4.remove(obj3);
            Object obj4 = this.field10.getIfPresent(obj3);
            if (obj4 == field1) {
               this.field10.invalidate(obj3);
            }

            if (this.field11 != null) {
               this.field11.invalidate(obj3);
            }

            AsyncCacheLoader.Data data5 = (AsyncCacheLoader.Data)entry2.getValue();
            this.field5.remove(data5);
         }
      }
   }

   public String getInfo() {
      Collection list1 = this.field10.asMap().values();
      return String.format(
         "keysToProcess=%d, taskQueue=%d, keyToTask=%d, executor=%b, waitTime=%d, retries=%d, cache=%d, expiryCache=%s, nullMarkers=%d, processingMarkers=%d",
         this.field4.size(),
         this.field5.size(),
         this.field6.size(),
         this.executorService != null,
         this.field7,
         this.field8,
         this.field10.estimatedSize(),
         this.field11 == null ? "N/A" : String.valueOf(this.field11.estimatedSize()),
         (int)list1.stream().filter(arg0 -> arg0 == field2).count(),
         (int)list1.stream().filter(arg0 -> arg0 == field1).count()
      );
   }

   private static class Data<K> {
      private final K field1;
      private volatile int field2 = 0;

      public Data(K value1) {
         this.field1 = (K)value1;
      }
   }

   @FunctionalInterface
   public interface Extension<K, V> {
      V fetch(K value1);
   }
}
