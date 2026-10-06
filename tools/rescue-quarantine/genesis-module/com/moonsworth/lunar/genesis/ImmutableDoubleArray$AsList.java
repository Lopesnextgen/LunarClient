package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;

class ImmutableDoubleArray$AsList extends AbstractList<Double> implements Serializable, RandomAccess {
   private final SerializableImpl3 field1;

   private ImmutableDoubleArray$AsList(SerializableImpl3 serializableimpl31) {
      this.field1 = serializableimpl31;
   }

   @Override
   public int size() {
      return this.field1.length();
   }

   public Double get(int index1) {
      return this.field1.get(index1);
   }

   @Override
   public boolean contains(Object obj1) {
      return this.indexOf(obj1) >= 0;
   }

   @Override
   public int indexOf(Object obj1) {
      return obj1 instanceof Double ? this.field1.indexOf((Double)obj1) : -1;
   }

   @Override
   public int lastIndexOf(Object obj1) {
      return obj1 instanceof Double ? this.field1.lastIndexOf((Double)obj1) : -1;
   }

   @Override
   public List<Double> subList(int number1, int number2) {
      return this.field1.method15(number1, number2).asList();
   }

   @Override
   public Spliterator<Double> spliterator() {
      return SerializableImpl3.method20(this.field1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof ImmutableDoubleArray$AsList) {
         ImmutableDoubleArray$AsList serializableimpl3$data36 = (ImmutableDoubleArray$AsList)obj1;
         return this.field1.equals(serializableimpl3$data36.field1);
      }

      if (!(obj1 instanceof List)) {
         return false;
      }

      List list2 = (List)obj1;
      if (this.size() != list2.size()) {
         return false;
      }

      int index3 = SerializableImpl3.method18(this.field1);

      for (Object obj5 : list2) {
         if (!(obj5 instanceof Double) || !SerializableImpl3.access$600(SerializableImpl3.method17(this.field1)[index3++], (Double)obj5)) {
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
