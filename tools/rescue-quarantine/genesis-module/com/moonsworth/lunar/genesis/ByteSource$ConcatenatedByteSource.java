package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.util.Collection;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

final class ByteSource$ConcatenatedByteSource extends MixinHelper12_2 {
   final Iterable<? extends MixinHelper12_2> field1;

   ByteSource$ConcatenatedByteSource(Iterable<? extends MixinHelper12_2> list1) {
      this.field1 = (Iterable<? extends MixinHelper12_2>)Preconditions.checkNotNull(list1);
   }

   public InputStream openStream() {
      return new MultiInputStream(this.field1.iterator());
   }

   public boolean isEmpty() {
      for (MixinHelper12_2 mixinhelper12_22 : this.field1) {
         if (!mixinhelper12_22.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Optional<Long> method3() {
      if (!(this.field1 instanceof Collection)) {
         return Optional.method1();
      }

      long number1 = 0L;

      for (MixinHelper12_2 mixinhelper12_24 : this.field1) {
         Optional serializablebase_25 = mixinhelper12_24.method3();
         if (!serializablebase_25.isPresent()) {
            return Optional.method1();
         }

         number1 += serializablebase_25.get();
         if (number1 < 0L) {
            return Optional.method2(Long.MAX_VALUE);
         }
      }

      return Optional.method2(number1);
   }

   public long size() {
      long number1 = 0L;

      for (MixinHelper12_2 mixinhelper12_24 : this.field1) {
         number1 += mixinhelper12_24.size();
         if (number1 < 0L) {
            return Long.MAX_VALUE;
         }
      }

      return number1;
   }

   public String toString() {
      return "ByteSource.concat(" + this.field1 + ")";
   }
}
