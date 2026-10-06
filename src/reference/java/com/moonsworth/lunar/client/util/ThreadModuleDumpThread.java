package com.moonsworth.lunar.client.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.util.Slayer;
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

public class ThreadModuleDumpThread<K, V> extends Thread {
   private static final Object field1 = new Object();
   private static final Object field2 = new Object();
   private static final Object field3 = new Object();
   private final Set<K> field4 = Sets.newConcurrentHashSet();
   private final Deque<ThreadModuleDumpThread.Data<K>> field5 = new ArrayDeque<>();
   private final Map<K, ThreadModuleDumpThread.Data<K>> field6 = new HashMap<>();
   @Nullable
   private final ExecutorService executorService;
   private final long field7;
   private final int field8;
   private final ThreadModuleDumpThread.Extension<K, V> field9;
   private final Cache<K, Object> field10;
   @Nullable
   private Cache<K, Object> field11;

   public ThreadModuleDumpThread(@Nullable ExecutorService var1, long var2, int var4, ThreadModuleDumpThread.Extension<K, V> var5, @NotNull Caffeine var6) {
      this.setDaemon(true);
      this.executorService = var1;
      this.field7 = var2;
      this.field8 = var4;
      this.field9 = var5;
      this.field10 = var6.build();
   }

   @Override
   public synchronized void start() {
      super.start();
      Slayer.method3("Starting fetch thread %s", new Object[]{this.getName()});
   }

   public ThreadModuleDumpThread<K, V> method1(int var1, TimeUnit var2) {
      this.field11 = Caffeine.newBuilder().expireAfterWrite(var1, var2).build();
      return this;
   }

   public V get(K var1) {
      if (var1 == null) {
         return null;
      }

      Object var2 = this.field10.getIfPresent(var1);
      if (var2 == field2) {
         return null;
      }

      if (var2 != null && var2 != field1) {
         return (V)var2;
      }

      if (this.field11 != null) {
         this.field11.put(var1, field3);
      }

      this.field4.add((K)var1);
      return null;
   }

   public List<V> method2() {
      return this.field10.asMap().values().stream().filter(var0 -> var0 != field1 && var0 != field2 && var0 != null).map(var0 -> (V)var0).toList();
   }

   @Override
   public void run() {
      while (true) {
         boolean var1 = false;

         try {
            synchronized (this) {
               this.method4();
               ThreadModuleDumpThread.Data var3 = this.field5.poll();
               if (var3 != null && (this.field11 == null || this.field11.getIfPresent(var3.field1) != null)) {
                  var1 = true;
                  if (this.executorService == null) {
                     this.method3(var3);
                  } else {
                     this.executorService.execute(() -> this.method3(var3));
                  }
               }
            }
         } catch (Exception var7) {
            Slayer.method9(var7, "Fatal fetch thread exception %s", new Object[]{this.getName()});
         }

         try {
            long var2 = var1 ? Math.max(1L, this.field7) : 10L;
            Thread.sleep(var2);
         } catch (InterruptedException var5) {
            Slayer.method7("Fetch thread %s interrupt %s", new Object[]{this.getName(), var5});
            return;
         }
      }
   }

   private void method3(ThreadModuleDumpThread.Data<K> var1) {
      Object var2 = var1.field1;
      boolean var3 = false;

      try {
         if (this.field10.getIfPresent(var2) != field1) {
            return;
         }

         Object var4 = this.field9.fetch((K)var2);
         var3 = true;
         synchronized (this) {
            if (this.field6.get(var2) == var1) {
               this.field10.put(var2, var4 == null ? field2 : var4);
            }
         }
      } catch (Throwable var10) {
         var10.printStackTrace();
         Slayer.method9(var10, "Fetch thread task %s %s", new Object[]{this.getName(), var2});
      }

      boolean var11 = !var3 && ++var1.field2 < this.field8;
      synchronized (this) {
         if (this.field6.get(var2) == var1) {
            if (var11) {
               this.field5.addFirst(var1);
            } else {
               this.field6.remove(var2);
               if (!var3 && this.field10.getIfPresent(var2) == field1) {
                  this.field10.put(var2, field2);
               }
            }
         }
      }
   }

   private void method4() {
      if (!this.field4.isEmpty()) {
         Iterator var1 = this.field4.iterator();

         while (var1.hasNext()) {
            Object var2 = var1.next();
            var1.remove();
            Object var3 = this.field10.getIfPresent(var2);
            if (var3 == null) {
               ThreadModuleDumpThread.Data var4 = new ThreadModuleDumpThread.Data<>(var2);
               this.field5.add(var4);
               this.field6.put((K)var2, var4);
               this.field10.put(var2, field1);
            } else if (var3 == field1) {
               ThreadModuleDumpThread.Data var5 = this.field6.get(var2);
               if (var5 != null && this.field5.remove(var5)) {
                  this.field5.addFirst(var5);
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
         Slayer.method3(
            "[" + this.getName() + "] Clearing tasks, keyToTask=%s taskQueue=%s keysToProcess=%s cache=%s]",
            new Object[]{this.field6.size(), this.field5.size(), this.field4.size(), this.field10.estimatedSize()}
         );
         Iterator var1 = this.field6.entrySet().iterator();

         while (var1.hasNext()) {
            Entry var2 = (Entry)var1.next();
            var1.remove();
            Object var3 = var2.getKey();
            this.field4.remove(var3);
            Object var4 = this.field10.getIfPresent(var3);
            if (var4 == field1) {
               this.field10.invalidate(var3);
            }

            if (this.field11 != null) {
               this.field11.invalidate(var3);
            }

            ThreadModuleDumpThread.Data var5 = (ThreadModuleDumpThread.Data)var2.getValue();
            this.field5.remove(var5);
         }
      }
   }

   public String getInfo() {
      Collection var1 = this.field10.asMap().values();
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
         (int)var1.stream().filter(var0 -> var0 == field2).count(),
         (int)var1.stream().filter(var0 -> var0 == field1).count()
      );
   }

   private static class Data<K> {
      private final K field1;
      private volatile int field2 = 0;

      public Data(K var1) {
         this.field1 = (K)var1;
      }
   }

   @FunctionalInterface
   public interface Extension<K, V> {
      V fetch(K var1);
   }
}
