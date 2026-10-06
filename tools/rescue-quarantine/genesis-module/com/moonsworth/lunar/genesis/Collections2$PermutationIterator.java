package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.common.collect.ImmutableList;

class Collections2$PermutationIterator<E> extends MixinHelperIterator32_2<List<E>> {
   final List<E> field2;
   final int[] field3;
   final int[] field4;
   int j;

   Collections2$PermutationIterator(List<E> list1) {
      this.field2 = new ArrayList<>(list1);
      int index2 = list1.size();
      this.field3 = new int[index2];
      this.field4 = new int[index2];
      Arrays.fill(this.field3, 0);
      Arrays.fill(this.field4, 1);
      this.j = Integer.MAX_VALUE;
   }

   protected List<E> computeNext() {
      if (this.j <= 0) {
         return (List<E>)this.method1();
      }

      ImmutableList abstractcollectioniterator31 = ImmutableList.method15(this.field2);
      this.calculateNextPermutation();
      return abstractcollectioniterator31;
   }

   void calculateNextPermutation() {
      this.j = this.field2.size() - 1;
      int index1 = 0;
      if (this.j != -1) {
         while (true) {
            int number2 = this.field3[this.j] + this.field4[this.j];
            if (number2 >= 0) {
               if (number2 != this.j + 1) {
                  Collections.swap(this.field2, this.j - this.field3[this.j] + index1, this.j - number2 + index1);
                  this.field3[this.j] = number2;
                  break;
               }

               if (this.j == 0) {
                  break;
               }

               index1++;
               this.switchDirection();
            } else {
               this.switchDirection();
            }
         }
      }
   }

   void switchDirection() {
      this.field4[this.j] = -this.field4[this.j];
      this.j--;
   }
}
