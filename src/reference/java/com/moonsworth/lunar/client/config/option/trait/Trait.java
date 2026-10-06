package com.moonsworth.lunar.client.config.option.trait;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import org.jetbrains.annotations.NotNull;

public class Trait<T> {
   private final int field1;
   private final T field2;

   public Trait(int value, T t) {
      this.field1 = value;
      this.field2 = (T)t;
   }

   public void method1(MutableTraitHost mutableTraitHost) {
      mutableTraitHost.set(this.field1, this.field2);
   }

   @NotNull
   @Override
   public String toString() {
      return this.field1 + ">" + this.field2;
   }

   static Trait<?> method2(Entry<Object> entry) {
      return method3(entry.getIntKey(), entry.getValue());
   }

   public static <T> Trait<T> method3(int value, Object object) {
      return new Trait<>(value, (T)object);
   }

   public int type() {
      return this.field1;
   }

   public T value() {
      return this.field2;
   }
}
