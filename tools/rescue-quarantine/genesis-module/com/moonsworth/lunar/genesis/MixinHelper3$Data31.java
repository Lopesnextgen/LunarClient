package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class MixinHelper3$Data31<InElementT, OutElementT, OutSpliteratorT extends Spliterator<OutElementT>> implements Spliterator<OutElementT> {
   @Weak
   @Nullable OutSpliteratorT prefix;
   final Spliterator<InElementT> field1;
   final Function<? super InElementT, OutSpliteratorT> field2;
   final MixinHelper3$Data31.Extension<InElementT, OutSpliteratorT> field3;
   int characteristics;
   long estimatedSize;

   MixinHelper3$Data31(
      OutSpliteratorT var1,
      Spliterator<InElementT> var2,
      Function<? super InElementT, OutSpliteratorT> var3,
      MixinHelper3$Data31.Extension<InElementT, OutSpliteratorT> var4,
      int var5,
      long var6
   ) {
      this.prefix = (OutSpliteratorT)var1;
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = var4;
      this.characteristics = var5;
      this.estimatedSize = var6;
   }

   @Override
   public final boolean tryAdvance(Consumer<? super OutElementT> var1) {
      while (this.prefix == null || !this.prefix.tryAdvance(var1)) {
         this.prefix = null;
         if (!this.field1.tryAdvance(var1x -> this.prefix = this.field2.apply(var1x))) {
            return false;
         }
      }

      if (this.estimatedSize != Long.MAX_VALUE) {
         this.estimatedSize--;
      }

      return true;
   }

   @Override
   public final void forEachRemaining(Consumer<? super OutElementT> var1) {
      if (this.prefix != null) {
         this.prefix.forEachRemaining(var1);
         this.prefix = null;
      }

      this.field1.forEachRemaining(var2 -> {
         Spliterator var3 = this.field2.apply(var2);
         if (var3 != null) {
            var3.forEachRemaining(var1);
         }
      });
      this.estimatedSize = 0L;
   }

   @Override
   public final OutSpliteratorT trySplit() {
      Spliterator var1 = this.field1.trySplit();
      if (var1 != null) {
         int var6 = this.characteristics & -65;
         long var3 = this.estimateSize();
         if (var3 < Long.MAX_VALUE) {
            var3 /= 2L;
            this.estimatedSize -= var3;
            this.characteristics = var6;
         }

         Spliterator var5 = this.field3.newFlatMapSpliterator(this.prefix, var1, this.field2, var6, var3);
         this.prefix = null;
         return (OutSpliteratorT)var5;
      } else if (this.prefix != null) {
         Spliterator var2 = this.prefix;
         this.prefix = null;
         return (OutSpliteratorT)var2;
      } else {
         return null;
      }
   }

   @Override
   public final long estimateSize() {
      if (this.prefix != null) {
         this.estimatedSize = Math.max(this.estimatedSize, this.prefix.estimateSize());
      }

      return Math.max(this.estimatedSize, 0L);
   }

   @Override
   public final int characteristics() {
      return this.characteristics;
   }

   @FunctionalInterface
   interface Extension<InElementT, OutSpliteratorT extends Spliterator<?>> {
      OutSpliteratorT newFlatMapSpliterator(
         OutSpliteratorT var1, Spliterator<InElementT> var2, Function<? super InElementT, OutSpliteratorT> var3, int var4, long var5
      );
   }
}
