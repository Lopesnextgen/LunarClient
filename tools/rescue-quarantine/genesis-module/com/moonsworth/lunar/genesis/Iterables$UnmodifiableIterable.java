package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;

final class Iterables$UnmodifiableIterable<T> extends FluentIterable<T> {
   private final Iterable<? extends T> field2;

   private Iterables$UnmodifiableIterable(Iterable<? extends T> list1) {
      this.field2 = list1;
   }

   public Iterator<T> iterator() {
      return Iterators.method3(this.field2.iterator());
   }

   public void forEach(Consumer<? super T> consumer1) {
      this.field2.forEach(consumer1);
   }

   public Spliterator<T> spliterator() {
      return (Spliterator<T>)this.field2.spliterator();
   }

   public String toString() {
      return this.field2.toString();
   }
}
