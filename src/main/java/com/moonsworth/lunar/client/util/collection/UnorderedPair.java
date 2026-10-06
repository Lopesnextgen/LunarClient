package com.moonsworth.lunar.client.util.collection;

import java.util.Objects;
import lombok.Generated;

public class UnorderedPair<T> {
   private final T field1;
   private final T field2;

   @Override
   public boolean equals(Object object) {
      return !(object instanceof UnorderedPair threadmoduledump52)
         ? false
         : this.field1.equals(threadmoduledump52.field1) && this.field2.equals(threadmoduledump52.field2) || this.field1.equals(threadmoduledump52.field2) && this.field2.equals(threadmoduledump52.field1);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.field2);
   }

   @Generated
   public T method1() {
      return this.field1;
   }

   @Generated
   public T method2() {
      return this.field2;
   }

   @Generated
   public UnorderedPair(T t, T t2) {
      this.field1 = (T)t;
      this.field2 = (T)t2;
   }
}
