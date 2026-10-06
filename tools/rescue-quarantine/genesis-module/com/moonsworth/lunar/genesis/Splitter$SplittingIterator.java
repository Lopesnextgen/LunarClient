package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;

abstract class Splitter$SplittingIterator extends MixinHelperIterator<String> {
   final CharSequence field2;
   final CharMatcher field3;
   final boolean field4;
   int offset = 0;
   int limit;

   abstract int separatorStart(int number1);

   abstract int separatorEnd(int number1);

   protected Splitter$SplittingIterator(MixinHelper12_3 mixinhelper12_31, CharSequence text2) {
      this.field3 = MixinHelper12_3.method16(mixinhelper12_31);
      this.field4 = MixinHelper12_3.method17(mixinhelper12_31);
      this.limit = MixinHelper12_3.method18(mixinhelper12_31);
      this.field2 = text2;
   }

   protected String computeNext() {
      int number1 = this.offset;

      while (this.offset != -1) {
         int index2 = number1;
         int number4 = this.separatorStart(this.offset);
         int index3;
         if (number4 == -1) {
            index3 = this.field2.length();
            this.offset = -1;
         } else {
            index3 = number4;
            this.offset = this.separatorEnd(number4);
         }

         if (this.offset != number1) {
            while (index2 < index3 && this.field3.matches(this.field2.charAt(index2))) {
               index2++;
            }

            while (index3 > index2 && this.field3.matches(this.field2.charAt(index3 - 1))) {
               index3--;
            }

            if (!this.field4 || index2 != index3) {
               if (this.limit == 1) {
                  index3 = this.field2.length();
                  this.offset = -1;

                  while (index3 > index2 && this.field3.matches(this.field2.charAt(index3 - 1))) {
                     index3--;
                  }
               } else {
                  this.limit--;
               }

               return this.field2.subSequence(index2, index3).toString();
            }

            number1 = this.offset;
         } else {
            this.offset++;
            if (this.offset > this.field2.length()) {
               this.offset = -1;
            }
         }
      }

      return (String)this.method1();
   }
}
