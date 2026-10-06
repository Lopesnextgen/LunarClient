package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;

class ImmutableIntArray$AsList extends AbstractList<Integer> implements Serializable, RandomAccess {
   private final SerializableImpl2_2 field1;

   private ImmutableIntArray$AsList(SerializableImpl2_2 serializableimpl2_21) {
      this.field1 = serializableimpl2_21;
   }

   @Override
   public int size() {
      return this.field1.length();
   }

   public Integer get(int index1) {
      return this.field1.get(index1);
   }

   @Override
   public boolean contains(Object obj1) {
      return this.indexOf(obj1) >= 0;
   }

   @Override
   public int indexOf(Object obj1) {
      return obj1 instanceof Integer ? this.field1.indexOf((Integer)obj1) : -1;
   }

   @Override
   public int lastIndexOf(Object obj1) {
      return obj1 instanceof Integer ? this.field1.lastIndexOf((Integer)obj1) : -1;
   }

   @Override
   public List<Integer> subList(int number1, int number2) {
      return this.field1.method15(number1, number2).asList();
   }

   @Override
   public Spliterator<Integer> spliterator() {
      return SerializableImpl2_2.method20(this.field1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof ImmutableIntArray$AsList) {
         ImmutableIntArray$AsList serializableimpl2$data26 = (ImmutableIntArray$AsList)obj1;
         return this.field1.equals(serializableimpl2$data26.field1);
      }

      if (!(obj1 instanceof List)) {
         return false;
      }

      List list2 = (List)obj1;
      if (this.size() != list2.size()) {
         return false;
      }

      int index3 = SerializableImpl2_2.method18(this.field1);

      for (Object obj5 : list2) {
         if (!(obj5 instanceof Integer) || SerializableImpl2_2.method17(this.field1)[index3++] != (Integer)obj5) {
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
