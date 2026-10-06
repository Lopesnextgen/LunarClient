package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;

final class MixinHelper4$Data15<T> extends FluentIterable<T> {
   private final Iterable<? extends T> field2;

   private MixinHelper4$Data15(Iterable<? extends T> var1) {
      this.field2 = var1;
   }

   @Override
   public Iterator<T> iterator() {
      return Iterators.method3(this.field2.iterator());
   }

   @Override
   public void forEach(Consumer<? super T> var1) {
      this.field2.forEach(var1);
   }

   @Override
   public Spliterator<T> spliterator() {
      return (Spliterator<T>)this.field2.spliterator();
   }

   @Override
   public String toString() {
      return this.field2.toString();
   }
}
