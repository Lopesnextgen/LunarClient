package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import com.google.common.eventbus.Subscribe;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableList;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterators;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.Maps;

final class MixinHelper_7 {
   private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<MixinHelper6_6>> field1 = Maps.newConcurrentMap();
   @Weak
   private final EventBus field2;
   private static final LoadingCache<Class<?>, ImmutableList<Method>> field3 = CacheBuilder.method1()
      .method15()
      .method34(new MixinHelper8_4<Class<?>, ImmutableList<Method>>() {
         public ImmutableList<Method> method1(Class<?> var1) {
            return MixinHelper_7.method3(var1);
         }
      });
   private static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> field4 = CacheBuilder.method1()
      .method15()
      .method34(new MixinHelper8_4<Class<?>, ImmutableSet<Class<?>>>() {
         public ImmutableSet<Class<?>> method1(Class<?> var1) {
            return ImmutableSet.method10(TypeToken.method1(var1).method13().rawTypes());
         }
      });

   MixinHelper_7(EventBus var1) {
      this.field2 = Preconditions.checkNotNull(var1);
   }

   void register(Object var1) {
      Multimap var2 = this.method1(var1);

      for (Entry var4 : var2.asMap().entrySet()) {
         Class var5 = (Class)var4.getKey();
         Collection var6 = (Collection)var4.getValue();
         CopyOnWriteArraySet var7 = this.field1.get(var5);
         if (var7 == null) {
            CopyOnWriteArraySet var8 = new CopyOnWriteArraySet();
            var7 = MoreObjects.firstNonNull(this.field1.putIfAbsent(var5, var8), var8);
         }

         var7.addAll(var6);
      }
   }

   void unregister(Object var1) {
      Multimap var2 = this.method1(var1);

      for (Entry var4 : var2.asMap().entrySet()) {
         Class var5 = (Class)var4.getKey();
         Collection var6 = (Collection)var4.getValue();
         CopyOnWriteArraySet var7 = this.field1.get(var5);
         if (var7 == null || !var7.removeAll(var6)) {
            throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + var1 + " registered?");
         }
      }
   }

   @Annotation4
   Set<MixinHelper6_6> getSubscribersForTesting(Class<?> var1) {
      return MoreObjects.firstNonNull(this.field1.get(var1), ImmutableSet.<MixinHelper6_6>method3());
   }

   Iterator<MixinHelper6_6> getSubscribers(Object var1) {
      ImmutableSet var2 = method4(var1.getClass());
      ArrayList var3 = Lists.newArrayListWithCapacity(var2.size());
      MixinHelperIterator3 var4 = var2.method1();

      while (var4.hasNext()) {
         Class var5 = (Class)var4.next();
         CopyOnWriteArraySet var6 = this.field1.get(var5);
         if (var6 != null) {
            var3.add(var6.iterator());
         }
      }

      return Iterators.concat(var3.iterator());
   }

   private Multimap<Class<?>, MixinHelper6_6> method1(Object var1) {
      MixinHelper1342232 var2 = MixinHelper1342232.method1();
      Class var3 = var1.getClass();
      MixinHelperIterator3 var4 = method2(var3).method1();

      while (var4.hasNext()) {
         Method var5 = (Method)var4.next();
         Class[] var6 = var5.getParameterTypes();
         Class var7 = var6[0];
         var2.put(var7, MixinHelper6_6.method1(this.field2, var1, var5));
      }

      return var2;
   }

   private static ImmutableList<Method> method2(Class<?> var0) {
      return field3.getUnchecked(var0);
   }

   private static ImmutableList<Method> method3(Class<?> var0) {
      Set var1 = TypeToken.method1(var0).method13().rawTypes();
      HashMap var2 = Maps.newHashMap();

      for (Class var4 : var1) {
         for (Method var8 : var4.getDeclaredMethods()) {
            if (var8.isAnnotationPresent(Annotation2_2.class) && !var8.isSynthetic()) {
               Class[] var9 = var8.getParameterTypes();
               Preconditions.checkArgument(
                  var9.length == 1,
                  "Method %s has @Subscribe annotation but has %s parameters.Subscriber methods must have exactly 1 parameter.",
                  var8,
                  var9.length
               );
               MixinHelper$Data52 var10 = new MixinHelper$Data52(var8);
               if (!var2.containsKey(var10)) {
                  var2.put(var10, var8);
               }
            }
         }
      }

      return ImmutableList.method15(var2.values());
   }

   @Annotation4
   static ImmutableSet<Class<?>> method4(Class<?> var0) {
      try {
         return field4.getUnchecked(var0);
      } catch (MixinHelperException_2 var2) {
         throw MixinHelper13_2.propagate(var2.getCause());
      }
   }
}
