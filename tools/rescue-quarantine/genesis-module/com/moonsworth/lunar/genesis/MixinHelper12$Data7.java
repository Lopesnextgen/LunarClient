package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

abstract class MixinHelper12$Data7 extends MixinHelperIterator<String> {
   final CharSequence field2;
   final CharMatcher field3;
   final boolean field4;
   int offset = 0;
   int limit;

   abstract int separatorStart(int var1);

   abstract int separatorEnd(int var1);

   protected MixinHelper12$Data7(Splitter var1, CharSequence var2) {
      this.field3 = Splitter.method16(var1);
      this.field4 = Splitter.method17(var1);
      this.limit = Splitter.method18(var1);
      this.field2 = var2;
   }

   protected String computeNext() {
      int var1 = this.offset;

      while (this.offset != -1) {
         int var2 = var1;
         int var4 = this.separatorStart(this.offset);
         int var3;
         if (var4 == -1) {
            var3 = this.field2.length();
            this.offset = -1;
         } else {
            var3 = var4;
            this.offset = this.separatorEnd(var4);
         }

         if (this.offset != var1) {
            while (var2 < var3 && this.field3.matches(this.field2.charAt(var2))) {
               var2++;
            }

            while (var3 > var2 && this.field3.matches(this.field2.charAt(var3 - 1))) {
               var3--;
            }

            if (!this.field4 || var2 != var3) {
               if (this.limit == 1) {
                  var3 = this.field2.length();
                  this.offset = -1;

                  while (var3 > var2 && this.field3.matches(this.field2.charAt(var3 - 1))) {
                     var3--;
                  }
               } else {
                  this.limit--;
               }

               return this.field2.subSequence(var2, var3).toString();
            }

            var1 = this.offset;
         } else {
            this.offset++;
            if (this.offset > this.field2.length()) {
               this.offset = -1;
            }
         }
      }

      return (String)this.computeNext();
   }
}
