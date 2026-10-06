package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;

class ImmutableLongArray$AsList extends AbstractList<Long> implements Serializable, RandomAccess {
   private final SerializableImpl_3 field1;

   private ImmutableLongArray$AsList(SerializableImpl_3 serializableimpl_31) {
      this.field1 = serializableimpl_31;
   }

   @Override
   public int size() {
      return this.field1.length();
   }

   public Long get(int index1) {
      return this.field1.get(index1);
   }

   @Override
   public boolean contains(Object obj1) {
      return this.indexOf(obj1) >= 0;
   }

   @Override
   public int indexOf(Object obj1) {
      return obj1 instanceof Long ? this.field1.indexOf((Long)obj1) : -1;
   }

   @Override
   public int lastIndexOf(Object obj1) {
      return obj1 instanceof Long ? this.field1.lastIndexOf((Long)obj1) : -1;
   }

   @Override
   public List<Long> subList(int number1, int number2) {
      return this.field1.method15(number1, number2).asList();
   }

   @Override
   public Spliterator<Long> spliterator() {
      return SerializableImpl_3.method20(this.field1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof ImmutableLongArray$AsList) {
         ImmutableLongArray$AsList serializableimpl$data136 = (ImmutableLongArray$AsList)obj1;
         return this.field1.equals(serializableimpl$data136.field1);
      }

      if (!(obj1 instanceof List)) {
         return false;
      }

      List list2 = (List)obj1;
      if (this.size() != list2.size()) {
         return false;
      }

      int index3 = SerializableImpl_3.method18(this.field1);

      for (Object obj5 : list2) {
         if (!(obj5 instanceof Long) || SerializableImpl_3.method17(this.field1)[index3++] != (Long)obj5) {
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
