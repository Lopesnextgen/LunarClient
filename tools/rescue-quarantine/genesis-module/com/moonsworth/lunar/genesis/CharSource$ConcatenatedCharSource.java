package com.moonsworth.lunar.genesis;

import java.io.Reader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

final class CharSource$ConcatenatedCharSource extends MixinHelper9 {
   private final Iterable<? extends MixinHelper9> field1;

   CharSource$ConcatenatedCharSource(Iterable<? extends MixinHelper9> list1) {
      this.field1 = (Iterable<? extends MixinHelper9>)Preconditions.checkNotNull(list1);
   }

   public Reader openStream() {
      return new MultiReader(this.field1.iterator());
   }

   public boolean isEmpty() {
      for (MixinHelper9 mixinhelper92 : this.field1) {
         if (!mixinhelper92.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Optional<Long> method2() {
      long number1 = 0L;

      for (MixinHelper9 mixinhelper94 : this.field1) {
         Optional serializablebase_25 = mixinhelper94.method2();
         if (!serializablebase_25.isPresent()) {
            return Optional.method1();
         }

         number1 += serializablebase_25.get();
      }

      return Optional.method2(number1);
   }

   public long length() {
      long number1 = 0L;

      for (MixinHelper9 mixinhelper94 : this.field1) {
         number1 += mixinhelper94.length();
      }

      return number1;
   }

   public String toString() {
      return "CharSource.concat(" + this.field1 + ")";
   }
}
