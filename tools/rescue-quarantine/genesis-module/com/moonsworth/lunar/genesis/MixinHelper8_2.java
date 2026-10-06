package com.moonsworth.lunar.genesis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.collect.Maps;
import com.google.common.base.Strings;

@GwtCompatible(emulated = true)
final class MixinHelper8_2 {
   private static final Logger field1 = Logger.getLogger(MixinHelper8_2.class.getName());

   static <K, V> Map<K, V> newHashMapWithExpectedSize(int var0) {
      return Maps.newHashMapWithExpectedSize(var0);
   }

   static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int var0) {
      return Maps.newLinkedHashMapWithExpectedSize(var0);
   }

   static <E> Set<E> newHashSetWithExpectedSize(int var0) {
      return Sets.newHashSetWithExpectedSize(var0);
   }

   static <E> Set<E> newLinkedHashSetWithExpectedSize(int var0) {
      return Sets.newLinkedHashSetWithExpectedSize(var0);
   }

   static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
      return Maps.newLinkedHashMap();
   }

   static <E> Set<E> preservesInsertionOrderOnAddsSet() {
      return Sets.newLinkedHashSet();
   }

   static <T> T[] newArray(T[] var0, int var1) {
      Class var2 = var0.getClass().getComponentType();
      return (T[])((Object[])Array.newInstance(var2, var1));
   }

   static <T> T[] copy(Object[] var0, int var1, int var2, T[] var3) {
      return (T[])Arrays.copyOfRange(var0, var1, var2, (Class<? extends T[]>)var3.getClass());
   }

   static MixinHelper26 method1(MixinHelper26 var0) {
      return var0.method5();
   }

   static int reduceIterationsIfGwt(int var0) {
      return var0;
   }

   static int reduceExponentIfGwt(int var0) {
      return var0;
   }

   static void checkGwtRpcEnabled() {
      String var0 = "guava.gwt.emergency_reenable_rpc";
      if (!Boolean.parseBoolean(System.getProperty(var0, "false"))) {
         throw new UnsupportedOperationException(
            Strings.lenientFormat(
               "We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.",
               var0,
               "https://stackoverflow.com/q/5189914/28465",
               "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"
            )
         );
      }

      field1.log(
         Level.WARNING,
         "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.",
         new Throwable()
      );
   }

   private MixinHelper8_2() {
   }
}
