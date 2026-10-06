package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import com.google.common.collect.Ordering;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.Monitor$Guard;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.ServiceManager;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.base.Stopwatch;
import com.google.common.base.Predicates;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableCollection;

final class MixinHelper122$Data7 {
   final Monitor field1 = new Monitor();
   @GuardedBy("monitor")
   final MixinHelper132_2<MixinHelper23$Type, Service> field2 = MixinHelper43.method7(MixinHelper23$Type.class).method6().method2();
   @GuardedBy("monitor")
   final Multiset<MixinHelper23$Type> field3 = this.field2.method2();
   @GuardedBy("monitor")
   final Map<Service, Stopwatch> field4 = Maps.newIdentityHashMap();
   @GuardedBy("monitor")
   boolean ready;
   @GuardedBy("monitor")
   boolean transitioned;
   final int field5;
   final Monitor$Guard field6 = new MixinHelper122$Data7.Data2();
   final Monitor$Guard field7 = new MixinHelper122$Data7.Data();
   final MixinHelper5_9<MixinHelper122$Data5> field8 = new MixinHelper5_9<>();

   MixinHelper122$Data7(ImmutableCollection<Service> var1) {
      this.field5 = var1.size();
      this.field2.putAll(MixinHelper23$Type.NEW, var1);
   }

   void method1(Service var1) {
      this.field1.enter();

      try {
         Stopwatch var2 = this.field4.get(var1);
         if (var2 == null) {
            this.field4.put(var1, Stopwatch.method3());
         }
      } finally {
         this.field1.leave();
      }
   }

   void markReady() {
      this.field1.enter();

      try {
         if (this.transitioned) {
            ArrayList var1 = Lists.newArrayList();
            MixinHelperIterator3 var2 = this.method3().method17().method1();

            while (var2.hasNext()) {
               Service var3 = (Service)var2.next();
               if (var3.method2() != MixinHelper23$Type.NEW) {
                  var1.add(var3);
               }
            }

            throw new IllegalArgumentException("Services started transitioning asynchronously before the ServiceManager was constructed: " + var1);
         }

         this.ready = true;
      } finally {
         this.field1.leave();
      }
   }

   void method2(MixinHelper122$Data5 var1, Executor var2) {
      this.field8.addListener(var1, var2);
   }

   void awaitHealthy() {
      this.field1.method5(this.field6);

      try {
         this.checkHealthy();
      } finally {
         this.field1.leave();
      }
   }

   void awaitHealthy(long var1, TimeUnit var3) {
      this.field1.enter();

      try {
         if (!this.field1.method20(this.field6, var1, var3)) {
            throw new TimeoutException(
               "Timeout waiting for the services to become healthy. The following services have not started: "
                  + MixinHelper37.<MixinHelper23$Type, Service>method29(
                     this.field2, Predicates.method15(ImmutableSet.method3(MixinHelper23$Type.NEW, MixinHelper23$Type.STARTING))
                  )
            );
         }

         this.checkHealthy();
      } finally {
         this.field1.leave();
      }
   }

   void awaitStopped() {
      this.field1.method5(this.field7);
      this.field1.leave();
   }

   void awaitStopped(long var1, TimeUnit var3) {
      this.field1.enter();

      try {
         if (!this.field1.method20(this.field7, var1, var3)) {
            throw new TimeoutException(
               "Timeout waiting for the services to stop. The following services have not stopped: "
                  + MixinHelper37.<MixinHelper23$Type, Service>method29(
                     this.field2, Predicates.method5(Predicates.method15(EnumSet.of(MixinHelper23$Type.TERMINATED, MixinHelper23$Type.FAILED)))
                  )
            );
         }
      } finally {
         this.field1.leave();
      }
   }

   ImmutableSetMultimap<MixinHelper23$Type, Service> method3() {
      ImmutableSetMultimap.Data2 var1 = ImmutableSetMultimap.method8();
      this.field1.enter();

      try {
         for (Entry var3 : this.field2.entries()) {
            if (!(var3.getValue() instanceof MixinHelper122$Data6)) {
               var1.method3(var3);
            }
         }
      } finally {
         this.field1.leave();
      }

      return var1.method11();
   }

