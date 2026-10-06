package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Lists$TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
   final List<F> field1;
   final Function<? super F, ? extends T> field2;
   private static final long field3 = 0L;

   Lists$TransformingSequentialList(List<F> list1, Function<? super F, ? extends T> mixinhelper24_22) {
      this.field1 = Preconditions.checkNotNull(list1);
      this.field2 = Preconditions.checkNotNull(mixinhelper24_22);
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public ListIterator<T> listIterator(int number1) {
      return new Data9$1(this, this.field1.listIterator(number1));
   }

   @Override
   public boolean removeIf(Predicate<? super T> predicate1) {
      Preconditions.checkNotNull(predicate1);
      return this.field1.removeIf(arg2 -> predicate1.test(this.field2.apply(arg2)));
   }
}
