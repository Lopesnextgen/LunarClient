package com.moonsworth.lunar.genesis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.collect.Sets;
import com.google.common.base.Strings;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;

@GwtCompatible(emulated = true)
final class CollectPlatform {
   private static final Logger field1 = Logger.getLogger(CollectPlatform.class.getName());

   static <K, V> Map<K, V> newHashMapWithExpectedSize(int number0) {
      return Maps.newHashMapWithExpectedSize(number0);
   }

   static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int number0) {
      return Maps.newLinkedHashMapWithExpectedSize(number0);
   }

   static <E> Set<E> newHashSetWithExpectedSize(int number0) {
      return Sets.newHashSetWithExpectedSize(number0);
   }

   static <E> Set<E> newLinkedHashSetWithExpectedSize(int number0) {
      return Sets.newLinkedHashSetWithExpectedSize(number0);
   }

   static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
      return Maps.newLinkedHashMap();
   }

   static <E> Set<E> preservesInsertionOrderOnAddsSet() {
      return Sets.newLinkedHashSet();
   }

   static <T> T[] newArray(T[] items0, int number1) {
      Class clazz2 = items0.getClass().getComponentType();
      return (T[])((Object[])Array.newInstance(clazz2, number1));
   }

   static <T> T[] copy(Object[] items0, int number1, int number2, T[] items3) {
      return (T[])Arrays.copyOfRange(items0, number1, number2, (Class<? extends T[]>)items3.getClass());
   }

   static MixinHelper26 method1(MixinHelper26 mixinhelper260) {
      return mixinhelper260.method5();
   }

   static int reduceIterationsIfGwt(int number0) {
      return number0;
   }

   static int reduceExponentIfGwt(int number0) {
      return number0;
   }

   static void checkGwtRpcEnabled() {
      String text0 = "guava.gwt.emergency_reenable_rpc";
      if (!Boolean.parseBoolean(System.getProperty(text0, "false"))) {
         throw new UnsupportedOperationException(
            Strings.lenientFormat(
               "We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.",
               new Object[]{text0, "https://stackoverflow.com/q/5189914/28465", "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"}
            )
         );
      }

      field1.log(
         Level.WARNING,
         "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.",
         new Throwable()
      );
   }

   private CollectPlatform() {
   }
}
