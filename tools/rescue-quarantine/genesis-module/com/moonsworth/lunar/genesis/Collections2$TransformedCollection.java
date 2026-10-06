package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Collections2$TransformedCollection<F, T> extends AbstractCollection<T> {
   final Collection<F> field1;
   final Function<? super F, ? extends T> field2;

   Collections2$TransformedCollection(Collection<F> list1, Function<? super F, ? extends T> mixinhelper24_22) {
      this.field1 = Preconditions.checkNotNull(list1);
      this.field2 = Preconditions.checkNotNull(mixinhelper24_22);
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Override
   public Iterator<T> iterator() {
      return Iterators.method17(this.field1.iterator(), this.field2);
   }

   @Override
   public Spliterator<T> spliterator() {
      return MixinHelper3_5.map(this.field1.spliterator(), this.field2);
   }

   @Override
   public void forEach(Consumer<? super T> consumer1) {
      Preconditions.checkNotNull(consumer1);
      this.field1.forEach(arg2 -> consumer1.accept(this.field2.apply(arg2)));
   }

   @Override
   public boolean removeIf(Predicate<? super T> predicate1) {
      Preconditions.checkNotNull(predicate1);
      return this.field1.removeIf(arg2 -> predicate1.test(this.field2.apply(arg2)));
   }

   @Override
   public int size() {
      return this.field1.size();
   }
}
