package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

final class Collections2$OrderedPermutationIterator<E> extends MixinHelperIterator32_2<List<E>> {
   @Nullable List<E> nextPermutation;
   final java.util.Comparator<? super E> field2;

   Collections2$OrderedPermutationIterator(List<E> list1, java.util.Comparator<? super E> comparator2) {
      this.nextPermutation = Lists.newArrayList(list1);
      this.field2 = comparator2;
   }

   protected List<E> computeNext() {
      if (this.nextPermutation == null) {
         return (List<E>)this.method1();
      }

      ImmutableList abstractcollectioniterator31 = ImmutableList.method15(this.nextPermutation);
      this.calculateNextPermutation();
      return abstractcollectioniterator31;
   }

   void calculateNextPermutation() {
      int index1 = this.findNextJ();
      if (index1 == -1) {
         this.nextPermutation = null;
      } else {
         int number2 = this.findNextL(index1);
         Collections.swap(this.nextPermutation, index1, number2);
         int index3 = this.nextPermutation.size();
         Collections.reverse(this.nextPermutation.subList(index1 + 1, index3));
      }
   }

   int findNextJ() {
      for (int index1 = this.nextPermutation.size() - 2; index1 >= 0; index1--) {
         if (this.field2.compare(this.nextPermutation.get(index1), this.nextPermutation.get(index1 + 1)) < 0) {
            return index1;
         }
      }

      return -1;
   }

   int findNextL(int index1) {
      Object obj2 = this.nextPermutation.get(index1);

      for (int index3 = this.nextPermutation.size() - 1; index3 > index1; index3--) {
         if (this.field2.compare((E)obj2, this.nextPermutation.get(index3)) < 0) {
            return index3;
         }
      }

      throw new AssertionError("this statement should be unreachable");
   }
}
