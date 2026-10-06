package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;

class SerializableImpl2$Data2 extends AbstractList<Integer> implements Serializable, RandomAccess {
   private final SerializableImpl2_2 field1;

   private SerializableImpl2$Data2(SerializableImpl2_2 var1) {
      this.field1 = var1;
   }

   @Override
   public int size() {
      return this.field1.length();
   }

   public Integer get(int var1) {
      return this.field1.get(var1);
   }

   @Override
   public boolean contains(Object var1) {
      return this.indexOf(var1) >= 0;
   }

   @Override
   public int indexOf(Object var1) {
      return var1 instanceof Integer ? this.field1.indexOf((Integer)var1) : -1;
   }

   @Override
   public int lastIndexOf(Object var1) {
      return var1 instanceof Integer ? this.field1.lastIndexOf((Integer)var1) : -1;
   }

   @Override
   public List<Integer> subList(int var1, int var2) {
      return this.field1.method15(var1, var2).asList();
   }

   @Override
   public Spliterator<Integer> spliterator() {
      return SerializableImpl2_2.method20(this.field1);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof SerializableImpl2$Data2) {
         SerializableImpl2$Data2 var6 = (SerializableImpl2$Data2)var1;
         return this.field1.equals(var6.field1);
      }

      if (!(var1 instanceof List)) {
         return false;
      }

      List var2 = (List)var1;
      if (this.size() != var2.size()) {
         return false;
      }

      int var3 = SerializableImpl2_2.method18(this.field1);

      for (Object var5 : var2) {
         if (!(var5 instanceof Integer) || SerializableImpl2_2.method17(this.field1)[var3++] != (Integer)var5) {
            return false;
         }
      }

      return true;
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public String toString() {
      return this.field1.toString();
   }
}
