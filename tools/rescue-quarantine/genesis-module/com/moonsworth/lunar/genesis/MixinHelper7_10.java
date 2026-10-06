package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

@Annotation3
final class MixinHelper7_10 {
   private MixinHelper7_10() {
   }

   static int readCount(ObjectInputStream var0) {
      return var0.readInt();
   }

   static <K, V> void writeMap(Map<K, V> var0, ObjectOutputStream var1) {
      var1.writeInt(var0.size());

      for (Entry var3 : var0.entrySet()) {
         var1.writeObject(var3.getKey());
         var1.writeObject(var3.getValue());
      }
   }

   static <K, V> void populateMap(Map<K, V> var0, ObjectInputStream var1) {
      int var2 = var1.readInt();
      populateMap(var0, var1, var2);
   }

   static <K, V> void populateMap(Map<K, V> var0, ObjectInputStream var1, int var2) {
      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = var1.readObject();
         Object var5 = var1.readObject();
         var0.put(var4, var5);
      }
   }

   static <E> void method1(Multiset<E> var0, ObjectOutputStream var1) {
      int var2 = var0.entrySet().size();
      var1.writeInt(var2);

      for (Multiset.Extension var4 : var0.entrySet()) {
         var1.writeObject(var4.getElement());
         var1.writeInt(var4.getCount());
      }
   }

   static <E> void method2(Multiset<E> var0, ObjectInputStream var1) {
      int var2 = var1.readInt();
      method3(var0, var1, var2);
   }

   static <E> void method3(Multiset<E> var0, ObjectInputStream var1, int var2) {
      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = var1.readObject();
         int var5 = var1.readInt();
         var0.add(var4, var5);
      }
   }

   static <K, V> void method4(Multimap<K, V> var0, ObjectOutputStream var1) {
      var1.writeInt(var0.asMap().size());

      for (Entry var3 : var0.asMap().entrySet()) {
         var1.writeObject(var3.getKey());
         var1.writeInt(((Collection)var3.getValue()).size());

         for (Object var5 : (Collection)var3.getValue()) {
            var1.writeObject(var5);
         }
      }
   }

   static <K, V> void method5(Multimap<K, V> var0, ObjectInputStream var1) {
      int var2 = var1.readInt();
      method6(var0, var1, var2);
   }

   static <K, V> void method6(Multimap<K, V> var0, ObjectInputStream var1, int var2) {
      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = var1.readObject();
         Collection var5 = var0.get(var4);
         int var6 = var1.readInt();

         for (int var7 = 0; var7 < var6; var7++) {
            Object var8 = var1.readObject();
            var5.add(var8);
         }
      }
   }

   static <T> MixinHelper7$Data19<T> method7(Class<T> var0, String var1) {
      try {
         Field var2 = var0.getDeclaredField(var1);
         return new MixinHelper7$Data19<>(var2);
      } catch (NoSuchFieldException var3) {
         throw new AssertionError(var3);
      }
   }
}
