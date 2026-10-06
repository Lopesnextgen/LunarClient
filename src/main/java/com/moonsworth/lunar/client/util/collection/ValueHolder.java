package com.moonsworth.lunar.client.util.collection;

import lombok.Generated;

public class ValueHolder<T> {
   private T value;

   public void set(T value1) {
      this.value = (T)value1;
   }

   public T get() {
      return this.value;
   }

   @Generated
   public ValueHolder(T value1) {
      this.value = (T)value1;
   }

   @Generated
   public ValueHolder() {
   }
}