   ImmutableMap<Service, Long> method4() {
      this.field1.enter();

      ArrayList var1;
      try {
         var1 = Lists.newArrayListWithCapacity(this.field4.size());

         for (Entry var3 : this.field4.entrySet()) {
            Service var4 = (Service)var3.getKey();
            Stopwatch var5 = (Stopwatch)var3.getValue();
            if (!var5.isRunning() && !(var4 instanceof MixinHelper122$Data6)) {
               var1.add(Maps.immutableEntry(var4, var5.elapsed(TimeUnit.MILLISECONDS)));
            }
         }
      } finally {
         this.field1.leave();
      }

      Collections.sort(var1, Ordering.<Long>method1().method12(new MixinHelper24_2<Entry<Service, Long>, Long>() {
         public Long apply(Entry<Service, Long> var1) {
            return (Long)var1.getValue();
         }
      }));
      return ImmutableMap.method10(var1);
   }

   void method5(Service var1, MixinHelper23$Type var2, MixinHelper23$Type var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(var2 != var3);
      this.field1.enter();

      try {
         this.transitioned = true;
         if (!this.ready) {
            return;
         }

         Preconditions.checkState(this.field2.remove(var2, var1), "Service %s not at the expected location in the state map %s", var1, var2);
         Preconditions.checkState(this.field2.put(var3, var1), "Service %s in the state map unexpectedly at %s", var1, var3);
         Stopwatch var4 = this.field4.get(var1);
         if (var4 == null) {
            var4 = Stopwatch.method3();
            this.field4.put(var1, var4);
         }

         if (var3.compareTo(MixinHelper23$Type.RUNNING) >= 0 && var4.isRunning()) {
            var4.method6();
            if (!(var1 instanceof MixinHelper122$Data6)) {
               ServiceManager.access$200().log(Level.FINE, "Started {0} in {1}.", new Object[]{var1, var4});
            }
         }

         if (var3 == MixinHelper23$Type.FAILED) {
            this.method6(var1);
         }

         if (this.field3.count(MixinHelper23$Type.RUNNING) == this.field5) {
            this.enqueueHealthyEvent();
         } else if (this.field3.count(MixinHelper23$Type.TERMINATED) + this.field3.count(MixinHelper23$Type.FAILED) == this.field5) {
            this.enqueueStoppedEvent();
         }
      } finally {
         this.field1.leave();
         this.dispatchListenerEvents();
      }
   }

   void enqueueStoppedEvent() {
      this.field8.method1(ServiceManager.method8());
   }

   void enqueueHealthyEvent() {
      this.field8.method1(ServiceManager.method9());
   }

   void method6(final Service var1) {
      this.field8.method1(new MixinHelper5$Extension<MixinHelper122$Data5>() {
         public void method1(MixinHelper122$Data5 var1x) {
            var1x.method1(var1);
         }

         @Override
         public String toString() {
            return "failed({service=" + var1 + "})";
         }
      });
   }

   void dispatchListenerEvents() {
      Preconditions.checkState(!this.field1.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
      this.field8.dispatch();
   }

   @GuardedBy("monitor")
   void checkHealthy() {
      if (this.field3.count(MixinHelper23$Type.RUNNING) != this.field5) {
         IllegalStateException var1 = new IllegalStateException(
            "Expected to be healthy after starting. The following services are not running: "
               + MixinHelper37.<MixinHelper23$Type, Service>method29(
                  this.field2, Predicates.method5(Predicates.method12(MixinHelper23$Type.RUNNING))
               )
         );

         for (Service var3 : this.field2.get(MixinHelper23$Type.FAILED)) {
            var1.addSuppressed(new MixinHelper122$Data3(var3));
         }

         throw var1;
      }
   }

   final class Data extends Monitor$Guard {
      Data() {
         super(MixinHelper122$Data7.this.field1);
      }

      @GuardedBy("ServiceManagerState.this.monitor")
      @Override
      public boolean isSatisfied() {
         return MixinHelper122$Data7.this.field3.count(MixinHelper23$Type.TERMINATED) + MixinHelper122$Data7.this.field3.count(MixinHelper23$Type.FAILED)
            == MixinHelper122$Data7.this.field5;
      }
   }

   final class Data2 extends Monitor$Guard {
      Data2() {
         super(MixinHelper122$Data7.this.field1);
      }

      @GuardedBy("ServiceManagerState.this.monitor")
      @Override
      public boolean isSatisfied() {
         return MixinHelper122$Data7.this.field3.count(MixinHelper23$Type.RUNNING) == MixinHelper122$Data7.this.field5
            || MixinHelper122$Data7.this.field3.contains(MixinHelper23$Type.STOPPING)
            || MixinHelper122$Data7.this.field3.contains(MixinHelper23$Type.TERMINATED)
            || MixinHelper122$Data7.this.field3.contains(MixinHelper23$Type.FAILED);
      }
   }
}
