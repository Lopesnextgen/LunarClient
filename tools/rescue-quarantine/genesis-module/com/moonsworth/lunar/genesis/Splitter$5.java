package com.moonsworth.lunar.genesis;

import java.util.Iterator;

class Splitter$5 implements Iterable<String> {
   Splitter$5(MixinHelper12_3 mixinhelper12_31, CharSequence text2) {
      this.field2 = mixinhelper12_31;
      this.field1 = text2;
   }

   @Override
   public Iterator<String> iterator() {
      return MixinHelper12_3.method15(this.field2, this.field1);
   }

   @Override
   public String toString() {
      return MixinHelper16_2.method1(", ").method5(new StringBuilder().append('['), this).append(']').toString();
   }
}
