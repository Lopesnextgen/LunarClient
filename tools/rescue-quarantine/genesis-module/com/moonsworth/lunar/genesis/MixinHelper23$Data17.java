package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;

abstract class MixinHelper23$Data17<K> {
   static final MixinHelper23$Data17<TypeToken<?>> field1 = new MixinHelper23$Data17<TypeToken<?>>() {
      Class<?> method1(TypeToken<?> var1) {
         return var1.method3();
      }

      Iterable<? extends TypeToken<?>> method2(TypeToken<?> var1) {
         return var1.method11();
      }

      @Nullable TypeToken<?> method3(TypeToken<?> var1) {
         return var1.method9();
      }
   };
   static final MixinHelper23$Data17<Class<?>> field2 = new MixinHelper23$Data17<Class<?>>() {
      Class<?> getRawType(Class<?> var1) {
         return var1;
      }

      Iterable<? extends Class<?>> getInterfaces(Class<?> var1) {
         return Arrays.asList(var1.getInterfaces());
      }

      @Nullable Class<?> getSuperclass(Class<?> var1) {
         return var1.getSuperclass();
      }
   };

   private MixinHelper23$Data17() {
   }

   final MixinHelper23$Data17<K> method1() {
      return new MixinHelper23$Data17.Data<K>(this) {
         @Override
         Iterable<? extends K> getInterfaces(K var1) {
            return ImmutableSet.method3();
         }

         @Override
         ImmutableList<K> method3(Iterable<? extends K> var1) {
            ImmutableList.Data2 var2 = ImmutableList.method30();

            for (Object var4 : var1) {
               if (!this.getRawType((K)var4).isInterface()) {
                  var2.method2(var4);
               }
            }

            return super.method3(var2.method6());
         }
      };
   }

   final ImmutableList<K> method2(K var1) {
      return this.method3(ImmutableList.method2((K)var1));
   }

   ImmutableList<K> method3(Iterable<? extends K> var1) {
      HashMap var2 = Maps.newHashMap();

      for (Object var4 : var1) {
         this.collectTypes((K)var4, var2);
      }

      return method4(var2, Ordering.method1().method9());
   }

   @CanIgnoreReturnValue
   private int collectTypes(K var1, Map<? super K, Integer> var2) {
      Integer var3 = (Integer)var2.get(var1);
      if (var3 != null) {
         return var3;
      }

      int var4 = this.getRawType((K)var1).isInterface() ? 1 : 0;

      for (Object var6 : this.getInterfaces((K)var1)) {
         var4 = Math.max(var4, this.collectTypes((K)var6, var2));
      }

      Object var7 = this.getSuperclass((K)var1);
      if (var7 != null) {
         var4 = Math.max(var4, this.collectTypes((K)var7, var2));
      }

      var2.put(var1, var4 + 1);
      return var4 + 1;
   }

   private static <K, V> ImmutableList<K> method4(final Map<K, V> var0, final java.util.Comparator<? super V> var1) {
      Ordering var2 = new Ordering<K>() {
         @Override
         public int compare(K var1x, K var2x) {
            return var1.compare(var0.get(var1x), var0.get(var2x));
         }
      };
      return var2.method17(var0.keySet());
   }

   abstract Class<?> getRawType(K var1);

   abstract Iterable<? extends K> getInterfaces(K var1);

   abstract @Nullable K getSuperclass(K var1);

   private static class Data<K> extends MixinHelper23$Data17<K> {
      private final MixinHelper23$Data17<K> field3;

      Data(MixinHelper23$Data17<K> var1) {
         this.field3 = var1;
      }

      @Override
      Class<?> getRawType(K var1) {
         return this.field3.getRawType((K)var1);
      }

      @Override
      Iterable<? extends K> getInterfaces(K var1) {
         return this.field3.getInterfaces((K)var1);
      }

      @Override
      K getSuperclass(K var1) {
         return this.field3.getSuperclass((K)var1);
      }
   }
}
